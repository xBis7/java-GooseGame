/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goosegame;

/**
 *
 * @author Xristos
 */

import java.util.Scanner;

public class GooseGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Game g = new Game();
        int i;
        do{
            System.out.println("1.New game");
            System.out.println("2.Load game");
            System.out.println("3.Exit");
            i = input.nextInt();
        }while(i!=1 && i!=2 && i!=3);

        if(i == 1){
            g.new_game();
        }        
        else if(i == 2){
            g.load_game();
        }        
        else{
            System.out.println("Exiting game...");
            return;
        }        

    }
    
}
