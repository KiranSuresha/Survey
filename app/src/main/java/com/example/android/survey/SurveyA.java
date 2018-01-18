package com.example.android.survey;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyA extends AppCompatActivity {

    private final static String LOG_TAG = SurveyA.class.getSimpleName();

    private DatabaseReference mAnswers;
    private RadioGroup question1;
    private RadioGroup question2;
    private RadioGroup question3;
    private RadioGroup question4;
    private DatabaseReference mQuestions;
    private FirebaseDatabase mFirebaseInstance;
    private TextInputEditText enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_activity);

        question1 = findViewById(R.id.s1_q1_rg1);
        question2 = findViewById(R.id.s1_q1_rg2);
        question3 = findViewById(R.id.s1_q1_rg3);
        question4 = findViewById(R.id.s1_q1_rg4);
        final TextView q1 = findViewById(R.id.s1_q1);
        final TextView q2 = findViewById(R.id.s1_q2);
        final TextView q3 = findViewById(R.id.s1_q3);
        final TextView q4 = findViewById(R.id.s1_q4);
        enteredName = findViewById(R.id.userName);

        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enteredName != null && enteredName.length() > 0) {
                    int question1Id = question1.getCheckedRadioButtonId();
                    String val1 = null;
                    switch (question1Id) {
                        case R.id.s1_q1_rg1_rb1:
                            val1 = "Meat";
                            break;
                        case R.id.s1_q1_rg1_rb2:
                            val1 = "Herbs";
                            break;
                        case R.id.s1_q1_rg1_rb3:
                            val1 = "Mushrooms";
                            break;
                        case R.id.s1_q1_rg1_rb4:
                            val1 = "Egg";
                            break;
                    }
                    int question2Id = question2.getCheckedRadioButtonId();
                    String val2 = null;
                    switch (question2Id) {
                        case R.id.s1_q1_rg2_rb1:
                            val2 = "Earth";
                            break;
                        case R.id.s1_q1_rg2_rb2:
                            val2 = "Mars";
                            break;
                        case R.id.s1_q1_rg2_rb3:
                            val2 = "Venus";
                            break;
                        case R.id.s1_q1_rg2_rb4:
                            val2 = "Moon";
                            break;
                    }

                    int question3Id = question3.getCheckedRadioButtonId();
                    String val3 = null;
                    switch (question3Id) {
                        case R.id.s1_q1_rg3_rb1:
                            val3 = "Not cool";
                            break;
                        case R.id.s1_q1_rg3_rb2:
                            val3 = "Average";
                            break;
                        case R.id.s1_q1_rg3_rb3:
                            val3 = "Super Cool";
                            break;
                        case R.id.s1_q1_rg3_rb4:
                            val3 = "Discreet";
                            break;
                    }
                    int question4Id = question4.getCheckedRadioButtonId();
                    String val4 = null;
                    switch (question4Id) {
                        case R.id.s1_q1_rg4_rb1:
                            val4 = "Simplicity";
                            break;
                        case R.id.s1_q1_rg4_rb2:
                            val4 = "Complex";
                            break;
                        case R.id.s1_q1_rg4_rb3:
                            val4 = "Concurrency";
                            break;
                        case R.id.s1_q1_rg4_rb4:
                            val4 = "JLT";
                            break;
                    }

                    mFirebaseInstance = FirebaseDatabase.getInstance();
                    mQuestions = mFirebaseInstance.getReference("Questions");
                    mQuestions.child("Question 1").setValue(q1.getText().toString());
                    mQuestions.child("Question 2").setValue(q2.getText().toString());
                    mQuestions.child("Question 3").setValue(q3.getText().toString());
                    mQuestions.child("Question 4").setValue(q4.getText().toString());


                    mAnswers = mFirebaseInstance.getReference("Answers");
                    DatabaseReference childRef = mAnswers.child(enteredName.getText().toString());
                    childRef.child("Question 1").setValue(val1);
                    childRef.child("Question 2").setValue(val2);
                    childRef.child("Question 3").setValue(val3);
                    childRef.child("Question 4").setValue(val4);

                    finish();
                } else {
                    Toast.makeText(SurveyA.this, "Please enter the name!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}