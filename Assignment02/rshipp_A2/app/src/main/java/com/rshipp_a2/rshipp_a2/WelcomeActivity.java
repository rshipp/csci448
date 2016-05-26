package com.rshipp_a2.rshipp_a2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private static final String EXTRA_ONE_PLAYER = "com.rshipp_a2.rshipp_a2.one_player";
    private static final String EXTRA_X_FIRST = "com.rshipp_a2.rshipp_a2.x_first";
    private static final String EXTRA_SCORE = "com.rshipp_a2.rshipp_a2.score";

    private static final String KEY_ONE_PLAYER = "one_player";
    private static final String KEY_X_FIRST = "x_first";
    private static final String KEY_SCORE = "score";
    private static final int REQUEST_CODE_OPTIONS = 0;
    private static final int REQUEST_CODE_GAME = 1;


    private boolean mOnePlayer;
    private boolean mXFirst;
    private int[] mScore;

    private Button mOptionsButton;
    private Button mNewGameButton;
    private Button mQuitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Default options
        mOnePlayer = true;
        mXFirst = true;

        mOptionsButton = (Button)findViewById(R.id.options_button);
        mOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start OptionsActivity
                Intent i = OptionsActivity.newIntent(WelcomeActivity.this, mOnePlayer, mXFirst, mScore);
                startActivityForResult(i, REQUEST_CODE_OPTIONS);
            }
        });

        mNewGameButton = (Button)findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GameActivity
                Intent i = GameActivity.newIntent(WelcomeActivity.this, mOnePlayer, mXFirst, mScore);
                startActivityForResult(i, REQUEST_CODE_GAME);
            }
        });

        mQuitButton = (Button) findViewById(R.id.quit_button);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (savedInstanceState != null) {
            mOnePlayer = savedInstanceState.getBoolean(KEY_ONE_PLAYER, true);
            mXFirst = savedInstanceState.getBoolean(KEY_X_FIRST, true);
            mScore = savedInstanceState.getIntArray(KEY_SCORE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK && resultCode != Activity.RESULT_FIRST_USER) {
            return;
        }

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_OPTIONS) {
                if (data == null) {
                    return;
                }
                mOnePlayer = OptionsActivity.isOnePlayer(data);
                mXFirst = OptionsActivity.isXFirst(data);
                mScore = OptionsActivity.getScore(data);
            } else if (requestCode == REQUEST_CODE_GAME) {
                if (data == null) {
                    return;
                }
                mScore = GameActivity.getScore(data);
            }
        } else if (resultCode == Activity.RESULT_FIRST_USER) {
            if (requestCode == REQUEST_CODE_GAME) {
                if (data == null) {
                    return;
                }
                mScore = GameActivity.getScore(data);
                // Start GameActivity
                Intent i = GameActivity.newIntent(WelcomeActivity.this, mOnePlayer, mXFirst, mScore);
                startActivityForResult(i, REQUEST_CODE_GAME);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_ONE_PLAYER, mOnePlayer);
        savedInstanceState.putBoolean(KEY_X_FIRST, mXFirst);
        savedInstanceState.putIntArray(KEY_SCORE, mScore);
    }
}
