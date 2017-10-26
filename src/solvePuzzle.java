package Sudoku;

import java.util.Scanner;

/**
 * Created by Jarvis on 9/23/17.
 */
public class solvePuzzle
{
    private Sudoku sud;
    private int size;


    public solvePuzzle()
    {
        sud = new Sudoku();
        size = sud.length();
    }

    public boolean solve(int row, int col)
    {
        if (col == size) {                                  // If column is 9th, we make it 0 and increase row number
            col = 0;                                        // If row becomes 9, that means we are in the last cell
            row+=1;                                         // and our work is done so we return true.
            if (row == size) {
                return true;
            }
        }

        for (int num = 1; num < size+1; num++)              // For numbers between 1 to 9, we start picking up values.
        {
            if (sud.puz[row][col] == 0)                     // If the entry in our matrix[row][col] is 0, that means its
            {                                               // empty and we try to fill it with the above value.
                if (isLegal(row, col, num))                 // If that value is legal, fill it and the call the function
                {                                           // again on the next cell.
                    sud.puz[row][col] = num;
                    if (solve(row, col + 1))            // We induce a recursive method that tries to fill each cell
                    {                                       // with a valid number till we reach the end. If no value in
                        return true;                        // that cell is legal, our function returns false which
                    }                                       // transfers control to the recursive call of the previous
                    else                                    // function call and sets the value in it back to zero and
                    {                                       // and starts filling in values from where it left off.
                        sud.puz[row][col] = 0;              // This continues till we reach the last cell which if it
                    }                                       // finds a legal value, fills it and that call returns true
                }                                           // which collapses all previous calls in one shot and returns
            }                                               // true.
            else
                return solve(row, col + 1);             // If that cell is not empty, skip it.
        }
        return false;                                       // No solution was found therefore return false.
    }
                                                            // We Looped through all the cells and were able to fill
                                                            //it out. So we return true.

    public boolean isLegal(int row, int col, int num)
    {
        for (int j = 0; j < size; j++)                      // Check for same number in row
        {
            if (sud.puz[row][j] == num) {return false;}
        }

        for (int r = 0; r < size; r++)                      // Check for same number in column
        {
            if (sud.puz[r][col] == num) {return false;}
        }

        int topRightRow = (row / sud.getBox_r()) * sud.getBox_r();           // Check for same number in that box.
        int topRightCol = (col / sud.getBox_c()) * sud.getBox_c();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (sud.puz[topRightRow+i][topRightCol+j] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        solvePuzzle sp = new solvePuzzle();
        if(!sp.sud.loadSudoku())   {return;}
        long start = System.currentTimeMillis();
        if(sp.solve(0,0)) {
            System.out.println("Solution found!\n");
            sp.sud.printSudoku();
            System.out.println("\nIt took me " + (System.currentTimeMillis() - start) + "ms to solve this puzzle.");
        }
        else
        {
            System.out.println("No Solution Found!");
        }

    }
}
