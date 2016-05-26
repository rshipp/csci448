package com.rshipp_A1.rshipp_A1;

/**
 * True/False question implementation.
 */
public class TrueFalseQuestion extends Question {
    private boolean mCorrectAnswer;
    public TrueFalseQuestion(int textResId, boolean correctAnswer) {
        super(textResId);
        mCorrectAnswer = correctAnswer;
    }

    public boolean isAnswerCorrect(Object answer) {
        return mCorrectAnswer == (boolean)answer;
    }
}
