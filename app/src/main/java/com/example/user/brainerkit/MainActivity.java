package com.example.user.brainerkit;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout gameRelativeLayout;
    GridLayout gridLayout;
    Button startButton;
    Button option0;
    Button option1;
    Button option2;
    Button option3;
    Button playAgainButton;
    TextView result;
    TextView scoreText;
    TextView questionText;
    TextView timerText;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    int score = 0;
    int noOfQuestions = 0;

    public void generateQuestion(){

        Random random = new Random(  );
        answers = new ArrayList<Integer>(  );

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        questionText.setText( Integer.toString( a ) + " + " + Integer.toString( b ) );
        locationOfCorrectAnswer = random.nextInt(4);

        for (int i=0; i<4; i++) {

            if(i == locationOfCorrectAnswer)
                answers.add(a + b);
            else {
                int incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a+b)
                    incorrectAnswer = random.nextInt(41);
                answers.add( incorrectAnswer);
            }
        }
        option0.setText( Integer.toString( answers.get( 0 ) ) );
        option1.setText( Integer.toString( answers.get( 1 ) ) );
        option2.setText( Integer.toString( answers.get( 2 ) ) );
        option3.setText( Integer.toString( answers.get( 3 ) ) );
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals( Integer.toString( locationOfCorrectAnswer ) )){
            score++;
            result.setText( "Correct !!" );
        } else {

            result.setText( "Wrong !" );
        }

        noOfQuestions++;
        scoreText.setText( Integer.toString( score ) + "/" + Integer.toString( noOfQuestions ) );

        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility( View.INVISIBLE );
        gameRelativeLayout.setVisibility( RelativeLayout.VISIBLE );

        playAgain( findViewById( R.id.playAgainButton ));
    }

    public void playAgain(View view) {

        score = 0;
        noOfQuestions = 0;

        timerText.setText( "30s" );
        scoreText.setText( "0/0" );
        result.setText( "" );

        playAgainButton.setVisibility( View.INVISIBLE );
        questionText.setVisibility( View.VISIBLE );
        timerText.setVisibility( View.VISIBLE );
        scoreText.setVisibility( View.VISIBLE );
        gridLayout.setVisibility( View.VISIBLE );

        generateQuestion();

        new CountDownTimer( 30100, 1000 ) {
            @Override
            public void onTick(long l) {
                timerText.setText( String.valueOf( l/1000 ) + "s" );

            }

            @Override
            public void onFinish() {

                result.setText( "Your Score is : " + Integer.toString( score ) + "/" + Integer.toString( noOfQuestions ) );
                gridLayout.setVisibility( View.INVISIBLE );
                questionText.setVisibility( View.INVISIBLE );
                timerText.setVisibility( View.INVISIBLE );
                scoreText.setVisibility( View.INVISIBLE );
                playAgainButton.setVisibility( View.VISIBLE );
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        startButton = (Button) findViewById( R.id.goButton );
        questionText = (TextView) findViewById( R.id.questionText );
        option0 = (Button) findViewById( R.id.option0 );
        option1 = (Button) findViewById( R.id.option1 );
        option2 = (Button) findViewById( R.id.option2 );
        option3 = (Button) findViewById( R.id.option3 );
        result = (TextView)findViewById( R.id.resultText );
        scoreText = (TextView) findViewById( R.id.scoreText );
        timerText = (TextView) findViewById( R.id.timerText );
        playAgainButton = (Button) findViewById( R.id.playAgainButton );
        gameRelativeLayout = (RelativeLayout) findViewById( R.id.gameRelativeLayout );
        gridLayout = (GridLayout) findViewById( R.id.gridLayout );

    }
}
