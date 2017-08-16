package com.divyanshjain.sudoku;

import android.content.Context;

import com.divyanshjain.sudoku.view.sudokugrid.GameGrid;

/**
 * Created by divyanshjain on 12/05/17.
 */

public class GameEngine {

    private GameGrid grid = null;

    private int selectedPosX = -1 , selectedPosY = -1;

    private static GameEngine instance;

    private GameEngine(){

    }

    public static GameEngine getInstance(){
        if (instance == null){
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context , int hd , int solver , int solve) {

        int[][] sudoku = sudokuGenerator.getInstance().generateGrid(solver);
        sudoku = sudokuGenerator.getInstance().removeElements(sudoku , hd);
        grid = new GameGrid(context);
        grid.setGrid(sudoku);

        if (solve == 1) {
            grid.solveSudoku();
        }

    }

    public GameGrid getGrid() {

        return grid;
    }

    public void setSelectedPosition(int x , int y) {
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber(int number) {
        if (selectedPosX != -1 && selectedPosY != -1) {
            grid.setItem(selectedPosX , selectedPosY , number);
        }
        grid.checkGame();
    }
}
