package com.rshipp_A1.rshipp_A1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rshipp_A1.rshipp_A1.R;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheckButton;
    private RadioGroup mRadioGroup;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private TextView mScoreTextView;

    private int mScore = 0;

    private Question[] mQuestionBank;
    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        LinearLayout trueFalseLayout = (LinearLayout) findViewById(R.id.true_false_layout);
        LinearLayout fillInTheBlankLayout = (LinearLayout) findViewById(R.id.fill_in_the_blank_layout);
        LinearLayout multipleChoiceLayout = (LinearLayout) findViewById(R.id.multiple_choice_layout);

        // Hide all initially
        trueFalseLayout.setVisibility(View.GONE);
        fillInTheBlankLayout.setVisibility(View.GONE);
        multipleChoiceLayout.setVisibility(View.GONE);

        // Show selectively
        if(TrueFalseQuestion.class == mQuestionBank[mCurrentIndex].getClass()) {
            trueFalseLayout.setVisibility(View.VISIBLE);
        } else if (FillInTheBlankQuestion.class == mQuestionBank[mCurrentIndex].getClass()) {
            fillInTheBlankLayout.setVisibility(View.VISIBLE);
        } else if (MultipleChoiceQuestion.class == mQuestionBank[mCurrentIndex].getClass()) {
            mRadioGroup.removeAllViews();
            for (int choice : ((MultipleChoiceQuestion)mQuestionBank[mCurrentIndex]).getAnswerChoiceIds()) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(choice);
                mRadioGroup.addView(radioButton);
            }

            multipleChoiceLayout.setVisibility(View.VISIBLE);
        }
    }

    private void checkAnswer(Object userAnswer) {
        int messageResId = 0;
        if (mQuestionBank[mCurrentIndex].isAnswerCorrect(userAnswer)) {
            mScore++;
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        mScoreTextView.setText(Integer.toString(mScore));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuestionBank = new Question[] {
                new TrueFalseQuestion(R.string.question_oceans, true),
                new FillInTheBlankQuestion(R.string.question_mideast, getString(R.string.answer_mideast)),
                new MultipleChoiceQuestion(R.string.question_africa, getString(R.string.answer_africa), new int[] {
                        R.string.choice_africa_1,
                        R.string.choice_africa_2,
                        R.string.choice_africa_3,
                        R.string.choice_africa_4
                }),
        };

        setContentView(R.layout.activity_quiz);
        ViewGroup layout = (LinearLayout) findViewById(R.id.content);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheckButton = (Button) findViewById(R.id.check_button);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText blankEdit = (EditText) findViewById(R.id.blank_edit_text);
                checkAnswer(blankEdit.getText().toString());
            }
        });

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedButton = (RadioButton) findViewById(checkedId);
                checkAnswer(selectedButton.getText().toString());
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mScoreTextView = (TextView) findViewById(R.id.score);
        mScoreTextView.setText(Integer.toString(mScore));

        updateQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
