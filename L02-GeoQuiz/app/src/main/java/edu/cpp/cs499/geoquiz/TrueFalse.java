package edu.cpp.cs499.geoquiz;

/**
 * Created by yusun on 3/19/15.
 */
public class TrueFalse {

    private int questionResId;
    private boolean answer;

    public TrueFalse(int questionResId, boolean answer) {
        this.questionResId = questionResId;
        this.answer = answer;
    }

    public int getQuestionResId() {
        return questionResId;
    }

    public void setQuestionResId(int questionResId) {
        this.questionResId = questionResId;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
