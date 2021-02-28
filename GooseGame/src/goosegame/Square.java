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

import java.lang.*; 

public class Square {
    
    private int squareNum;
    private Board board;
    private Player player;
    
    public Square(int squareNum, Board board){
        this.squareNum = squareNum;
        this.board = board;
    }

    public int getSquareNum() {
        return squareNum;
    }
  
    public void action(Player player, int dice, int squareNum){  
        this.player = player;
        this.squareNum = squareNum;
        
        Game g = new Game();
        
        if(squareNum == 5 || squareNum == 9 || squareNum == 14 || squareNum == 18 //goose
                || squareNum == 23 || squareNum == 27 || squareNum == 32 
                || squareNum == 36 || squareNum == 41 || squareNum == 45 
                || squareNum == 50 || squareNum == 54 || squareNum == 59){
            System.out.println("Goose square, repeating the dice");   
            
            squareNum += dice;
            
            if(squareNum > 63){
               int diff = squareNum - 63;
               squareNum = 63 - diff;
            }
            
            System.out.println("Moving to square " + squareNum);
            
            if(squareNum == 59 && dice == 8){
                squareNum = 59 - dice;
            }
            
            action(player, dice, squareNum);
            
            /*
            if the player lands in 59 and the dice is 8 then he repeats the dice and ends up in 59 again and again 
            until a stackoverflow error occurs
            so to prevent that the squareNum will be (59 - dice) instead of (59 + dice)
            */
        }
        else if(squareNum == 6){    //bridge
            System.out.println("Bridge square, moving to square 12");
            squareNum = 12;
        }
        else if(squareNum == 19){    //inn
            System.out.println("Inn square, waiting for 2 rounds");
            //action in game_play()
        }
        else if(squareNum == 31){    //well
            System.out.println("Well square");
            //action in game_play()
        }
        else if(squareNum == 42){   //labyrinth
            System.out.println("Labyrinth square, moving back to square 30");
            squareNum = 30;
        }
        else if(squareNum == 52){    //prison
            System.out.println("Prison square");
             //action in game_play()
        }
        else if(squareNum == 58){   //death
            System.out.println("Death square, moving back to square 1");
            squareNum = 1;
        }
        else if(squareNum == 63){
            player.setWinner();
            player.isWinner();
            System.out.println("PLAYER '" + player.getName() + "' IS THE WINNER!");
            System.out.println("GAME OVER!");
            System.exit(0); 
        }
        else{
            System.out.println("Plain square, no special action...");   
        } 
    }
    
}

