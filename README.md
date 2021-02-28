# java-GooseGame

 Simple implementation of the Goose Game. The game is played with 2-6 players. Each player has the ability to save the game in a file, during his turn and load it later.
 
 At the start there are 3 options: New Game, Load Game, Exit.
 
 All players start from square 0 and throw two dices to move across the board.
 
 The first player to reach square 63, is the winner. 
 
 If the player is near square 63, he rolls the dices and he lands out of the board boundaries then he will move backwards for as many squares as the difference.
 
 If the player rolls the dices and lands in a square occupied by another player, then the players trade places. 
 
 The player1 goes in his new square and player2 occupying the new squares moves to the square from which player1 started, his old square.
 
 If at the first roll of the dices the outcome is 6 + 3 then the player moves to square 26.
 
 If at the first roll of the dices the outcome is 5 + 4 then the player moves to square 53.
 
 There are special squares:
  squares 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59 are "goose" squares, if the player lands there, he plays again repeating the previous dice outcome.
  (if he lands in a special square he has to perform the action of the square, for example if the lands in a "goose" square he repeats the dice)
  
  square 6 is the "bridge" square, if the player lands there, he moves to square 12.
  
  square 19 is the "inn" square, if the player lands there, he doesnt play for the next 2 rounds.
  
  square 31 is the "well" square and square 52 is the "prison" square, if the player lands in one of these two, 
  he is trapped until someone else lands in the square and takes his place.
  
  square 58 is the "death" square, if the player lands there, he moves back to square 1.
