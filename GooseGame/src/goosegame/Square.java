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

public class Square {
    
    private int squareNum;
    private Board board;
    private Player player;
    private int round;
    
    public Square(int squareNum, Board board){
        this.squareNum = squareNum;
        this.board = board;
    }

    public int getSquareNum() {
        return squareNum;
    }

    public void setSquareNum(int squareNum) {
        this.squareNum = squareNum;
    }     
    
    public void action(Player player, int round, int dice){  
        this.player = player;
        this.round = round;
        
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
            
            if(squareNum == 63){
                player.setWinner();
                player.isWinner();
                System.out.println("PLAYER '" + player.getName() + "' IS THE WINNER!");
                System.out.println("GAME OVER!");
                return;
            }
            
            if(squareNum == 59 && dice == 8){
                squareNum = 59 - dice;
            }
            
            action(player,round,dice);
            
            /*
            otan peftei sto 59 kai einai na epanalabei to zari kai fernei ektos twn oriwn
            megalytero apo 63 kai prepei na epistrepsei th diafora kai na ektelesei pali action 
            pagideyetai sto 59 kai ksanarixnei kai den termatizei mexri 
            na uparksei stackoverflow error
            */
        }
        else if(squareNum == 6){    //bridge
            System.out.println("Bridge square, moving to square 12");
            squareNum = 12;
        }
        else if(squareNum == 19){    //inn
            System.out.println("Inn square, waiting for 2 rounds");
        }
        else if(squareNum == 31){    //well
            System.out.println("Well square");
        }
        else if(squareNum == 42){   //labyrinth
            System.out.println("Labyrinth square, moving back to square 30");
            squareNum = 30;
        }
        else if(squareNum == 52){    //prison
            System.out.println("Prison square");
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
            return; //den xreiazetai
        }
        else{
            System.out.println("Plain square, no special action...");   
        } 
    }
    
}

/*
1-63 tetragwna 
oi paiktes ksekinane apo to 0
sto 63 nikh
5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 'goose' kai o paikths 
    epanalambanei th zaria poy erikse
dice 6+3 o paikths phgainei sto 26 
dice 5+4 o paikths phgainei sto 53
an sto tetragwno brisketai allos paikths 8a metafer8ei sto tetragwno apo to opoio 
    ksekinhse o neos paikths
6 'bridge' kai metaferetai sto 12
19 'inn' xanei 2 gurous
31 'Well' meneis ekei mexri na r8ei kapoios allos kai na parei th 8esh soy
42 'Labyrinth' epistrefeis pisw sto 30
52 'Prison' meneis mexri na r8ei kapoios allos alla autos pou 8a r8ei apla se bgazei 
    kai den pairnei kai th 8esh soy,sunexizei kanonika meta
58 'Death' epistrefeis sto 1
*/


