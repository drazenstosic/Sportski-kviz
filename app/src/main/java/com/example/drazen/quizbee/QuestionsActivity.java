package com.example.vikasojha.quizbee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
                            "Tko je najbolji strijelac u povijesti hrvatske nogometne reprezentacije?",
                            "Na kojem mjestu je završila hrvatska košarkaška reprezentacija na Olimpijskim Igrama u Barceloni 1992.?",
                            "Koje godine je Goran Ivanišević osvojio Wimbledon?",
                            "'U svojoj reprezentativnoj karijeri sam zabio samo 2 gola, i to oba Hrvatskoj u polufinalu Svjetskog Nogometnog Prvenstva 1998. Kako se zovem?' ",
                            "Nadopuni rečenicu! Marin Čilić je 2014. godine osvojio _______, svoj jedini Grand Slam naslov.",
                            "U kojem gradu je osnovan hrvatski nogometni klub Hajduk Split",
                            "Tko od navedenih sportaša je osvojio najviše zlatnih medalja na Olimpijskim Igrama?",
                            "Koji hrvatski nogometni klub je jedini u povijesti Hrvatske osvojio europski naslov? ",
                            "Koje veliko natjecanje hrvatska rukometna reprezentacija nije nikada osvojila?",
                            "Iz kojeg grada dolazi Jug, najuspješniji hrvatski vaterpolski klub?"
                         };
    String answers[] = {"Davor Šuker","Na drugom mjestu.","2001.","Lillian Thuram","US Open","U Pragu.","Janica Kostelić","Dinamo Zagreb","Europsko prvenstvo","Iz Dubrovnika."};
    String opt[] = {
                    "Davor Šuker","Eduardo Da Silva","Mario Mandžukić","Ivan Perišić",
                    "Na prvom mjestu.","Na drugom mjestu.","Na trecem mjestu.","Nije uopce nastupila.",
                    "2003.","2002.","2001.","2003.",
                    "Bixente Lizarazu","Lillian Thuram","Thierry Henry","Fabian Barthez",
                    "Australian Open","US Open","Wimbledon","Roland Garros",
                    "U Splitu.","U Rimu.","U Pragu.","U Zadru.",
                    "Janica Kostelić","Ivica Kostelić","Sandra Perković","Blanka Vlašić",
                    "Hajduk Split","HNK Rijeka","NK Osijek","Dinamo Zagreb",
                    "Olimpijske Igre","Svjetsko prvenstvo","Europsko prvenstvo","Osvojila je svako od navedenih natjecanja.",
                     "Iz Dubrovnika.","Iz Šibenika.","Iz Splita","S Hvara."
                   };
    int flag=0, counter=0, questioNum=1;

    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        TextView numQuestion = (TextView)findViewById(R.id.textView5);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Ime natjecatelja: User");
        else
        textView.setText("Ime natjecatelja: " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        numQuestion.setText("Pitanje: " + String.valueOf(questioNum) + "/10");
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Morate odabrati jedan od odgovora!", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Bravo!", Toast.LENGTH_SHORT).show();
                    counter++;
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Krivo! Točan odgovor: " + answers[counter], Toast.LENGTH_SHORT).show();
                    counter++;
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                    questioNum++;
                    numQuestion.setText("Pitanje: " + String.valueOf(questioNum) + "/10");
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

}