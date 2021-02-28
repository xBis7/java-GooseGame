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
import java.util.ArrayList;
import java.io.*;


public class Game implements java.io.Serializable {
    
    private final ArrayList<Player> players;
    private final Board board;
    private int round;
    private int roundCount;
    private int startSqNum;

    
    public Game(){
        players = new ArrayList();
        board = new Board();
        round = 0;
        roundCount = 1;

    }

    public void setRound(int round) {
        this.round = round;
    }
    
    public void setStartSqNum(int startSqNum){
        this.startSqNum = startSqNum;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

        
    public void new_game(){
        
        Scanner input= new Scanner(System.in);
        //board creation
        Square sq;
        for(int k = 0; k < 64; ++k){
            sq = new Square(k, board);
            board.addSquare(sq);
        }
        System.out.println("Welcome to the goose game!");
        System.out.println("For this game to be played we will need");
        System.out.println("at least 2 players and no more than 6");
        System.out.println("Enter the number of players(2-6):");
        int i = input.nextInt();
        while(i < 2 || i > 6){
            System.out.println("invalid input... try again");
            System.out.println("Enter the number of players(2-6):");
            i = input.nextInt();
        }
        //players creation
        Player pl;
        for (int j=0; j<i ; ++j ){
            System.out.println("Enter the name of the player:");
            String name = input.next();
            setStartSqNum(0);
            pl = new Player(name, board, startSqNum); 
            players.add(pl);
        }
        game_play();
    }
    
    public void game_play(){
        
        Scanner input = new Scanner(System.in);
        String name = null;
        Player pl = new Player(name, board, startSqNum);    
        
        do{  
            
            ++round;
            System.out.println("\n\n --- round " + round + " ---");
            
            for(int c=0; c<players.size(); ++c){
                
                pl = players.get(c);
                name = pl.getName();
                System.out.println("\n\n --- Now playing " + name + " ---");

                System.out.println("Enter a char to roll dice or 'x' to exit the game");
                String str = input.next();

                if (str.equalsIgnoreCase("x")){
                    save_game(c);
                    return;
                }  
                
                int dice;
                int dice1; 
                int dice2;
                int oldSquareNumber;
                Square oldSquare, newSquare;
                
                oldSquare = pl.getSquare();
                oldSquareNumber = oldSquare.getSquareNum();
                
                System.out.println("Player '" + name + "' is in square " + oldSquareNumber );
                
                dice1 = Dice.dice_roll();
                dice2 = Dice.dice_roll();
                dice = dice1 + dice2;
                
                if(oldSquareNumber == 0){
                    if( (dice1 == 6 && dice2 == 3) || (dice1 == 3 && dice2 == 6) ){               
                        newSquare = board.getSquare(26);
                    }
                    else if( (dice1 == 5 && dice2 == 4) || (dice1 == 4 && dice2 == 5) ){
                        newSquare = board.getSquare(53);
                    }   
                    else{
                        newSquare = board.getSquare(oldSquareNumber + dice);
                    }    
                }
                else if(oldSquareNumber == 19){   //inn
                    
                    if(roundCount < 3){  
                        newSquare = board.getSquare(oldSquareNumber);
                        System.out.println("Round " + roundCount + " of 2");
                        setRoundCount(++roundCount);    
                    } 
                    else{
                        newSquare = board.getSquare(oldSquareNumber + dice);
                        setRoundCount(1);
                    }

                }
                else if(oldSquareNumber == 31){   //well
                    
                    newSquare = board.getSquare(oldSquareNumber);

                }
                else if(oldSquareNumber == 52){   //prison
                                                            
                    newSquare = board.getSquare(oldSquareNumber);
                    
                }
                else{
                    newSquare = board.getSquare(oldSquareNumber + dice);
                }
               
                square_occupied(pl, name, newSquare, oldSquare);

                pl.moveTo(newSquare);
                
                System.out.println("Player '" + name + "' has now moved to square " + newSquare.getSquareNum());
                
                newSquare.action(pl, dice, newSquare.getSquareNum());

            }
            
        }while(pl.isWinner() == false);
        
    }
    
    
    public void save_game(int plr_turn) {   
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to save the game?(y/n)");
        System.out.println("Enter 'y' for saving...");
        System.out.println("Enter 'n' or anything else for not saving");
        String str = input.next();
        if (str.equalsIgnoreCase("y")){
            
            String name = null;
            Player pl = new Player(name, board, startSqNum);
            Square pl_square;
            int squareNum;
            FileOutputStream fos = null;
            ObjectOutputStream out = null;
            int plr_num = players.size();
            try {
                fos = new FileOutputStream("GooseSave.txt");
                out = new ObjectOutputStream(fos);
                out.writeInt(plr_turn); //the number of the arraylist which goes to the player that saved the game 
                out.writeInt(plr_num);  //the number of players
                out.writeInt(round);  //round number
                for(int c=0; c<plr_num; ++c){
                    pl = players.get(c);
                    name = pl.getName();
                    pl_square = pl.getSquare();
                    squareNum = pl_square.getSquareNum();                    
                    out.writeUTF(name);     //player name
                    out.writeInt(squareNum);    //player square number
                }
                out.close();
                fos.close();
                System.out.println("Game saved...");
                System.out.println("Exit...");
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }
        else{
            System.out.println("Game not saved...");
            System.out.println("Exit...");
        }
    }
    
    
    public void load_game() {

        FileInputStream fis = null;
        ObjectInputStream in = null;
        String name = null;
        int last_plr_turn = 0;
        int squareNum = 0;
        int plr_num = 0; 
        int roundNum = 0;
        Square sq;
        for(int k = 0; k < 64; ++k){
            sq = new Square(k, board);
            board.addSquare(sq);   
        }
        Player pl;
        try {
            fis = new FileInputStream("GooseSave.txt");
            in = new ObjectInputStream(fis);
            last_plr_turn = in.readInt();
            plr_num = in.readInt();
            System.out.println(plr_num + " players");
            roundNum = in.readInt();
            setRound(roundNum);
            System.out.println("Game saved in round " + roundNum);
            for(int j=0; j<plr_num; ++j){
                name = (String) in.readUTF();
                squareNum = in.readInt();
                setStartSqNum(squareNum);
                pl = new Player(name, board, startSqNum); 
                players.add(pl);
                if(j == last_plr_turn){
                    System.out.println("Game saved by player '" + name + "'");
                }
                System.out.println("Player '" + name + "' is in square " + squareNum);
            } 
            
            in.close();
            fis.close();
            System.out.println("Game loaded...");
            
            
        }
        catch(EOFException ex1){
           ex1.printStackTrace();        
        } 
        catch (IOException ex2){
            ex2.printStackTrace();
        }
            
            // playing one round in load_game() so that the game resumes from the player that saved it
            // and then the game continues in game_play()
            Scanner input = new Scanner(System.in);

            System.out.println("\n\n --- round " + roundNum + " ---");

            for(int c=last_plr_turn; c<players.size(); ++c){
                
                pl = players.get(c);
                name = pl.getName();
                System.out.println("\n\n --- Now playing " + name + " ---");

                System.out.println("Enter a char to roll dice or 'x' to exit the game");
                String str = input.next();

                if (str.equalsIgnoreCase("x")){
                    save_game(c);
                    return;
                }  
                
                int dice;
                int dice1; 
                int dice2;
                int oldSquareNumber;
                Square oldSquare, newSquare;
                
                oldSquare = pl.getSquare();
                oldSquareNumber = oldSquare.getSquareNum();
                
                System.out.println("Player '" + name + "' is in square " + oldSquareNumber );
                
                dice1 = Dice.dice_roll();
                dice2 = Dice.dice_roll();
                dice = dice1 + dice2;
                
                
                if(oldSquareNumber == 0){
                    if( (dice1 == 6 && dice2 == 3) || (dice1 == 3 && dice2 == 6) ){               
                        newSquare = board.getSquare(26);
                    }
                    else if( (dice1 == 5 && dice2 == 4) || (dice1 == 4 && dice2 == 5) ){
                        newSquare = board.getSquare(53);
                    }   
                    else{
                        newSquare = board.getSquare(oldSquareNumber + dice);
                    }    
                }
                else if(oldSquareNumber == 19){   //inn
                    
                    if(roundCount < 2){  
                        newSquare = board.getSquare(oldSquareNumber);
                        setRoundCount(++roundCount);
                    } 
                    else{
                        newSquare = board.getSquare(oldSquareNumber + dice);
                        setRoundCount(0);
                    }

                }
                else if(oldSquareNumber == 31){   //well
                    
                    newSquare = board.getSquare(oldSquareNumber);

                }
                else if(oldSquareNumber == 52){   //prison
                                                            
                    newSquare = board.getSquare(oldSquareNumber);

                }
                else{
                    newSquare = board.getSquare(oldSquareNumber + dice);
                }
                
                square_occupied(pl, name, newSquare, oldSquare);

                pl.moveTo(newSquare);
                
                System.out.println("Player '" + name + "' has now moved to square " + newSquare.getSquareNum());
                
                newSquare.action(pl, dice, newSquare.getSquareNum());
        
            }
            
        game_play();    
    }
    
    
    public void square_occupied(Player plr, String name, Square new_sqr, Square old_sqr){
        String pl_name = null;
        Player pl = new Player(pl_name, board, startSqNum);
        
        for(int c=0; c<players.size(); ++c){
            pl = players.get(c);
            pl_name = pl.getName();    
            if(!name.equals(pl_name)){   
                if(new_sqr == pl.getSquare()){
                    System.out.println("Square " + new_sqr.getSquareNum() + " occupied by player '" + pl_name + "'");
                    pl.moveTo(old_sqr);
                    System.out.println("Player '" + pl_name + "' has been moved to square " + old_sqr.getSquareNum()); 
                }
                else{
                    System.out.println("Player '" + pl_name + "' is NOT in this square");
                }
            }
            
        }
    }
    
}
