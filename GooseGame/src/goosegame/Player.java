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

public class Player {
    
    private String name;
    private Board board;
    private Square square;
    private boolean isWinner;
    
    
    public Player(String name, Board board, int  startingSquare){
        this.name = name;
        this.board = board;
        square = board.getSquare(startingSquare);
        isWinner = false;
    }
    
    public void setWinner(){
        isWinner = true;
    }
    
    public boolean isWinner(){
        return isWinner;
    }

    public String getName() {
        return name;
    }

    public Square getSquare() {
        return square;
    }
       
    public void moveTo(Square square){
        this.square = square;    
    }
    
}
