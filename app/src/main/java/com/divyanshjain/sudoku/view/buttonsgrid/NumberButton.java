package com.divyanshjain.sudoku.view.buttonsgrid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.divyanshjain.sudoku.GameEngine;

/**
 * Created by divyanshjain on 12/05/17.
 */

public class NumberButton extends Button implements View.OnClickListener{

    private int number;

    public NumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        GameEngine.getInstance().setNumber(number);

    }

    public void setNumber(int number) {
        this.number = number;
    }
}
