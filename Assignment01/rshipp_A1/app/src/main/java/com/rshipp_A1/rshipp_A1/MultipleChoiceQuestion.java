package com.rshipp_A1.rshipp_A1;

/**
 * Multiple choice question implementation, allows arbitrary number of choices.
 */
public class MultipleChoiceQuestion extends Question {
    private String mCorrectAnswer;
    private int[] mAnswerChoiceIds;
    public MultipleChoiceQuestion(int textResId, String correctAnswer, int[] choiceIds) {
        super(textResId);
        mCorrectAnswer = correctAnswer;
        mAnswerChoiceIds = choiceIds;
    }

    public boolean isAnswerCorrect(Object answer) {
        return mCorrectAnswer.equals((String)answer);
    }

    public int[] getAnswerChoiceIds() {
        return mAnswerChoiceIds;
    }
}
