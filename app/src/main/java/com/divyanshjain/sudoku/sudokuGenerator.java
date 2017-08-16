package com.divyanshjain.sudoku;

import com.divyanshjain.sudoku.Solver.Backtrack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by divyanshjain on 12/05/17.
 */

public class sudokuGenerator {

    private static sudokuGenerator instance;
    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();
    private Random rand = new Random();

    private void SudokuGenerator() {


    }

    public static sudokuGenerator getInstance() {           // sudokuGenerator is the return type here.

        if(instance == null) {

            instance = new sudokuGenerator();
        }
        return instance;
    }

    public int[][] generateGrid(int solver) {

        int[][] sudoku = new int[9][9];
        if(solver == 0) {
            int currentPos = 0;
            clearGrid(sudoku);

            while (currentPos < 81) {

                if (Available.get(currentPos).size() != 0) {
                    int i = rand.nextInt(Available.get(currentPos).size());
                    int number = Available.get(currentPos).get(i);

                    if (!checkConflict(sudoku, currentPos, number)) {
                        int xPos = currentPos % 9;
                        int yPos = currentPos / 9;

                        sudoku[xPos][yPos] = number;
                        Available.get(currentPos).remove(i);

                        currentPos++;
                    } else {

                        Available.get(currentPos).remove(i);
                    }

                } else {

                    for (int i = 1; i <= 9; i++) {

                        Available.get(currentPos).add(i);

                    }
                    currentPos--;
                }


            }
        }

        else if (solver == 1) {

            for(int y = 0; y < 9; y++) {

                for(int x = 0; x < 9; x++) {

                    sudoku[x][y] = 0;

                }
            }
        }

        return sudoku;
    }

    public int[][] removeElements(int[][] sudoku , int hrd) {
        int i = 0;

        while (i < hrd) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if (x < 9 && y < 9) {

                if (sudoku[x][y] != 0) {
                    sudoku[x][y] = 0;
                    i++;
                }
            }
        }
        return sudoku;
    }

    private void clearGrid(int[][] sudoku) {
        Available.clear();

        for(int y = 0; y < 9; y++) {

            for(int x = 0; x < 9; x++) {

                sudoku[x][y] = -1;

            }
        }

        for(int x = 0; x < 81; x++) {
            Available.add(new ArrayList<Integer>());
            for(int i = 1; i <= 9; i++) {
                Available.get(x).add(i);
            }
        }
    }

    private boolean checkConflict(int[][] sudoku , int currentPos , final int number ) {
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if(checkHorizontalConflict(sudoku , xPos , yPos , number) || checkVerticalConflict(sudoku , xPos , yPos , number) || checkRegionConflict(sudoku , xPos , yPos , number)) {
            return true;
        }

        return false;
    }

    private boolean checkHorizontalConflict(final int[][] sudoku , final int xPos , final int yPos , final int number) {
        for (int x = xPos-1 ; x >= 0; x--) {
            if (number == sudoku[x][yPos]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalConflict(final int[][] sudoku , final int xPos , final int yPos , final int number) {
        for (int y = yPos-1 ; y >= 0; y--) {
            if (number == sudoku[xPos][y]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegionConflict(final int[][] sudoku , final int xPos , final int yPos , final int number) {
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for(int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
            for(int y = yRegion * 3; y < yRegion * 3 + 3; y++) {

                if((x != xPos || y != yPos) &&number == sudoku[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

}
