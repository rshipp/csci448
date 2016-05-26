

package com.rshipp_a2.rshipp_a2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GAME = 1;

    private static final String EXTRA_ONE_PLAYER = "com.rshipp_a2.rshipp_a2.one_player";
    private static final String EXTRA_X_FIRST = "com.rshipp_a2.rshipp_a2.x_first";
    private static final String EXTRA_SCORE = "com.rshipp_a2.rshipp_a2.score";

    private static final String KEY_ONE_PLAYER = "one_player";
    private static final String KEY_X_FIRST = "x_first";
    private static final String KEY_O_TURN = "o_turn";
    private static final String KEY_GAME_OVER = "game_over";
    private static final String KEY_SCORE = "score";
    private static final String KEY_BOARD = "board";

    private static final int X = 0;
    private static final int O = 2;
    private static final int DRAW = 1;

    private boolean mOnePlayer;
    private boolean mXFirst;
    private int[] mScore;
    private boolean mOTurn;
    private boolean mGameOver;
    private int[] mBoard;

    private TicTacToeGame mGame;

    private TextView mScoreXView;
    private TextView mScoreDrawView;
    private TextView mScoreOView;
    private Button mBackButton;
    private Button mPlayAgainButton;

    private ImageButton mImageButton1;
    private ImageButton mImageButton2;
    private ImageButton mImageButton3;
    private ImageButton mImageButton4;
    private ImageButton mImageButton5;
    private ImageButton mImageButton6;
    private ImageButton mImageButton7;
    private ImageButton mImageButton8;
    private ImageButton mImageButton9;


    public static Intent newIntent(Context packageContext, boolean onePlayer, boolean xFirst, int[] score) {
        Intent i = new Intent(packageContext, GameActivity.class);
        i.putExtra(EXTRA_ONE_PLAYER, onePlayer);
        i.putExtra(EXTRA_X_FIRST, xFirst);
        i.putExtra(EXTRA_SCORE, score);
        return i;
    }

    public static int[] getScore(Intent result) {
        return result.getIntArrayExtra(EXTRA_SCORE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mOnePlayer = extras.getBoolean(EXTRA_ONE_PLAYER, true);
            mXFirst = extras.getBoolean(EXTRA_X_FIRST, true);
            mScore = extras.getIntArray(EXTRA_SCORE);
        }
        if (!mXFirst) {
            // If O goes first, the game starts on O's turn
            mOTurn = true;
        }
        if (savedInstanceState != null) {
            mOnePlayer = savedInstanceState.getBoolean(KEY_ONE_PLAYER, true);
            mXFirst = savedInstanceState.getBoolean(KEY_X_FIRST, true);
            mOTurn = savedInstanceState.getBoolean(KEY_O_TURN, false);
            mGameOver = savedInstanceState.getBoolean(KEY_GAME_OVER, false);
            mScore = savedInstanceState.getIntArray(KEY_SCORE);
            mBoard = savedInstanceState.getIntArray(KEY_BOARD);
        }
        if(mScore == null) {
            mScore = new int[] { 0, 0, 0 };
        }
        if(mBoard == null) {
            mBoard = new int[] {
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0
            };
        }

        mScoreXView = (TextView) findViewById(R.id.score_x_text);
        mScoreDrawView = (TextView) findViewById(R.id.score_draw_text);
        mScoreOView = (TextView) findViewById(R.id.score_o_text);

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPlayAgainButton = (Button) findViewById(R.id.play_again_button);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GameActivity
                Intent data = new Intent();
                data.putExtra(EXTRA_SCORE, mScore);
                setResult(Activity.RESULT_FIRST_USER, data);  //idk
                finish();
            }
        });

        mImageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        mImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(0);
                drawBackgrounds();
            }
        });

        mImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(1);
                drawBackgrounds();
            }
        });

        mImageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        mImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(2);
                drawBackgrounds();
            }
        });

        mImageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        mImageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(3);
                drawBackgrounds();
            }
        });

        mImageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        mImageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(4);
                drawBackgrounds();
            }
        });

        mImageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        mImageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(5);
                drawBackgrounds();
            }
        });

        mImageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        mImageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(6);
                drawBackgrounds();
            }
        });

        mImageButton8 = (ImageButton) findViewById(R.id.imageButton8);
        mImageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(7);
                drawBackgrounds();
            }
        });

        mImageButton9 = (ImageButton) findViewById(R.id.imageButton9);
        mImageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTurn(8);
                drawBackgrounds();
            }
        });

        if (mGameOver) {
            disableBoard();
        }
        drawBackgrounds();
        updateScore();
    }

    private void takeTurn(int thisSquare) {
        if (mBoard[thisSquare] != TicTacToeGame.EMPTY) {
            // Already taken
            return;
        }
        mGame = new TicTacToeGame(mBoard, mXFirst, !mOnePlayer);
        // Human move
        if (!mOTurn) {
            mBoard = mGame.xTurn(thisSquare);
        } else if (mOTurn && !mXFirst) {
            mBoard = mGame.oTurn(thisSquare);
        } else if (!mOnePlayer && mOTurn) {
            mBoard = mGame.oTurn(thisSquare);
        } else if (!mOnePlayer && !mOTurn) {
            mBoard = mGame.xTurn(thisSquare);
        }
        // Check
        if (mGame.xWon()) {
            gameOver(X);
            return;
        } else if (mGame.oWon()) {
            gameOver(O);
            return;
        } else if (mGame.draw()) {
            gameOver(DRAW);
            return;
        }
        // Computer move?
        if (mOnePlayer) {
            mBoard = mGame.computerTurn();
            // Check
            if (mGame.xWon()) {
                gameOver(X);
                return;
            } else if (mGame.oWon()) {
                gameOver(O);
                return;
            } else if (mGame.draw()) {
                gameOver(DRAW);
                return;
            }
        } else {
            mOTurn = !mOTurn;
        }
    }

    private void drawBackgrounds() {
        int[] drawableBoard = new int[] {
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };
        for (int i=0; i<mBoard.length; i++) {
            if (mBoard[i] == TicTacToeGame.X) {
                drawableBoard[i] = R.drawable.x;
            } else if (mBoard[i] == TicTacToeGame.O) {
                drawableBoard[i] = R.drawable.o;
            }
        }
        if (drawableBoard[0] != 0)
            mImageButton1.setBackground(getResources().getDrawable(drawableBoard[0]));
        if (drawableBoard[1] != 0)
            mImageButton2.setBackground(getResources().getDrawable(drawableBoard[1]));
        if (drawableBoard[2] != 0)
            mImageButton3.setBackground(getResources().getDrawable(drawableBoard[2]));
        if (drawableBoard[3] != 0)
            mImageButton4.setBackground(getResources().getDrawable(drawableBoard[3]));
        if (drawableBoard[4] != 0)
            mImageButton5.setBackground(getResources().getDrawable(drawableBoard[4]));
        if (drawableBoard[5] != 0)
            mImageButton6.setBackground(getResources().getDrawable(drawableBoard[5]));
        if (drawableBoard[6] != 0)
            mImageButton7.setBackground(getResources().getDrawable(drawableBoard[6]));
        if (drawableBoard[7] != 0)
            mImageButton8.setBackground(getResources().getDrawable(drawableBoard[7]));
        if (drawableBoard[8] != 0)
            mImageButton9.setBackground(getResources().getDrawable(drawableBoard[8]));
    }

    private void gameOver(int winner) {
        // Update score
        mScore[winner]++;
        int messageResId = 0;
        if (winner == X) {
            messageResId = R.string.x_won_toast;
        } else if (winner == O) {
            messageResId = R.string.o_won_toast;
        } else {
            messageResId = R.string.draw_toast;
        }
        updateScore();

        // Disable buttons
        disableBoard();

        // Display message
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

        // Persist
        mGameOver = true;
    }

    private void disableBoard() {
        // Disable buttons so the user can't rack up points after the game is over
        mImageButton1.setEnabled(false);
        mImageButton2.setEnabled(false);
        mImageButton3.setEnabled(false);
        mImageButton4.setEnabled(false);
        mImageButton5.setEnabled(false);
        mImageButton6.setEnabled(false);
        mImageButton7.setEnabled(false);
        mImageButton8.setEnabled(false);
        mImageButton9.setEnabled(false);
    }

    private void updateScore() {
        mScoreXView.setText(((Integer) mScore[0]).toString());
        mScoreDrawView.setText(((Integer) mScore[1]).toString());
        mScoreOView.setText(((Integer) mScore[2]).toString());

        Intent data = new Intent();
        data.putExtra(EXTRA_SCORE, mScore);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_GAME) {
            if (data == null) {
                return;
            }
            mScore = GameActivity.getScore(data);
            updateScore();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_ONE_PLAYER, mOnePlayer);
        savedInstanceState.putBoolean(KEY_X_FIRST, mXFirst);
        savedInstanceState.putBoolean(KEY_O_TURN, mOTurn);
        savedInstanceState.putBoolean(KEY_GAME_OVER, mGameOver);
        savedInstanceState.putIntArray(KEY_SCORE, mScore);
        savedInstanceState.putIntArray(KEY_BOARD, mBoard);
    }

}
