package com.example.myapplication.View;

import static com.example.myapplication.View.MainActivity.answeredFragment;
import static com.example.myapplication.View.MainActivity.fragment;
import static com.example.myapplication.View.MainActivity.main_menu_fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.myapplication.BufferClass;
import com.example.myapplication.MyTimer;
import com.example.myapplication.questions.QuestionInserter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.InfoSettingsBinding;
import com.example.myapplication.databinding.SingleplayerGameBinding;

public class MyFragment extends Fragment {
    public TextView question;
    private TextView playerNumber;
    private TextView numberOfQuestion;
    public TextView timer;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    Button[] options;
    private static TextView questionType;
    public MyTimer myTimer;
    private Button livesPlacehodler;
    ViewBinding binding;
    String intent;
    public static final String GAMEMODE_SINGLE = "SINGLEPLAYER";
    public static final String GAMEMODE_MULTIPLAYER = "MULTIPLAYER";
    public static final String GAMEMODE_INFO = "INFO";
    private QuestionInserter qi1;
    private QuestionInserter[] qis;
    Integer numberOfPlayers;
    Integer currentPlayer = 0;
    boolean isMP;
    boolean isLaunched;

    public void launchTest(String message) {
        intent = message;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!getIntent().equals(GAMEMODE_INFO)) {
            binding = SingleplayerGameBinding.inflate(inflater, container, false);
        } else {
            binding = InfoSettingsBinding.inflate(inflater, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
//        int gamemode=R.layout.singleplayer_game;
        int gamemode = getIntent().equals(GAMEMODE_SINGLE) ?
                R.layout.singleplayer_game : (getIntent().equals(GAMEMODE_MULTIPLAYER) ?
                R.layout.singleplayer_game : R.layout.info_settings);
        if (!BufferClass.isIsFileOpened()) {
            BufferClass.setQuestions();
            qi1 = new QuestionInserter(1);
            BufferClass.fileIsOpened();
        } else {
            BufferClass.clearAll();
            BufferClass.setQuestions();
            qi1 = new QuestionInserter(1);
        }
//        setContentView(gamemode);
        initView();
        setupView(gamemode, getIntent().equals(GAMEMODE_MULTIPLAYER));


    }

    private void initView() {
        isLaunched = true;
        option1 = getView().findViewById(R.id.firstOption);
        option2 = getView().findViewById(R.id.secondOption);
        option3 = getView().findViewById(R.id.thirdOption);
        option4 = getView().findViewById(R.id.fourthOption);
        options = new Button[]{option1, option2, option3, option4};
        questionType = getView().findViewById(R.id.type_of_question);
        numberOfQuestion = getView().findViewById(R.id.number_of_question);
        timer = getView().findViewById(R.id.timer);
        myTimer = new MyTimer();
        question = getView().findViewById(R.id.question);
        livesPlacehodler = getView().findViewById(R.id.button3);
        playerNumber = getView().findViewById(R.id.playerNumber);
    }

    @SuppressLint("ResourceAsColor")
    private void setupView(int layout, boolean isMP) {

        currentPlayer = 0;
        qis = null;
        this.isMP = isMP;
        if (layout == R.layout.singleplayer_game && !isMP) {
            if(myTimer.running){
                myTimer.stopWork();
            }
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

    public void setupText() {

        question.setText(getQi().actualQuestions.get(getQi().getNumbOfQuestion()).question);
        livesPlacehodler.setText(String.valueOf(getQi().getLives()));
//        QuestionInserter temp=getQi();
//        temp.actualQuestions.get(temp.getNumbOfQuestion()).mixOptions();
        for (int i = 0; i < getQi().actualQuestions.get(getQi().getNumbOfQuestion()).options.size(); i++) {
            options[i].setText(getQi().actualQuestions.get(getQi().getNumbOfQuestion()).options.get(i));

        }
        if (getQi().actualQuestions.get(getQi().getNumbOfQuestion()).isQuestion_torf()) {
            questionType.setText("Правда или ложь?");
            option3.setVisibility(View.INVISIBLE);
            option4.setVisibility(View.INVISIBLE);
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
        boolean b=true;
        if (answeredOption == getQi().actualQuestions.get(getQi().getNumbOfQuestion()).getTrueAnswer()) {
            getQi().answeredTrue();

            if (isMP) {
                nextPlayer();
            }
            try {
                setupText();
            } catch (IndexOutOfBoundsException e) {
                if (!isMP) {
                    showSinglePlayerWinMsg();
                } else {
                    nextPlayer();
                    try {
                        setupText();
                    } catch (IndexOutOfBoundsException err) {
                        show_Multiplayer_Win_Msg(getWinner());
                        b=false;
                    }
                }
            }
            finally {
                if(isMP&&b){
                    answeredFragment.showReadyScreen(currentPlayer+1);
                }
            }
            if(!isMP&&isLaunched){
                answeredFragment.showIsAnswerCorrect(true);
            }

        }
        else {
            getQi().answeredWrong();
            if(!isMP){
                if(getQi().lives==0){
                    show_Singleplayer_lost_msg();
                }
                else {
                    getQi().addNumbOfQuestion();
                    try {
                        setupText();
                    }
                    catch (IndexOutOfBoundsException e){
                        showSinglePlayerWinMsg();
                    }
                }
                if(isLaunched){
                    answeredFragment.showIsAnswerCorrect(false);
                }
            }
            else {
                if(getQi().getLives()==0){
                    getQi().playerLost();
                    numberOfPlayers--;
                    if(numberOfPlayers==1){
                        show_Multiplayer_Win_Msg(getWinner());
                    }
                }
                if(!getQi().lost){
                    getQi().addNumbOfQuestion();
                }
                nextPlayer();
                try {
                    setupText();
                } catch (IndexOutOfBoundsException e) {
                    show_Multiplayer_Win_Msg(getWinner());
                }
                if(isLaunched) {
                    answeredFragment.showIsAnswerCorrect(false);
                }
            }
        }

//            getQi().answeredTrue();
//            try {
//                getQi().addNumbOfQuestion();
//
//                if (isMP) {
//                    currentPlayer++;
//                    if (currentPlayer == qis.length) {
//                        currentPlayer = 0;
//                    }
//                    myTimer.stopWork();
//                    myTimer.startWork(90);
//                }
//                setupText();
//                if (isMP) {
//                    myTimer.stopWork();
//
//                    myTimer.startWork(90);
//                }
//            } catch (IndexOutOfBoundsException e) {
//                if (!isMP) {
//                    showSinglePlayerWinMsg();
////                    Toast.makeText(getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!" +
////                            "\nТы ответил правильно на " + (getQi().actualQuestions.size()-getQi().wrongAnswers) + " из " + getQi().actualQuestions.size() + " вопросов", Toast.LENGTH_LONG).show();
//                } else {
////                    numberOfPlayers--;
////                    qis[currentPlayer].playerWon();
//
//                    currentPlayer++;
//                    if (currentPlayer == qis.length) {
//                        currentPlayer = 0;
//                    }
//                    while (qis[currentPlayer].lost) {
//                        currentPlayer++;
//                        if (currentPlayer == qis.length) {
//                            currentPlayer = 0;
//                        }
//                    }
//
//                    try {
//                        setupText();
//                        myTimer.stopWork();
//                        myTimer.startWork(90);
//                    } catch (IndexOutOfBoundsException err) {
//                        QuestionInserter winner = new QuestionInserter();
//                        for (int i = 0; i < qis.length; i++) {
////                            Log.d("Who Won", String.valueOf(qi.won));
//                            if (qis[i].lost) {
//                                continue;
//                            } else if (winner.overallScore < qis[i].overallScore) {
//                                winner = qis[i];
//                            }
//                        }
//                        setAlertDialog(winner);
//
//
//                    }
//                }
//            }
//        } else {
//            try {
//                getQi().setLives(getQi().getLives() - 1);
//                getQi().answeredWrong();
//                Toast.makeText(getActivity().getApplicationContext(), "Этот ответ неправильный!", Toast.LENGTH_SHORT).show();
//                if (getQi().getLives() == 0) {
//                    if (!isMP) {
//                        launchMainMenu();
//                        Toast.makeText(getActivity().getApplicationContext(), "К сожалению, ты проиграл. Но не расстраивайся!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        numberOfPlayers--;
//                        qis[currentPlayer].playerLost();
//                        if (numberOfPlayers == 1) {
//                            QuestionInserter winner = new QuestionInserter();
//                            for (int i = 0; i < qis.length; i++) {
////                            Log.d("Who Won", String.valueOf(qi.won));
//                                if (qis[i].lost) {
//                                    continue;
//                                } else if (winner.overallScore < qis[i].overallScore) {
//                                    winner = qis[i];
//                                }
//                            }
//                            setAlertDialog(winner);
//
//
//                        }
//
//                    }
//                }
//                if (getQi().getLives() != 0) {
//                    getQi().addNumbOfQuestion();
//                }
//                if (isMP) {
//                    currentPlayer++;
//                    if (currentPlayer == qis.length) {
//                        currentPlayer = 0;
//                    }
//                    try {
//                        while (qis[currentPlayer].lost) {
//                            currentPlayer++;
//                            if (currentPlayer == qis.length) {
//                                currentPlayer = 0;
//                            }
//                        }
//                    } catch (IndexOutOfBoundsException ex) {
//                        currentPlayer = 0;
//                        while (qis[currentPlayer].lost) {
//                            currentPlayer++;
//                            if (currentPlayer == qis.length) {
//                                currentPlayer = 0;
//                            }
//                        }
//                    }
//                }
//                setupText();
//                if (isMP) {
//                    myTimer.stopWork();
//
//                    myTimer.startWork(90);
//                }
//            } catch (IndexOutOfBoundsException e) {
//                if (!isMP) {
//                    if (getQi().getLives() == 0) {
//                        launchMainMenu();
//                        Toast.makeText(getActivity().getApplicationContext(), "К сожалению, ты проиграл. Но не расстраивайся!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        showSinglePlayerWinMsg();
////                        Toast.makeText(getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!", Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    if (getQi().getLives() == 0) {
//                        numberOfPlayers--;
//                        qis[currentPlayer].playerLost();
//                    }
////                    else {
////                        numberOfPlayers--;
////                        qis[currentPlayer].playerWon();
////                    }
//
//                    currentPlayer++;
//                    if (currentPlayer == qis.length) {
//                        currentPlayer = 0;
//                    }
//                    try {
//                        while (qis[currentPlayer].lost) {
//                            currentPlayer++;
//                            if (currentPlayer == qis.length) {
//                                currentPlayer = 0;
//                            }
//                        }
//                    } catch (IndexOutOfBoundsException ex) {
//                        currentPlayer = 0;
//                        while (qis[currentPlayer].lost) {
//                            currentPlayer++;
//                            if (currentPlayer == qis.length) {
//                                currentPlayer = 0;
//                            }
//                        }
//                    }
//                    try {
//                        setupText();
//                        myTimer.stopWork();
//
//                        myTimer.startWork(90);
//                    } catch (IndexOutOfBoundsException err) {
//                        QuestionInserter winner = new QuestionInserter();
//                        for (int i = 0; i < qis.length; i++) {
////                            Log.d("Who Won", String.valueOf(qi.won));
//                            if (qis[i].lost) {
//                                continue;
//                            } else if (winner.overallScore < qis[i].overallScore) {
//                                winner = qis[i];
//                            }
//                        }
//                        setAlertDialog(winner);
//
//
////                        Toast.makeText(getApplicationContext(), "Многопользовательская игра завершена", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            }
//
//        }

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


    public void setupTimerText(int time) {
        timer.setText("" + time);
    }

    private void show_Multiplayer_Win_Msg(QuestionInserter winner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Итоги игры")
                .setCancelable(true)
                .setMessage("Выиграл игрок под номером " + winner.getRealNumber() + "\nСо счётом в " + winner.overallScore
                        + " очков"
                        + "\nПравильных ответов: " + (winner.actualQuestions.size() - winner.wrongAnswers) + " из " + winner.actualQuestions.size()
                        + "\nРезультаты остальных игроков:\n" + getResults(winner))
                .setNeutralButton("ОК", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog ad = builder.create();
        ad.show();
//        Toast.makeText(getActivity().getApplicationContext(), "Многопользовательская игра завершена", Toast.LENGTH_LONG).show();

        launchMainMenu();
//                        ad.show();
    }

    private void showSinglePlayerWinMsg() {
//        Toast.makeText(getActivity().getApplicationContext(), "Ура! Ты выиграл! Молодец!\nТы набрал " + getQi().overallScore + " очков!" +
//                "\nТы ответил правильно на " + (getQi().actualQuestions.size() - getQi().wrongAnswers) + " из " + getQi().actualQuestions.size() + " вопросов", Toast.LENGTH_LONG).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Итоги игры")
                .setCancelable(true)
                .setMessage("Ура! Ты выиграл! Молодец!" + "\nТвой счёт: " + getQi().overallScore
                        + " очков"
                        + "\nТы дал " + (getQi().actualQuestions.size() - getQi().wrongAnswers) + " из " + getQi().actualQuestions.size() + " правильных ответов ")
                .setNeutralButton("ОК", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog ad = builder.create();
        ad.show();
        launchMainMenu();
    }
    private void show_Singleplayer_lost_msg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Итоги игры")
                .setCancelable(true)
                .setMessage("К сожалению, ты проиграл. Попробуй ещё в следующий раз!" + "\nТвой счёт: " + getQi().overallScore
                        + " очков"
                        + "\nТы дал " + (getQi().trueAnswers) + " из " + getQi().actualQuestions.size() + " правильных ответов ")
                .setNeutralButton("ОК", (dialogInterface, i) -> dialogInterface.cancel());
        AlertDialog ad = builder.create();
        ad.show();
        launchMainMenu();
    }

    public String getIntent() {
        return intent;
    }

    public void launchMainMenu() {
        isLaunched = false;
        if (myTimer.running) {
            myTimer.stopWork();

        }
        getParentFragmentManager().beginTransaction()
                .detach(fragment)
                .show(main_menu_fragment)
                .commitNow();
    }

    private String getResults(QuestionInserter winner) {
        String result = "";
        for (int i = 0; i < qis.length; i++) {
            if (!qis[i].equals(winner)) {
                result += "\nИгрок номер " + qis[i].realNumber + (qis[i].lost ? " проиграл" : (qis[i].overallScore == winner.overallScore ? " победил" : "")) + ":";
                result += " Счёт: " + qis[i].overallScore + " очков.";
                if(!qis[i].lost) {
                    result += "\nПравильных ответов: " + (qis[i].actualQuestions.size() - qis[i].wrongAnswers) + " из " + qis[i].actualQuestions.size();
                }
                else {
                    result += "\nПравильных ответов: " + (qis[i].trueAnswers) + " из " + qis[i].actualQuestions.size();
                }
            }
        }
        return result;
    }

    private void resetTimer(int time) {
        myTimer.stopWork();
        myTimer.startWork(time);
    }

    public void resetTimer() {
        myTimer.stopWork();
        myTimer.startWork(90);
    }

    private void nextPlayer() {

        currentPlayer++;
        try {
            qis[currentPlayer].check();
        }
        catch (IndexOutOfBoundsException e){
            currentPlayer=0;
        }
        finally {
            try {
                checkIfLost();
            }
            catch (IndexOutOfBoundsException e){
                nextPlayer();
            }
        }

//        if (currentPlayer == qis.length) {
//            currentPlayer = 0;
//        }
//        if(qis[currentPlayer].lost){
//            currentPlayer++;
//            if (currentPlayer == qis.length) {
//                currentPlayer = 0;
//            }
//        }
    }

    private QuestionInserter getWinner() {
        QuestionInserter winner = new QuestionInserter();
        for (QuestionInserter qi : qis) {
            if (winner.overallScore < qi.overallScore) {
                winner = qi;
            }
        }
        return winner;
    }
    private void checkIfLost(){
        if(qis[currentPlayer].lost){
            currentPlayer++;
        }
        qis[currentPlayer].check();
    }



}