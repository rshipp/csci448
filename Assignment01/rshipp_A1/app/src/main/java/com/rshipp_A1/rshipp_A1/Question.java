package com.rshipp_A1.rshipp_A1;

/**
 * Abstract Question base class.
 */
public abstract class Question {
    protected int mTextResId;
    public Question(int textResId) {
        mTextResId = textResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public abstract boolean isAnswerCorrect(Object answer);

}
