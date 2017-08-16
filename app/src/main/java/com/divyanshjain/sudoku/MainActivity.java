package com.divyanshjain.sudoku;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.divyanshjain.sudoku.Solver.SudokuSolver;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startGame;
    private Button sudokuSolver;
    private Button highScores;
    private int hardness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startGame = (Button) findViewById(R.id.button2);
        startGame.setOnClickListener(this);

        sudokuSolver = (Button) findViewById(R.id.button3);
        sudokuSolver.setOnClickListener(this);

        highScores = (Button) findViewById(R.id.button4);
        highScores.setOnClickListener(this);

        Typeface face = Typeface.createFromAsset(getAssets(), "funky1.ttf");
        startGame.setTypeface(face);
        sudokuSolver.setTypeface(face);
        highScores.setTypeface(face);

        // Load an ad into the AdMob banner view.

        MobileAds.initialize(getApplicationContext(), " ca-app-pub-4527272160231444~5516063211");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("13DD56D2E91F9E096D9A28C3D615181D").build();
        adView.loadAd(adRequest);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button2:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("set difficulty");

                alertDialogBuilder.setPositiveButton("hard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Bundle basket = new Bundle();
                        hardness = 50;
                        basket.putInt("key" , hardness);
                        Intent a = new Intent(MainActivity.this , ActivityGame.class);
                        a.putExtras(basket);
                        startActivity(a);
                    }
                });

                alertDialogBuilder.setNegativeButton("medium", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle basket = new Bundle();
                        hardness = 40;
                        basket.putInt("key" , hardness);
                        Intent a = new Intent(MainActivity.this , ActivityGame.class);
                        a.putExtras(basket);
                        startActivity(a);

                    }
                });

                alertDialogBuilder.setNeutralButton("easy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle basket = new Bundle();
                        hardness = 30;
                        basket.putInt("key" , hardness);
                        Intent a = new Intent(MainActivity.this , ActivityGame.class);
                        a.putExtras(basket);
                        startActivity(a);
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                /* LinearLayout.LayoutParams buttonParams;
                Button buttonPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                buttonParams = (LinearLayout.LayoutParams) buttonPositive.getLayoutParams();
                buttonParams.weight = 1;
                buttonParams.width = buttonParams.MATCH_PARENT;

                Button buttonNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonParams = (LinearLayout.LayoutParams) buttonNegative.getLayoutParams();
                buttonParams.weight = 1;
                buttonParams.width = buttonParams.MATCH_PARENT;

                Button buttonNeutral = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                buttonParams = (LinearLayout.LayoutParams) buttonNeutral.getLayoutParams();
                buttonParams.weight = 1;
                buttonParams.width = buttonParams.MATCH_PARENT; */

                break;

            case R.id.button3:
                Intent a = new Intent(MainActivity.this , SudokuSolver.class);
                startActivity(a);
                break;

        }
    }
}