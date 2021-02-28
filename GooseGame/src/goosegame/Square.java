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
    
    public void setSquareNum(int squareNum){
       this.squareNum = squareNum; 
    }
  
    public void action(Player player, int dice, int squareNum){  
        this.player = player;
        
        Game g = new Game();
        
        if(squareNum == 5 || squareNum == 9 || squareNum == 14 || squareNum == 18 //goose
                || squareNum == 23 || squareNum == 27 || squareNum == 32 
                || squareNum == 36 || squareNum == 41 || squareNum == 45 
                || squareNum == 50 || squareNum == 54 || squareNum == 59){
            System.out.println("Goose square, repeating the dice");   
            
            setSquareNum(squareNum + dice);
            
            if(getSquareNum() > 63){
               int diff = getSquareNum() - 63;
               setSquareNum(63 - diff);
            }
            
            System.out.println("Moving to square " + getSquareNum());
            
            if(getSquareNum() == 59 && dice == 8){
                setSquareNum(59 - dice);
            }
            
            action(player, dice, getSquareNum());
            
            /*
            otan peftei sto 59 kai einai na epanalabei to zari kai fernei 8, pagideyetai sto 59 
            kai ksanarixnei kai den termatizei mexri na uparksei stackoverflow error
            gi auto anti na kanei +dice , kanei -dice
            */
        }
        else if(squareNum == 6){    //bridge
            System.out.println("Bridge square, moving to square 12");
            setSquareNum(12);
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
            setSquareNum(30);
        }
        else if(squareNum == 52){    //prison
            System.out.println("Prison square");
             //action in game_play()
        }
        else if(squareNum == 58){   //death
            System.out.println("Death square, moving back to square 1");
            setSquareNum(1);
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

