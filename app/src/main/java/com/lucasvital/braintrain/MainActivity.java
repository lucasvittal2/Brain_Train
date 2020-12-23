package com.lucasvital.braintrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout gameLayout;
    GridLayout gridLayout;
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    TextView gameTitleTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<Integer> incorrectAnswers;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;



    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText(("0/0"));
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateNewQuestion();
        setTimer();
        gridLayout.setActivated(true);
    }
    private void setTimer() {
        new CountDownTimer(3100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000)+ "s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score is: " + Integer.toString(score) + "/ " + Integer.toString(numberOfQuestions));
                Log.i("ADSAS","adasd");
                gridLayout.block


            }
        }.start();
    }
    public void generateNewQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        answers.clear();
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        for(int i =0; i<4; i++){
            if( i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameTitleTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));



    }
    public void  choooseAnswer(View view){

        resultTextView.setVisibility(View.VISIBLE);
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("Correct:","correct");
            score ++;
            resultTextView.setText("Correct!");
        }
        else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/ " + Integer.toString(numberOfQuestions));
        generateNewQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);////
        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        gameTitleTextView = (TextView)findViewById(R.id.gameTitleTextView);
        gameLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        gridLayout =(GridLayout)findViewById(R.id.gridLayout);
        button0 = (Button)findViewById((R.id.button1));
        button1 = (Button)findViewById((R.id.button2));
        button2 = (Button)findViewById((R.id.button3));
        button3 = (Button)findViewById((R.id.button4));
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        setTimer();












    }
}
