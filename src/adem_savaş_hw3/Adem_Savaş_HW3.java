/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adem_savaş_hw3;

import java.io.IOException;

/**
 *
 * @author Adem
 */
public class Adem_Savaş_HW3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        // TODO code application logic here
        MazeClass mz=new MazeClass();
        mz.InputMaze("C:\\Users\\adams\\OneDrive\\Desktop\\adem.txt");
        mz.FindLoops();
        mz.FindLoops("C:\\Users\\adams\\OneDrive\\Desktop\\out.txt");
    }
    
}
