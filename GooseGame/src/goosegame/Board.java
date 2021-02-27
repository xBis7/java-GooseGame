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
        
        //squares.size() = 64 giati exoume apo to 0-63, opote an afairesoume 1 exoume 63 kai prepei na afairesoume 
        //kai allo ena gia to 0 gia na exoume times apo to 1 ews to 63 dhladh 62 tetragwna ara +2
        if (squareNumber >= squares.size()){
            int diff = squareNumber - squares.size() + 2;         
            squareNumber = squares.size() - diff;
        }
        
        return squares.get(squareNumber);
        
    }
    
}
