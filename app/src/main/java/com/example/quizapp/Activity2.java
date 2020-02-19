package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answer1;
    private Button answer2;
    private Button answer3;

    private String rightAnswer;
    private int rightAnswerCount=0;
    //the number of the question
    private int quizCount=1;
    //total number of questions
    static final private int QUIZ_COUNT=50;

    ArrayList<ArrayList<String>> qArray =new ArrayList<>();

    String qData[][]={
            //{"country","right answere","answer","answer"}
            {"Olanda","Amsterdam","Oslo","Copenhaga"},
            {"Andorra","Andorra la Vella","Ankara","Bratislava"},
            {"Turcia","Ankara","Atena","Berna"},
            {"Grecia","Atena","Dublin","Sofia"},
            {"Serbia","Belgrad","Berlin","Bratislava"},
            {"Germania","Berlin","Oslo","Liubliana"},
            {"Elvetia","Berna","Bratislava","Bruxelles"},
            {"Slovacia","Bratislava","Copenhaga","Berna"},
            {"Belgia","Buruxelles","Bratislava","Dublin"},
            {"Romania","Bucuresti","Budapesta","Sofia"},
            {"Ungaria","Budapesta","Lisabona","Sofia"},
            {"Republica Moldova","Chisinau","Kiev","Minsk"},
            {"Danemarca","Copenhaga","Dublin","Praga"},
            {"Irlanda","Dublin","Helsinki","Kiev"},
            {"Finlanda","Helsinki","Oslo","Sarajevo"},
            {"Ucraina","Kiev","Lisabona","Tirana"},
            {"Portugalia","Lisabona","Liubliana","Riga"},
            {"Slovenia","Liubliana","Oslo","Reykjavik"},
            {"United Kingdom","Londra","Luxemburg","Stockholm"},
            {"Luxemburg","Luxemburg","Kiev","Lisabona"},
            {"Spania","Madrid","Lisabona","Podgorica"},
            {"Belarus","Minsk","Monaco","Stockholm"},
            {"Monaco","Monaco","Nicosia","Riga"},
            {"Rusia","Moscova","Oslo","Praga"},
            {"Cipru","Nicosia","Reykjavik","Riga"},
            {"Norvegia","Oslo","Praga","Copenhaga"},
            {"Franta","Paris","Praga","Riga"},
            {"Muntenegru","Podgorica","Riga","Vilnius"},
            {"Cehia","Praga","Pristina","Stockholm"},
            {"Kosovo","Pristina","San Marino","Minsk"},
            {"Islanda","Reykjavik","Riga","Roma"},
            {"Armenia","Erevan","Nicosia","Minsk"},
            {"Letonia","Riga","Sarajevo","Stockholm"},
            {"Italia","Roma","Zagreb","Monaco"},
            {"San Marino","San Marino","Sofia","Zagreb"},
            {"Bosnia and Hertegovina","Sarajevo","Minsk","Valletta"},
            {"Macedonia","Skopje","Sofia","Zagreb"},
            {"Bulgaria","Sofia","Praga","Budapesta"},
            {"Suedia","Stockholm","Lisabona","Oslo"},
            {"Estonia","Tallinn","Vilnius","Riga"},
            {"Albania","Tirana","Pristina","Oslo"},
            {"Lichtenstein","Vaduz","Varsovia","Helsinki"},
            {"Malta","Valletta","Vatican","Skopje"},
            {"Polonia","Varsovia","Lisabona","Kiev"},
            {"Vatican","Vatican","Vaduz","Riga"},
            {"Austria","Viena","Valletta","Berlin"},
            {"Lituania","Vilnius","Zagreb","Berna"},
            {"Croatia","Zagreb","Andora","Sarajevo"},
            {"Georgia","Tibillisi","Tirana","Tallinn"},
            {"Azerbaijan","Baku","Lisabona","Vaduz"}



    };
      //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        countLabel=(TextView)findViewById(R.id.countLabel);
        questionLabel=(TextView)findViewById(R.id.questionLabel);
        answer1=(Button)findViewById(R.id.answere1);
        answer2 =(Button)findViewById(R.id.answere2);
        answer3 =(Button)findViewById(R.id.answere3);

//create array from data
        for(int i = 0; i< qData.length; i++){
            ArrayList<String>tmpArray=new ArrayList<>();
            tmpArray.add(qData[i][0]); //Country
            tmpArray.add(qData[i][1]);
            tmpArray.add(qData[i][2]);
            tmpArray.add(qData[i][3]);

            qArray.add(tmpArray);
        }
      showNextQuiz();


    }
    public void showNextQuiz(){
        //update every question
        countLabel.setText("Question "+quizCount);
        //generate random number between 0 and 50 - qArray
        Random random= new Random();
        int randomNum = random.nextInt(qArray.size());

        //one quiz
        ArrayList<String> quiz = qArray.get(randomNum);

        //set question
        questionLabel.setText(quiz.get(0));

        //set right answer
        rightAnswer =quiz.get(1);

        //remove the current question
        quiz.remove(0);

        //shuffle choices
        Collections.shuffle(quiz);

        //set choices
        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));

        //remove quiz from qArray
        qArray.remove(randomNum);

    }

    public void checkAnswer(View view){

               Button answerBtn=(Button)findViewById(view.getId());
               String btnText = answerBtn.getText().toString();

               String inregTitle;
               if(btnText.equals(rightAnswer)){
                   inregTitle="Correct!";
                   rightAnswerCount++;
               }else{
                   inregTitle="Wrong!";
               }
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
               builder.setTitle(inregTitle);
               builder.setMessage("Right answere is:" + rightAnswer);
               builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int i) {
                       if(quizCount==QUIZ_COUNT){
                           //Show result
                           Intent intent = new Intent(getApplicationContext(),ActivityRezult.class);
                           intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                           startActivity(intent);


                       }else{
                           quizCount++;
                           showNextQuiz();
                       }

                   }
               });
              builder.setCancelable(false);
              builder.show();
    }
}
