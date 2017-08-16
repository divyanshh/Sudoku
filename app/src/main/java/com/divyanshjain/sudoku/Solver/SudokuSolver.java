package com.divyanshjain.sudoku.Solver;

import com.divyanshjain.sudoku.GameEngine;
import com.divyanshjain.sudoku.R;
import com.divyanshjain.sudoku.view.sudokugrid.GameGrid;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.divyanshjain.sudoku.Solver.Backtrack.grid;

public class SudokuSolver extends AppCompatActivity implements View.OnClickListener {

    private Button solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_solver);

        MobileAds.initialize(getApplicationContext(), " ca-app-pub-4527272160231444~5516063211");
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("B3831012E69C2476CA3042F94FB057AE").build();
        adView.loadAd(adRequest);

        GameEngine.getInstance().createGrid(this , 0 , 1 , 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            //item.setIcon(R.drawable.glossy);
            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show();
            GameEngine.getInstance().createGrid(this , 0 , 1 , 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


    }
}
