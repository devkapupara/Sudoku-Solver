package Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Jarvis on 9/23/17.
 */
public class Sudoku
{
    public int[][] puz;

    public Sudoku()
    {
        puz = new int[9][9];
    }

    public boolean loadSudoku()
    {
        try
        {
            Scanner scan = new Scanner(new File("puzzle.txt"));

            if (!scan.hasNextLine())    {System.out.println("Empty File. Exiting..."); return false;}

            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    puz[i][j] = scan.nextInt();
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Not Found. Please create a puzzle.txt in the same folder this app is in. Exiting...");
        }
        System.out.println("Sudoku is now loaded.");
        return true;
    }

    public void printSudoku()
    {
        for (int[] i: puz)
        {
            System.out.println("-------------------------------------");
            for(int j: i)
            {
                System.out.print("| " + j + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }
}
