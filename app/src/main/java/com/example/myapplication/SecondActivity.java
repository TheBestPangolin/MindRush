package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {
    boolean isMP;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    Button[] options;
    private static TextView questionType;
    private Button livesPlacehodler;
    Integer currentPlayer = 0;
    private TextView numberOfQuestion;
    @SuppressLint("StaticFieldLeak")
    public static TextView timer;
    public static MyTimer myTimer;
    @SuppressLint("StaticFieldLeak")
    public static TextView question;
    private TextView playerNumber;
    public static final String GAMEMODE_SINGLE = "gamemode_single";
    public static final String GAMEMODE_MULTIPLAYER = "gamemode_MULTIPLAYER";
    public static final String GAMEMODE_INFO = "gamemode_INFO";
    private QuestionInserter qi1;


    private QuestionInserter[] qis;
    Integer numberOfPlayers = 2;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public static Intent launchTest(Context context, String message) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra((message.equals("SINGLEPLAYER") ?
                GAMEMODE_SINGLE : (message.equals("MULTIPLAYER") ?
                GAMEMODE_MULTIPLAYER : GAMEMODE_INFO)), message);
        return intent;
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        int gamemode = getIntent().hasExtra(GAMEMODE_SINGLE) ?
                R.layout.singleplayer_game : (getIntent().hasExtra(GAMEMODE_MULTIPLAYER) ?
                R.layout.singleplayer_game : R.layout.info_settings);
        if (!BufferClass.isIsFileOpened()) {
            fileOpener();
            BufferClass.setQuestions();
            qi1 = new QuestionInserter(1);
            BufferClass.fileIsOpened();
        } else {
            BufferClass.clearAll();
            BufferClass.setQuestions();
            qi1 = new QuestionInserter(1);
        }
        setContentView(gamemode);

        initView();
        setupView(gamemode, getIntent().hasExtra(GAMEMODE_MULTIPLAYER));
    }

    private void initView() {

        option1 = findViewById(R.id.firstOption);
        option2 = findViewById(R.id.secondOption);
        option3 = findViewById(R.id.thirdOption);
        option4 = findViewById(R.id.fourthOption);
        options = new Button[]{option1, option2, option3, option4};
        questionType = findViewById(R.id.type_of_question);
        numberOfQuestion = findViewById(R.id.number_of_question);
        timer = findViewById(R.id.timer);
        myTimer  = new MyTimer();
        question = findViewById(R.id.question);
        livesPlacehodler = findViewById(R.id.button3);
        playerNumber = findViewById(R.id.playerNumber);
    }

    @SuppressLint("ResourceAsColor")
    private void setupView(int layout, boolean isMP) {
        this.isMP = isMP;
        if (layout == R.layout.singleplayer_game && !isMP) {
            playerNumber.setText("");
            qis = new QuestionInserter[1];
            qis[0] = qi1;
            setupText();
            timer.setText("");
            option1.setOnClickListener(view -> {
                nextQuestion(1, false);
            });
            option2.setOnClickListener(view -> {
                nextQuestion(2, false);
            });
            option3.setOnClickListener(view -> {
                nextQuestion(3, false);
            });
            option4.setOnClickListener(view -> {
                nextQuestion(4, false);
            });
        } else if (layout == R.layout.singleplayer_game && isMP) {
            qis = setPlayers();
            setupText();
            myTimer.startWork(90);
            option1.setOnClickListener(view -> {
                nextQuestion(1, true);
            });
            option2.setOnClickListener(view -> {
                nextQuestion(2, true);
            });
            option3.setOnClickListener(view -> {
                nextQuestion(3, true);
            });
            option4.setOnClickListener(view -> {
                nextQuestion(4, true);
            });
            for (int i = 0; i < options.length; i++) {
                options[i].setBackgroundColor(R.color.buttons);
            }



        } else if (layout == R.layout.info_settings) {

        }

    }

    public void fileOpener() {
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));
            String readline;
            while ((readline = br.readLine()) != null) {
                BufferClass.addString(readline);
            }
            br.close();
        } catch (IOException e) {
            Log.d("da b", "why");
        }
    }



    public void setupText() {

        question.setText(getQi().actualQuestions.get(getQi().getNumbOfQuestion()).question);
        livesPlacehodler.setText(String.valueOf(getQi().getLives()));

        for (int i = 0; i < getQi().actualQuestions.get(getQi().getNumbOfQuestion()).options.length; i++) {
            options[i].setText(getQi().actualQuestions.get(getQi().getNumbOfQuestion()).options[i]);

        }
        if (getQi().actualQuestions.get(getQi().getNumbOfQuestion()).isQuestion_torf()) {
            questionType.setText("Правда или ложь?");
            option3.setVisibility(View.INVISIBLE);
            option4.setVisibility(View.INVISIBLE);
        } else if (getQi().actualQuestions.get(getQi().getNumbOfQuestion()).getQuestion().equals("Кто изображён на фотографии?")) {
            questionType.setText("Кто на фото?");
            option3.setVisibility(View.VISIBLE);
            option4.setVisibility(View.VISIBLE);
        } else {
            questionType.setText("Обычный вопрос");
            option3.setVisibility(View.VISIBLE);
            option4.setVisibility(View.VISIBLE);
        }
        if (isMP) {
            playerNumber.setText("Отвечает игрок №\n" + String.valueOf(currentPlayer + 1));
        }
        numberOfQuestion.setText("Номер вопроса: " + String.valueOf(getQi().getNumbOfQuestion() + 1));
    }


    public void nextQuestion(int answeredOption, boolean isMP) {
        if (answeredOption == getQi().actualQuestions.get(getQi().getNumbOfQuestion()).getTrueAnswer()) {
            getQi().answeredTrue();
            try {
                getQi().addNumbOfQuestion();

                if (isMP) {
                    currentPlayer++;
                    if (currentPlayer == qis.length) {
                        currentPlayer = 0;
                    }
                    myTimer.stopWork();
                    myTimer.startWork(90);
                }
                setupText();
                if(isMP) {
                    myTimer.stopWork();

                    myTimer.startWork(90);
                }
            } catch (IndexOutOfBoundsException e) {
                if (!isMP) {
                    Intent intent = MainActivity.launchTest(SecondActivity.this);
                    startActivity(intent);
                    showSinglePlayerWinToast();
//                    Toast.makeText(getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!" +
//                            "\nТы ответил правильно на " + (getQi().actualQuestions.size()-getQi().wrongAnswers) + " из " + getQi().actualQuestions.size() + " вопросов", Toast.LENGTH_LONG).show();
                } else {
//                    numberOfPlayers--;
//                    qis[currentPlayer].playerWon();

                    currentPlayer++;
                    if (currentPlayer == qis.length) {
                        currentPlayer = 0;
                    }
                    while (qis[currentPlayer].lost) {
                        currentPlayer++;
                        if (currentPlayer == qis.length) {
                            currentPlayer = 0;
                        }
                    }

                    try {
                        setupText();
                        myTimer.stopWork();
                        myTimer.startWork(90);
                    } catch (IndexOutOfBoundsException err) {
                        QuestionInserter winner = new QuestionInserter();
                        for (int i = 0; i < qis.length; i++) {
//                            Log.d("Who Won", String.valueOf(qi.won));
                            if (qis[i].lost) {
                                qis[i] = null;
                            } else if (winner.overallScore < qis[i].overallScore) {
                                winner = qis[i];
                            }
                        }
                        setAlertDialog(winner);


                    }
                }
            }
        } else {
            try {
                getQi().setLives(getQi().getLives() - 1);
                getQi().answeredWrong();
                Toast.makeText(getApplicationContext(), "Этот ответ неправильный!", Toast.LENGTH_SHORT).show();
                if (getQi().getLives() == 0) {
                    if (!isMP) {
                        Intent intent = MainActivity.launchTest(SecondActivity.this);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "К сожалению, ты проиграл. Но не расстраивайся!", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfPlayers--;
                        qis[currentPlayer].playerLost();
                        if (numberOfPlayers == 1) {
                            QuestionInserter winner = new QuestionInserter();
                            for (int i = 0; i < qis.length; i++) {
//                            Log.d("Who Won", String.valueOf(qi.won));
                                if (qis[i].lost) {
                                    qis[i] = null;
                                } else if (winner.overallScore < qis[i].overallScore) {
                                    winner = qis[i];
                                }
                            }
                            setAlertDialog(winner);


                        }

                    }
                }
                if (getQi().getLives() != 0) {
                    getQi().addNumbOfQuestion();
                }
                if (isMP) {
                    currentPlayer++;
                    if (currentPlayer == qis.length) {
                        currentPlayer = 0;
                    }
                    try {
                        while (qis[currentPlayer].lost) {
                            currentPlayer++;
                            if (currentPlayer == qis.length) {
                                currentPlayer = 0;
                            }
                        }
                    } catch (IndexOutOfBoundsException ex) {
                        currentPlayer = 0;
                        while (qis[currentPlayer].lost) {
                            currentPlayer++;
                            if (currentPlayer == qis.length) {
                                currentPlayer = 0;
                            }
                        }
                    }
                }
                setupText();
                if(isMP) {
                    myTimer.stopWork();

                    myTimer.startWork(90);
                }
            } catch (IndexOutOfBoundsException e) {
                if (!isMP) {
                    Intent intent = MainActivity.launchTest(SecondActivity.this);
                    startActivity(intent);
                    if (getQi().getLives() == 0) {
                        Toast.makeText(getApplicationContext(), "К сожалению, ты проиграл. Но не расстраивайся!", Toast.LENGTH_SHORT).show();
                    } else {
                        showSinglePlayerWinToast();
//                        Toast.makeText(getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (getQi().getLives() == 0) {
                        numberOfPlayers--;
                        qis[currentPlayer].playerLost();
                    }
//                    else {
//                        numberOfPlayers--;
//                        qis[currentPlayer].playerWon();
//                    }

                    currentPlayer++;
                    if (currentPlayer == qis.length) {
                        currentPlayer = 0;
                    }
                    try {
                        while (qis[currentPlayer].lost) {
                            currentPlayer++;
                            if (currentPlayer == qis.length) {
                                currentPlayer = 0;
                            }
                        }
                    } catch (IndexOutOfBoundsException ex) {
                        currentPlayer = 0;
                        while (qis[currentPlayer].lost) {
                            currentPlayer++;
                            if (currentPlayer == qis.length) {
                                currentPlayer = 0;
                            }
                        }
                    }
                    try {
                        setupText();
                        myTimer.stopWork();

                        myTimer.startWork(90);
                    } catch (IndexOutOfBoundsException err) {
                        QuestionInserter winner = new QuestionInserter();
                        for (int i = 0; i < qis.length; i++) {
//                            Log.d("Who Won", String.valueOf(qi.won));
                            if (qis[i].lost) {
                                qis[i] = null;
                            } else if (winner.overallScore < qis[i].overallScore) {
                                winner = qis[i];
                            }
                        }
                        setAlertDialog(winner);



//                        Toast.makeText(getApplicationContext(), "Многопользовательская игра завершена", Toast.LENGTH_LONG).show();
                    }

                }
            }

        }
    }

    public QuestionInserter getQi() {
        return qis[currentPlayer];
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    private QuestionInserter[] setPlayers() {
        QuestionInserter[] questionInserters = new QuestionInserter[numberOfPlayers];
        questionInserters[0] = qi1;
        for (int i = 1; i < numberOfPlayers; i++) {
            questionInserters[i] = new QuestionInserter(i + 1);
        }
        return questionInserters;
    }



    public static void setupTimerText(int time) {
        timer.setText(""+ time);
    }
    private void setAlertDialog(QuestionInserter winner){
        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Итоги игры")
                .setCancelable(true)
                .setMessage("Выиграл игрок под номером " + winner.getRealNumber() + "\nСо счётом в " + winner.overallScore
                        + "\nРезультаты остальных игроков:\n")
                .setNeutralButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                        Intent intent = MainActivity.launchTest(SecondActivity.this);
                        startActivity(intent);
                    }
                }).setView(R.layout.singleplayer_game);
        AlertDialog ad=builder.create();
        ad.show();
//                        ad.show();
    }
    private void showSinglePlayerWinToast(){
        Toast.makeText(getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!" +
                "\nТы ответил правильно на " + (getQi().actualQuestions.size()-getQi().wrongAnswers) + " из " + getQi().actualQuestions.size() + " вопросов", Toast.LENGTH_LONG).show();
    }
}
