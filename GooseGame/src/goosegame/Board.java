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

import java.util.ArrayList;

public class Board {
    ArrayList <Square> squares;
    
    public Board(){
        squares = new ArrayList();
    }
    
    public void addSquare(Square newSquare){
        squares.add(newSquare);
    }
    
    public Square getSquare(int squareNumber){
        
        //squares.size() = 64 (0-63)
        //we want numbers 1-63 (63 is the victory square), so 62 squares, therefore 64-2 (-64 + 2)
        if (squareNumber >= squares.size()){
            int diff = squareNumber - squares.size() + 2;         
            squareNumber = squares.size() - diff;
        }
        
        return squares.get(squareNumber);
        
    }
    
}
