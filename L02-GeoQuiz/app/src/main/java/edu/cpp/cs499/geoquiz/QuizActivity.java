package edu.cpp.cs499.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yusun on 3/19/15.
 */
public class QuizActivity extends Activity {

    private TrueFalse[] questionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private int currentIndex = 0;

    TextView questionTextView;
    Button trueButton;
    Button falseButton;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.quiz_activity);

        questionTextView = (TextView) findViewById(R.id.questionText);

        trueButton = (Button) findViewById(R.id.trueButton);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        falseButton = (Button) findViewById(R.id.falseButton);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // next button
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        if (currentIndex < questionBank.length) {
            questionTextView.setText(questionBank[currentIndex].getQuestionResId());
        }
    }

    private void checkAnswer(boolean givenAnswer) {
        if (givenAnswer == questionBank[currentIndex].getAnswer()) {
            Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }
}
