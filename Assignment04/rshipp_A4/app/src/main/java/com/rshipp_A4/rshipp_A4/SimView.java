package com.rshipp_A4.rshipp_A4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * This is the View that contains the 2D drawing objects.
 * @author Bill Hoff
 */
public class SimView extends View {
    private static final String TAG = "SimView";

    /**
     * Ball position and radius (in pixels).
     */
    private PointF mBallPos;
    private Rect mPaddle;
    private Rect[][] mBlocks;
    private int mR;
    private float mLightLevel;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Constructor for SimView.
    public SimView(Context context)   {
        super(context);

        // Initialize values to something reasonable.
        mBallPos = new PointF(0, 0);
        mPaddle = new Rect(0, 0, 0, 0);
        mBlocks = new Rect[0][0];
        mR = 30;
        mLightLevel = 50;
    }


    // Android calls this to redraw the view, after invalidate()
    @Override
    protected void onDraw(Canvas canvas)    {
        super.onDraw(canvas);
        //Log.d(TAG, "onDraw(); X = " + mX + " Y = " + mY);
        if (mLightLevel > 10) {
            this.setBackgroundColor(0xFFFFFFFF);
            mPaint.setColor(0xFFF7CAC9);
        } else {
            this.setBackgroundColor(0xFF000000);
            mPaint.setColor(0xFF532D3B);
        }
        canvas.drawCircle(mBallPos.x, mBallPos.y, mR, mPaint);

        if (mLightLevel > 10) {
            mPaint.setColor(0xFF92a8d1);
        } else {
            mPaint.setColor(0xFF403F6F); //50574C);
        }
        for (int i=0; i<mBlocks.length; i++) {
            for (int j=0; j<mBlocks[i].length; j++) {
                canvas.drawRect(mBlocks[i][j], mPaint);
            }
        }
        canvas.drawRect(mPaddle, mPaint);
    }


    // Setters.

    public void setBallPos(PointF ballPos) {
        mBallPos = ballPos;
    }

    public void setPaddle(Rect paddle) {
        mPaddle = paddle;
    }

    public void setBlocks(Rect[][] blocks) {
        mBlocks = blocks;
    }

    public void setR(int r) {
        mR = r;
    }

    public void setLightLevel(float level) {
        mLightLevel = level;
    }
}
