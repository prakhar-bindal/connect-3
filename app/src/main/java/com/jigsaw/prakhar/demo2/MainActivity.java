package com.jigsaw.prakhar.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int player = 0;

    boolean gameisactive = true;
    int [] played = {2,2,2,2,2,2,2,2,2};

    int [][] won = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin (View view) {

        ImageView coin = (ImageView) view;


        int tapped = Integer.parseInt(coin.getTag().toString());

        if (played[tapped] == 2 && gameisactive) {


            coin.setTranslationY(-1000f);
            played[tapped] = player;


            if (player == 0) {

                coin.setImageResource(R.drawable.yellow);
                player = 1;

            } else if (player == 1) {
                coin.setImageResource(R.drawable.red);
                player = 0;
            }


            coin.animate().translationYBy(1000f).rotation(36).setDuration(300);


            for(int [] yo:won){

                if(played[yo[0]]==played[yo[1]] && played[yo[1]]==played[yo[2]] && played[yo[0]]!=2){

                    String playerwon = "yellow";

                    if(played[yo[0]]==1)
                        playerwon = "Red";

                    LinearLayout playagain = (LinearLayout) findViewById(R.id.linear);
                    TextView winning = (TextView) findViewById(R.id.textView);
                    winning.setText(playerwon + " won !");

                    playagain.setVisibility(View.VISIBLE);
                    gameisactive=false;


                }
                else{
                    boolean draw = true;
                    for(int i = 0;i<played.length;i++){
                        if(played[i]==2)
                            draw = false;
                    }

                    if(draw)
                    {
                        LinearLayout playagain = (LinearLayout) findViewById(R.id.linear);
                        TextView winning = (TextView) findViewById(R.id.textView);
                        winning.setText("Its a draw !");

                        playagain.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void again(View view){
        LinearLayout playagain = (LinearLayout) findViewById(R.id.linear);
        playagain.setVisibility(View.INVISIBLE);
        gameisactive = true;

        for(int i = 0;i<played.length;i++){
            played[i] = 2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0;i<grid.getChildCount();i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
