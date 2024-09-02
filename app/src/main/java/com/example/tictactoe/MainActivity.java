package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    char[][] board = new char[3][3];


    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    TextView labels;

    char player;
    char pl;
    boolean gameOver;
    boolean tie;

    int n;
    int ROW, COL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        player = 'X';
        gameOver = false;
        tie = false;
        n = 0;
        ROW = 0;
        COL = 0;


        init_();
    }

    private void init_() {
        //    Board making

        for(int row = 0; row<3;row++)
        {
            for(int col = 0;col<3;col++)
            {
                board[row][col] = '|';
            }
        }

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        labels = findViewById(R.id.textView);

    }

    public void buttonClick(View view) {
        Button thisBtn = (Button) view;
//        thisBtn.setEnabled(false);

        if(thisBtn == button1){n=1;}
        else if(thisBtn == button2){n=2;}
        else if(thisBtn == button3){n=3;}
        else if(thisBtn == button4){n=4;}
        else if(thisBtn == button5){n=5;}
        else if(thisBtn == button6){n=6;}
        else if(thisBtn == button7){n=7;}
        else if(thisBtn == button8){n=8;}
        else {n=9;}


        if(n<=3)
        {
            ROW = 0;
            COL = n-1;
        }else if(n<=6)
        {
            ROW = 1;
            COL = n-4;
        }else if(n<=9)
        {
            ROW = 2;
            COL = n-7;
        }


        if(!gameOver) {
            if(board[ROW][COL] == '|')
            {
                board[ROW][COL] = player;
                player = player == 'X' ? 'O' : 'X';
            }
            thisBtn.setText(String.format("%s", board[ROW][COL]));
        }

        gameOver = haveWon(player, board);
        if(gameOver)
        {
//            System.out.println("Player " + (player == 'X' ? 'O' : 'X') + " Won! ");
//            Toast.makeText(this, player == 'X' ? 'O' : 'X', Toast.LENGTH_SHORT).show();
            labels.setText("Player " + (player == 'X' ? 'O' : 'X') + " Won! ");
        }

        tie = isFull(board);
       if(tie){
//            System.out.println("Tie !");
//            Toast.makeText(this, " Tie", Toast.LENGTH_SHORT).show();
           labels.setText("Tie !");
        }

    }

    public boolean isFull(char[][] board)
    {
        boolean flag = true;
        for( int i = 0;i<3;i++)
        {
            for(int j = 0 ; j<3 ; j++)
            {
                if(board[i][j] == '|')
                {
                    flag = false;
                    break;
                }
            }
        }
        return flag;

    }

    public boolean haveWon(char player, char[][] board)
    {
        pl = player == 'X' ? 'O' :'X';

        for(int row = 0; row<3;row++)
        {
            for(int col = 0;col<3;col++)
            {
                System.out.print(" " +board[row][col] + " ");
            }
            System.out.println(" ");
        }

        for( int row = 0; row<3;row++)
        {
            if (board[row][0]==pl && board[row][1]==pl && board[row][2]==pl) {
                return true;
            }
        }

        for( int col = 0; col<3;col++)
        {
            if (board[0][col]==pl && board[1][col]==pl && board[2][col]==pl) {
                return true;
            }
        }
        if (board[0][0]==pl && board[1][1]==pl && board[2][2]==pl) {
            return true;
        }
        if (board[0][2]==pl && board[1][1]==pl && board[2][0]==pl) {
            return true;
        }


        return false;
    }

    public void restartButton(View v)
    {
        player = 'X';
        gameOver = false;
        tie = false;
        n = 0;
        ROW = 0;
        COL = 0;

        for(int row = 0; row<3;row++)
        {
            for(int col = 0;col<3;col++)
            {
                board[row][col] = '|';
            }
        }

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        labels.setText("");

    }

}