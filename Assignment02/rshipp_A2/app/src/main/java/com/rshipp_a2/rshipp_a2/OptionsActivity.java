package com.rshipp_a2.rshipp_a2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class OptionsActivity extends AppCompatActivity {

    private static final String EXTRA_ONE_PLAYER = "com.rshipp_a2.rshipp_a2.one_player";
    private static final String EXTRA_X_FIRST = "com.rshipp_a2.rshipp_a2.x_first";
    private static final String EXTRA_SCORE = "com.rshipp_a2.rshipp_a2.score";

    private static final String KEY_ONE_PLAYER = "one_player";
    private static final String KEY_X_FIRST = "x_first";
    private static final String KEY_SCORE = "score";

    private boolean mOnePlayer;
    private boolean mXFirst;
    private int[] mScore;

    private Button mClearButton;
    private RadioGroup mPlayersGroup;
    private RadioGroup mFirstGroup;


    public static Intent newIntent(Context packageContext, boolean onePlayer, boolean xFirst, int[] score) {
        Intent i = new Intent(packageContext, OptionsActivity.class);
        i.putExtra(EXTRA_ONE_PLAYER, onePlayer);
        i.putExtra(EXTRA_X_FIRST, xFirst);
        i.putExtra(EXTRA_SCORE, score);
        return i;
    }

    public static boolean isOnePlayer(Intent result) {
        return result.getBooleanExtra(EXTRA_ONE_PLAYER, true);
    }

    public static boolean isXFirst(Intent result) {
        return result.getBooleanExtra(EXTRA_X_FIRST, true);
    }

    public static int[] getScore(Intent result) {
        return result.getIntArrayExtra(EXTRA_SCORE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mOnePlayer = extras.getBoolean(EXTRA_ONE_PLAYER, true);
            mXFirst = extras.getBoolean(EXTRA_X_FIRST, true);
            mScore = extras.getIntArray(EXTRA_SCORE);
        }
        if (savedInstanceState != null) {
            mOnePlayer = savedInstanceState.getBoolean(KEY_ONE_PLAYER, true);
            mXFirst = savedInstanceState.getBoolean(KEY_X_FIRST, true);
            mScore = savedInstanceState.getIntArray(KEY_SCORE);
        }

        mPlayersGroup = (RadioGroup) findViewById(R.id.players_radio_group);
        if(mOnePlayer) {
            mPlayersGroup.check(R.id.players_one);
        } else {
            mPlayersGroup.check(R.id.players_two);
        }
        mPlayersGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.players_one) {
                    mOnePlayer = true;
                } else {
                    mOnePlayer = false;
                }
                updateIntent();
            }
        });

        mFirstGroup = (RadioGroup) findViewById(R.id.first_radio_group);
        if(mXFirst) {
            mFirstGroup.check(R.id.x_first);
        } else {
            mFirstGroup.check(R.id.o_first);
        }
        mFirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.x_first) {
                    mXFirst = true;
                } else {
                    mXFirst = false;
                }
                updateIntent();
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore = new int[]{0, 0, 0};
                updateIntent();
            }
        });

        updateIntent();

    }

    private void updateIntent() {
        Intent data = new Intent();
        data.putExtra(EXTRA_ONE_PLAYER, mOnePlayer);
        data.putExtra(EXTRA_X_FIRST, mXFirst);
        data.putExtra(EXTRA_SCORE, mScore);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_ONE_PLAYER, mOnePlayer);
        savedInstanceState.putBoolean(KEY_X_FIRST, mXFirst);
        savedInstanceState.putIntArray(KEY_SCORE, mScore);
    }


}
