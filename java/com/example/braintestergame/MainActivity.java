package com.example.braintestergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button go;
    ConstraintLayout gameLayout;
    ArrayList<Integer> options = new ArrayList<Integer>();
    TextView resultText;
    TextView scoreTextView;
    int score=0, numberOfQuestions=0;
    int setAnswerPosition;
    TextView questionText ;
    Button playAgainButton ;
    TextView timer ;
    Button option1;
    Button option2;
    Button option3;
    Button option4;

    public void PlayAgain(final View view){

        playAgainButton.setVisibility(view.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timer.setText("30s");
        scoreTextView.setText(Integer.toString(numberOfQuestions) +"/"+ Integer.toString(score));
        newQuestion();

        new CountDownTimer(30000+100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(view.VISIBLE);
                resultText.setText("Done!");
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);
                timer.setText("0s");
            }
        }.start();
    }

    public void GoButton(View view){


        go.setVisibility(view.INVISIBLE);
        gameLayout.setVisibility(view.VISIBLE);
        PlayAgain(findViewById(R.id.timer));

    }

    public void ChooseAnswer(View view){

        if(Integer.toString(setAnswerPosition).equals(view.getTag().toString())){
            resultText.setText("Correct!");
            score++;
        }
        else{
            resultText.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(numberOfQuestions) +"/"+ Integer.toString(score));
        newQuestion();
    }

    public void newQuestion(){

        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionText = (TextView) findViewById(R.id.question);

        questionText.setText(Integer.toString(a) + "+" + Integer.toString(b));
        setAnswerPosition = random.nextInt(4);

        options.clear();

        for (int i = 0; i < 4; i++) {

            if (i == setAnswerPosition) {
                options.add(a + b);
            }
            else {

                int wrongAnswers = random.nextInt(41);

                while (wrongAnswers == a + b) {

                    wrongAnswers = random.nextInt(41);
                }
                options.add(wrongAnswers);
            }
        }

        option1.setText(Integer.toString(options.get(0)));
        option2.setText(Integer.toString(options.get(1)));
        option3.setText(Integer.toString(options.get(2)));
        option4.setText(Integer.toString(options.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go = (Button) findViewById(R.id.Gobutton);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        go.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        option1 = (Button) findViewById(R.id.button1);
        option2 = (Button) findViewById(R.id.button2);
        option3 = (Button) findViewById(R.id.button3);
        option4 = (Button) findViewById(R.id.button4);
        playAgainButton = (Button) findViewById(R.id.playAgain);

        resultText = findViewById(R.id.correct);
        scoreTextView = findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);

    }

}