package com.rshipp_A1.rshipp_A1;

/**
 * Fill in the blank question implementation.
 */
public class FillInTheBlankQuestion extends Question {
    private String mCorrectAnswer;
    public FillInTheBlankQuestion(int textResId, String correctAnswer) {
        super(textResId);
        mCorrectAnswer = correctAnswer;
    }

    public boolean isAnswerCorrect(Object answer) {
        return mCorrectAnswer.equalsIgnoreCase((String)answer);
    }
}
