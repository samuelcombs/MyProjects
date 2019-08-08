package com.sam.samcombstictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView gameGrid[][] = new TextView[3][3];
    private TextView text;
    private int turn = 0;
    private boolean win = false;
    private int board[][] = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameGrid[0][0] = findViewById(R.id.box1);
        gameGrid[0][1] = findViewById(R.id.box2);
        gameGrid[0][2] = findViewById(R.id.box3);
        gameGrid[1][0] = findViewById(R.id.box4);
        gameGrid[1][1] = findViewById(R.id.box5);
        gameGrid[1][2] = findViewById(R.id.box6);
        gameGrid[2][0] = findViewById(R.id.box7);
        gameGrid[2][1] = findViewById(R.id.box8);
        gameGrid[2][2] = findViewById(R.id.box9);

        text = findViewById(R.id.textBox);

        Button newGameButton = findViewById(R.id.newGame);
        newGameButton.setOnClickListener(this);

        // set each button to listen for click
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < gameGrid[x].length; y++) {
                gameGrid[x][y].setOnClickListener(this);
            }
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGame:
                turn = 0;
                win = false;
                clearBoard();
                Random r = new Random();
                int x = r.nextInt(2);
                if (x == 0) {
                    text.setText(R.string.comp_went);
                    updateComputer();
                } else {
                    text.setText(R.string.you_go);
                }
                break;
            default:
                if (!win) {
                    text.setText("");
                    updatePlayer(v);
                }
                break;
        }
    }
    private void clearBoard(){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < gameGrid[x].length; y++) {
                gameGrid[x][y].setText("");
                board[x][y] = 0;
            }
        }
    }

    private void updatePlayer(View v){
        switch (v.getId()) {
            case R.id.box1:
                if (board[0][0] == 0) {
                    gameGrid[0][0].setText("X");
                    board[0][0] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box2:
                if (board[0][1] == 0) {
                    gameGrid[0][1].setText("X");
                    board[0][1] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box3:
                if (board[0][2] == 0) {
                    gameGrid[0][2].setText("X");
                    board[0][2] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box4:
                if (board[1][0] == 0) {
                    gameGrid[1][0].setText("X");
                    board[1][0] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box5:
                if (board[1][1] == 0) {
                    gameGrid[1][1].setText("X");
                    board[1][1] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box6:
                if (board[1][2] == 0) {
                    gameGrid[1][2].setText("X");
                    board[1][2] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box7:
                if (board[2][0] == 0) {
                    gameGrid[2][0].setText("X");
                    board[2][0] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box8:
                if (board[2][1] == 0) {
                    gameGrid[2][1].setText("X");
                    board[2][1] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            case R.id.box9:
                if (board[2][2] == 0) {
                    gameGrid[2][2].setText("X");
                    board[2][2] = 1;
                    checkWin("X");
                    if(turn<9)updateComputer();
                }
                break;
            default:
                break;
        }
    }

    private void updateComputer() {
        int row1 = board[0][0] + board[0][1] + board[0][2];
        int row2 = board[1][0] + board[1][1] + board[1][2];
        int row3 = board[2][0] + board[2][1] + board[2][2];
        int column1 = board[0][0] + board[1][0] + board[2][0];
        int column2 = board[0][1] + board[1][1] + board[2][1];
        int column3 = board[0][2] + board[1][2] + board[2][2];
        int diag1 = board[0][0] + board[1][1] + board[2][2];
        int diag2 = board[2][0] + board[1][1] + board[0][2];

        // value of -2 means computer can win
        // value of 2 means player can win

        if (board[1][1] == 0){
            gameGrid[1][1].setText("O");
            board[1][1] = -1;
        } else if (row1 == -2) {
            for (int y = 0; y < 3; y++){
                if (board[0][y] == 0) {
                    gameGrid[0][y].setText("O");
                    board[0][y] = -1;
                    break;
                }
            }
        } else if (row2 == -2) {
            for (int y = 0; y < 3; y++){
                if (board[1][y] == 0) {
                    gameGrid[1][y].setText("O");
                    board[1][y] = -1;
                    break;
                }
            }
        } else if (row3 == -2) {
            for (int y = 0; y < 3; y++){
                if (board[2][y] == 0) {
                    gameGrid[2][y].setText("O");
                    board[2][y] = -1;
                    break;
                }
            }
        } else if (column1 == -2) {
            for (int x = 0; x < 3; x++){
                if (board[x][0] == 0) {
                    gameGrid[x][0].setText("O");
                    board[x][0] = -1;
                    break;
                }
            }
        } else if (column2 == -2) {
            for (int x = 0; x < 3; x++){
                if (board[x][1] == 0) {
                    gameGrid[x][1].setText("O");
                    board[x][1] = -1;
                    break;
                }
            }
        } else if (column3 == -2) {
            for (int x = 0; x < 3; x++){
                if (board[x][2] == 0) {
                    gameGrid[x][2].setText("O");
                    board[x][2] = -1;
                    break;
                }
            }
        } else if (diag1 == -2){
            for (int x = 0; x < 3; x++){
                if (board[x][x] == 0) {
                    gameGrid[x][x].setText("O");
                    board[x][x] = -1;
                    break;
                }
            }
        } else if (diag2 == -2){
            for (int x = 0; x < 3; x++){
                if (board[2-x][x] == 0) {
                    gameGrid[2-x][x].setText("O");
                    board[2-x][x] = -1;
                    break;
                }
            }
        } else if (row1 == 2) {
            for (int y = 0; y < 3; y++){
                if (board[0][y] == 0) {
                    gameGrid[0][y].setText("O");
                    board[0][y] = -1;
                    break;
                }
            }
        } else if (row2 == 2) {
            for (int y = 0; y < 3; y++){
                if (board[1][y] == 0) {
                    gameGrid[1][y].setText("O");
                    board[1][y] = -1;
                    break;
                }
            }
        } else if (row3 == 2) {
            for (int y = 0; y < 3; y++){
                if (board[2][y] == 0) {
                    gameGrid[2][y].setText("O");
                    board[2][y] = -1;
                    break;
                }
            }
        } else if (column1 == 2) {
            for (int x = 0; x < 3; x++){
                if (board[x][0] == 0) {
                    gameGrid[x][0].setText("O");
                    board[x][0] = -1;
                    break;
                }
            }
        } else if (column2 == 2) {
            for (int x = 0; x < 3; x++){
                if (board[x][1] == 0) {
                    gameGrid[x][1].setText("O");
                    board[x][1] = -1;
                    break;
                }
            }
        } else if (column3 == 2) {
            for (int x = 0; x < 3; x++){
                if (board[x][2] == 0) {
                    gameGrid[x][2].setText("O");
                    board[x][2] = -1;
                    break;
                }
            }
        } else if (diag1 == 2){
            for (int x = 0; x < 3; x++){
                if (board[x][x] == 0) {
                    gameGrid[x][x].setText("O");
                    board[x][x] = -1;
                    break;
                }
            }
        } else if (diag2 == 2){
            for (int x = 0; x < 3; x++){
                if (board[2-x][x] == 0) {
                    gameGrid[2-x][x].setText("O");
                    board[2-x][x] = -1;
                    break;
                }
            }
        } else {
            // choosing random spot if no win condition, prioritizing corners early in game.
            boolean compTurn = true;
            Random r = new Random();
            while (compTurn) {
                int x = r.nextInt(3);
                int y = r.nextInt(3);
                if (turn < 4) {
                    if (x == 1) x = 0;
                    if (y == 1) y = 0;
                }
                if (board[x][y] == 0){
                    gameGrid[x][y].setText("O");
                    board[x][y] = -1;
                    compTurn = false;
                }
            }
        }

        checkWin("O");
    }

    private void checkWin(String V) {
        for (int x = 0; x < 3; x++) {
            if (gameGrid[x][0].getText().equals(V) && gameGrid[x][1].getText().equals(V) &&
                    gameGrid[x][2].getText().equals(V)) {
                win = true;
                break;
            }
            for (int y = 0; y < 3; y++) {
                if (gameGrid[0][y].getText().equals(V) && gameGrid[1][y].getText().equals(V) &&
                        gameGrid[2][y].getText().equals(V)) {
                    win = true;
                    break;
                }
            }
        }
        if (gameGrid[0][0].getText().equals(V) && gameGrid[1][1].getText().equals(V) &&
                gameGrid[2][2].getText().equals(V)) {
            win = true;
        }
        if (gameGrid[2][0].getText().equals(V) && gameGrid[1][1].getText().equals(V) &&
                gameGrid[0][2].getText().equals(V)) {
            win = true;
        }
        if (win) {
            if("O".equals(V))
                text.setText(R.string.comp_wins);
            else
                text.setText(R.string.you_win);
        }
        turn++;
        if (turn==9 && !win) {
            win = true;
            text.setText(R.string.draw);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
