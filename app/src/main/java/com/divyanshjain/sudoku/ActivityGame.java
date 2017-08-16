package com.divyanshjain.sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityGame extends AppCompatActivity {

    private AdView adView;
    private TextView timer;
    private int time = 0;
    private int hardness;
    private long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_with_ads);

        MobileAds.initialize(getApplicationContext(), " ca-app-pub-4527272160231444~5516063211");
        // Load an ad into the AdMob banner view.
        adView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("13DD56D2E91F9E096D9A28C3D615181D").build();
        adView.loadAd(adRequest);

        Bundle gotBasket = getIntent().getExtras();
        hardness = gotBasket.getInt("key");

        GameEngine.getInstance().createGrid(this , hardness , 0 , 0);

        timer = new TextView(this);
        timer.setPadding(5, 5, 50, 5);
        timer.setTextSize(20);
        timer.setTextColor(getColor(R.color.white));
        setTimer();

        /*adView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "ad opened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "ad closed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                Toast.makeText(getApplicationContext(), "ad failed to load", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "ad left application", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "ad loaded", Toast.LENGTH_LONG).show();
            }
        });*/

    }

    private void setTimer() {

        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

                                  public void run() {
                                      //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                      runOnUiThread(new Runnable() {

                                          public void run() {
                                              int seconds = time % 60;
                                              int minutes = time / 60;
                                              String stringTime = String.format("%02d:%02d", minutes, seconds);
                                              timer.setText(stringTime);
                                              time += 1;

                                          }

                                      });
                                  }

                              },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                1000);
    }

    private void printSudoku(int[][] sudoku) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(sudoku[x][y] + "|");
            }
            System.out.println();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("").setActionView(timer).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press back again to stop game",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up

            super.onBackPressed();
        }
    }
}

