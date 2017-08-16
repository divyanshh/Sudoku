package com.divyanshjain.sudoku.view.sudokugrid;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by divyanshjain on 12/05/17.
 */

public class BaseSudokuCell extends View{

    private int value;
    private boolean modifiable = true;

    public BaseSudokuCell(Context context) {

        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setNotModifiable() {
        modifiable = false;
    }

    public boolean modifiableOrNot() {
        return modifiable;
    }

    public void setInitValue(int value) {
        this.value = value;
        invalidate();

    }

    public void setValue(int value){
        if(modifiable) {
            this.value = value;
            invalidate();
        }
    }

    public int getValue(){
        return value;
    }
}
