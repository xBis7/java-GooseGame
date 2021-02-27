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

import java.util.Random;

public class Dice {
    
    private static final Random random = new Random();
    private static final int max = 6;
    private static final int min = 1;
    
    public static int dice_roll(){
        int r = random.nextInt(max - min + 1) + min;
        

        System.out.println("Rolling dice... outcome: " + r);
        return r;
    }

}