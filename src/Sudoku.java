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
    private int size;
    private int box_r;
    private int box_c;

    public Sudoku()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter size of sudoku: ");
        size = s.nextInt();
        System.out.print("Enter # rows of inner box: ");
        box_r = s.nextInt();
        System.out.print("Enter # columns of inner box: ");
        box_c = s.nextInt();
        s.close();
        puz = new int[size][size];
    }

    public int length()
    {
        return puz.length;
    }

    public int getBox_r()
    {
        return box_r;
    }

    public int getBox_c()
    {
        return box_c;
    }

    public boolean loadSudoku()
    {
        try
        {
            Scanner scan = new Scanner(new File("puzzle.txt"));

            if (!scan.hasNextLine())    {System.out.println("Empty File. Exiting..."); return false;}

            for (int i = 0; i < puz.length; i++)
            {
                for (int j = 0; j < puz[i].length; j++)
                {
                    puz[i][j] = scan.nextInt();
                }
            }
            scan.close();
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
        int r = 0;
        int c = 0;
        String repeatedStar = new String(new char[4*puz.length+4]).replace('\0', '-');
        for (int[] i: puz)
        {
            if (r % box_r == 0)
                System.out.print(repeatedStar+'\n');
            for(int j: i)
            {
                if (c % box_c == 0)
                    System.out.print('|');
                if (j < 10)
                    System.out.print("  " + j + " ");
                else
                    System.out.print(" " + j + " ");
                c++;
            }
            r++;
            System.out.print("|");
            System.out.println();
        }
        System.out.print(repeatedStar+'\n');
    }
}
