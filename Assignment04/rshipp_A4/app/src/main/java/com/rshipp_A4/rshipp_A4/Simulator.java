package com.rshipp_A4.rshipp_A4;

import android.graphics.PointF;
import android.graphics.Rect;

import java.util.concurrent.TimeUnit;

/**
 * This models the simulation of the ball.
 * @author Bill Hoff
 */
public class Simulator {
    /**
     * Width and height of screen.
     */
    private int mWidth, mHeight;

    /**
     * Ball position (in pixels).
     */
    private PointF mBallPos;

    /**
     * Ball radius (in pixels).
     */
    private int mBallRadius;

    /**
     * Ball velocity.
     */
    private PointF mBallVel;

    /**
     * Paddle velocity.
     */
    private PointF mPaddleVel;

    /**
     * Paddle rect.
     */
    private Rect mPaddle;

    /**
     * Blocks
     */
    private Rect[][] mBlocks;

    /**
     * Blocks
     */
    private boolean mGameOver, mGameStarted, mPastPaddle;

    /**
     * Device acceleration (X,Y only).
     */
    private PointF mDeviceAccel;

    /**
     * Device light level.
     */
    private float mLightLevel;

    private long t;

    // Constructor for Simulator.
    public Simulator(int width, int height)   {
        mWidth = width;
        mHeight = height;

        mGameOver = false;
        mGameStarted = false;
        mPastPaddle = false;

        // Create variables for ball position and velocity.
        mPaddleVel = new PointF(0, 0);

        // Initialize ball radius to something reasonable.
        mBallRadius = width/30;

        // Initialize paddle size to something reasonable.
        mPaddle = new Rect(0, mHeight-mBallRadius*2-height/45, mBallRadius*5, mHeight-mBallRadius*2);

        // Create variables for ball position and velocity.
        mBallVel = new PointF(0, 0);
        mBallPos = new PointF(mBallRadius, mPaddle.top-mBallRadius);
        mBallVel.x = 0.3F;
        mBallVel.y = 0.3F;

        // Initialize blocks
        int blockSpace = mWidth/8;
        int blockPadding = mWidth/150;
        int blockWidth = blockSpace - blockPadding*2;
        int blockHeight = mPaddle.height();
        int blocksTop = 5*mBallRadius;
        mBlocks = new Rect[8][4];
        for (int i=0; i<8; i++) {
            for (int j=0; j<4; j++) {
                mBlocks[i][j] = new Rect(i*blockSpace+blockPadding, j*blockHeight+j*2*blockPadding+blocksTop, i*blockSpace+blockWidth+blockPadding, (j+1)*blockHeight+j*2*blockPadding+blocksTop); //(left, top, right, bottom)
            }
        }


        // Create variable for device acceleration (X,Y only).
        mDeviceAccel = new PointF(0, 0);
        mLightLevel = 50;

        t = System.currentTimeMillis();
    }


    public void runSimulation() {
        if (mGameOver) {
            try {
                TimeUnit.SECONDS.sleep(1);
                return;
            } catch (InterruptedException e) {
                return;
            }
        }

        long dt = System.currentTimeMillis() - t;
        t = System.currentTimeMillis();

        // Set velocity and position
        mPaddleVel.x = mPaddleVel.x + -mDeviceAccel.x * dt;

        // Set a velocity ceiling
        if (mPaddleVel.x > 3) mPaddleVel.x = 3;
        if (mPaddleVel.y < -3) mPaddleVel.y = -3;

        mBallPos.x = mBallPos.x + mBallVel.x*dt;
        mBallPos.y = mBallPos.y + mBallVel.y*dt;

        mPaddle.offset(Math.round(mPaddleVel.x*dt + 0.5F*mDeviceAccel.x*dt*dt), 0);

        // If ball hits the edge of the screen, reverse velocity (bounce)
        if (mBallPos.x > mWidth-mBallRadius) {
            mBallPos.x = mWidth-mBallRadius;
            mBallVel.x = -mBallVel.x;
        }
        if (mBallPos.x < 0+mBallRadius) {
            mBallPos.x = 0+mBallRadius;
            mBallVel.x = -mBallVel.x;
        }
        if (mBallPos.y < 0+mBallRadius) {
            mBallPos.y = 0+mBallRadius;
            mBallVel.y = -mBallVel.y;
        }

        // Make the paddle bounce with a smaller velocity
        if (mPaddle.right > mWidth) {
            mPaddle.offsetTo(mWidth-mPaddle.width(), mPaddle.top);
            mPaddleVel.x = -mPaddleVel.x*0.3F;
        }
        if (mPaddle.left < 0) {
            mPaddle.offsetTo(0, mPaddle.top);
            mPaddleVel.x = -mPaddleVel.x*0.3F;
        }

        // Make the ball bounce off the paddle
        if (!mPastPaddle && mBallPos.y > mPaddle.top-mBallRadius) {
            if (mBallPos.x + mBallRadius > mPaddle.left && mBallPos.x - mBallRadius < mPaddle.right) {
                mBallPos.y = mPaddle.top - mBallRadius;
                mBallVel.y = -mBallVel.y*1.05F;
            }
        }

        // Make the ball break and bounce off of blocks
        blocks: {
            for (int i=0; i<mBlocks.length; i++) {
                for (int j = 0; j < mBlocks[i].length; j++) {
                    if (mBallPos.y + mBallRadius > mBlocks[i][j].top && mBallPos.y - mBallRadius < mBlocks[i][j].bottom) {
                        if (mBallPos.x + mBallRadius > mBlocks[i][j].left && mBallPos.x - mBallRadius < mBlocks[i][j].right) {
                            mBallVel.y = -mBallVel.y;
                            mBlocks[i][j].set(0, 0, 0, 0);
                            break blocks;
                        }
                    }
                }
            }
        }

        // Check win/lose conditions
        if (mBallPos.y > mPaddle.top && mGameStarted) {
            mPastPaddle = true;
        }
        if (mBallPos.y > mHeight+mBallRadius && mGameStarted) {
            mBallVel.y = 0;
            mBallVel.x = 0;
            mGameOver = true;
        }
        check: {
            for (int i=0; i<mBlocks.length; i++) {
                for (int j = 0; j < mBlocks[i].length; j++) {
                    if (!(mBlocks[i][j].width() == 0)) {
                        // Block is not broken
                        break check;
                    }
                }
            }
            mGameOver = true;
        }
        mGameStarted = true;
    }



    // Getters and setters.

    public PointF getBallPos() {
        return mBallPos;
    }

    public void setBallPos(PointF ballPos) {
        mBallPos = ballPos;
    }

    public Rect getPaddle() {
        return mPaddle;
    }

    public Rect[][] getBlocks() {
        return mBlocks;
    }

    public void setPaddle(Rect paddle) {
        mPaddle = paddle;
    }

    public PointF getBallVel() {
        return mBallVel;
    }

    public void setBallVel(PointF ballVel) {
        mBallVel = ballVel;
    }

    public int getBallRadius() {
        return mBallRadius;
    }

    public void setBallRadius(int ballRadius) {
        mBallRadius = ballRadius;
    }

    public PointF getDeviceAccel() {
        return mDeviceAccel;
    }

    public void setDeviceAccel(PointF deviceAccel) {
        mDeviceAccel = deviceAccel;
        mDeviceAccel.set(deviceAccel.x/3500, deviceAccel.y/3500);
    }

    public float getLightLevel() {
        return mLightLevel;
    }

    public void setLightLevel(float lightLevel) {
        mLightLevel = lightLevel;
    }

    public boolean isGameOver() {
        return mGameOver;
    }
}
