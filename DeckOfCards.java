import java.util.*;
import java.util.Random;
public class DeckOfCards{
      public static void main(String[] args){
            Random random = new Random();
            String[] suits = {
                  "Clubs" , "Diamonds", "Hearts", "Spades"
               };

            String[] ranks = {"2", "3" ,"4", "5", "6", "7", "8", "9", "10","Jack", "Queen", "King", "Ace"};
            int suits_idx, rank_idx;
            String[][] players = new String[4][9];
            for (int i = 0; i<4; i++){
               for (int j=0; j<9; j++){
                     suits_idx = random.nextInt(4);
                     rank_idx = random.nextInt(13);
                     players[i][j] = "Player " + i + " has " + ranks[rank_idx]
 + " of " + suits[suits_idx];
                  }
               }

            for (int i = 0; i<4; i++){
               for (int j=0; j<9; j++){
                  System.out.println(players[i][j]);
               }

         }
   }

}
