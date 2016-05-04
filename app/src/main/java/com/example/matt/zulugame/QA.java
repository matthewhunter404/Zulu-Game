package com.example.matt.zulugame;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
        import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by matt on 2016/04/15.
 */
public class QA extends Fragment {
    ArrayAdapter<String> mAnswerAdapter;
    final int displaySize=5; //The number of possible answers given to the player at any one time
    final int QASize=15;    //The overall number of Question/Answer pairs in the database
    int score=0;            //The score
    String displayQuestion; //Stores the current question
    String displayAnswer;   //Stores the current correct answe
    String displayAnswers[]= new String[displaySize]; //Stores the current answers displayed on the screen
    HistoryQueue pastQuestions=new HistoryQueue(5); //This will keep track of recently asked questions so that the samequestion isn't asked repeatedly

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //Lets create two matching sets of questions and answers.
        final String[] questionsMaster= {
                "What is \"I Thank You\" in IsiZulu?",
                "What is \"Hello\" in IsiZulu?",
                "What is \"Goodbye\" or \"Stay Well\" in IsiZulu?",
                "What is \"Goodbye\" or \"Go Well\" in IsiZulu?",
                "What is \"No\" in IsiZulu?",
                "What is \"Yes\" in IsiZulu?",
                "What is \"I Walk\" in IsiZulu?",
                "What is \"I Run\" in IsiZulu?",
                "What is \"I Eat\" in IsiZulu?",
                "What is \"I Think\" in IsiZulu?",
                "What is \"Mountain\" in IsiZulu?",
                "What is \"Grass\" in IsiZulu?",
                "What is \"Sky\" in IsiZulu?",
                "What is \"Boy\" or \"Sun\" in IsiZulu?",
                "What is \"Girl\" or \"Daughter\" in IsiZulu?"
        };
        final String[] answersMaster= {
                "Ngiyabonga",
                "Sawubona",
                "Hlale Kahle",
                "Hamba Kahle",
                "Cha",
                "Yebo",
                "Ngiyahamba",
                "Ngigijima",
                "Ngidla",
                "Ngiyacabanga",
                "Intaba",
                "Isikhotha",
                "Isibhakabhaka",
                "Umfana",
                "intombazane"
        };
        //Set the Question and answer options, as well as the various handleson viewobjects
        setQuestion(questionsMaster,answersMaster);
        mAnswerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.answers_list_item, R.id.answers_list_item_textview, displayAnswers);
        View rootView = inflater.inflate(R.layout.qa_fragment,container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.AnswerList);
        listView.setAdapter(mAnswerAdapter);
        final TextView questionText= (TextView) rootView.findViewById(R.id.Question);
        questionText.setText("Question: " + displayQuestion);
        final TextView Pointtext = (TextView) rootView.findViewById(R.id.Points);
        //Sets the onclicklistenerto respond to list item selections by the user.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String Answer = mAnswerAdapter.getItem(position);
                if (Answer == displayAnswer) {
                    setQuestion(questionsMaster, answersMaster); //Sets the nextquestion
                    questionText.setText("Question: " + displayQuestion); //Displays the question
                    mAnswerAdapter.notifyDataSetChanged();      //updates the list adapter
                    score++;                                    //updates the score
                    Pointtext.setText("Score: "+score);
                }
                else{
                    score--;                                    //updates the score
                    Pointtext.setText("Score: "+score);
                }
            }
        });

        return rootView;
    }
    //the setQuestion method is passed a matching pair of questions and answers. It will use these to update the displayQuestion, displayAnswer, displayAnswers variables
    public void setQuestion(String[] questions,String[] answers) {
        int alreadySelected[]= new int[displaySize];
        Random randomGenerator = new Random();
        int randomInt=0;
        //first we generate fake answers
        int i = 0;
        while(i<displaySize){
            randomInt = randomGenerator.nextInt(QASize);
            if (checkIfUnique(alreadySelected,randomInt)==true){
                alreadySelected[i]=randomInt;
                displayAnswers[i]=answers[randomInt];
                i++;
            }
        }
        //Now lets generate the actual answer/question pair and insert a real answer over one of the fake answers
        boolean haveAnswer=false;
        while (haveAnswer==false){
            randomInt = randomGenerator.nextInt(QASize);             //select a QA index
            //Then check to see if it's already a fake answer or has already been selected
            if (checkIfUnique(alreadySelected,randomInt)==true){
                if (pastQuestions.checkItemHistory(randomInt)==true){
                    haveAnswer=true;
                }
            }
        }
        int randomInt2=randomGenerator.nextInt(displaySize);
        displayQuestion=questions[randomInt];
        displayAnswer=answers[randomInt];
        displayAnswers[randomInt2]=answers[randomInt];
        pastQuestions.add(randomInt);
        //String quick=answers[randomInt] +" was assigned to pos "+Integer.toString(randomInt2)+". "+"displayAnswers is "+displayAnswers[randomInt2];
        //Toast.makeText(getActivity(), quick, Toast.LENGTH_SHORT).show();
        //ListView listView = (ListView) passedrootview.findViewById(R.id.AnswerList);
        //listView.setAdapter(mAnswerAdapter);
        //alreadySelected[randomInt2]=randomInt;
    }

    public boolean checkIfUnique(int[] array,int number){
        boolean unique=true;
        for (int i=0; i<array.length;i++){
            if(array[i]==number) {
            unique=false;
            }
        }
        Utility.bla();
        return unique;
    }

}
