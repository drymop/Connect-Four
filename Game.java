import java.util.*;
import java.io.*;

public class Game {
  public static void main(String[] args) {
    int[] totWins = new int[3];
    int t, game;
    Board gameBrd = null;
    Scanner scnr = new Scanner(System.in);
    
    BoardGUI brdGUI = null;
    MainMenuGUI menuGUI = new MainMenuGUI();
    GameEndGUI endGUI = new GameEndGUI();
    ChoosePlayerGUI choosePlayersGUI = new ChoosePlayerGUI();

    Player[] players= new Player[2];
    
    StdDraw.setCanvasSize(600, 400);
    StdDraw.enableDoubleBuffering();

    while (true) {
      
      // ask to choose the game
      menuGUI.draw();
      do {
        game = menuGUI.getMove();
      } while (game == 0);
      
      if (game == 1) { // Tic Tac Toe
        gameBrd = new TicTacToeBoard();
        brdGUI = new TicTacToeGUI();
      } else { // Connect Four
        gameBrd = new Connect4Board();
        brdGUI = new Connect4GUI();
      }

      // choose player
      do {
        choosePlayersGUI.draw();
        do {
          t = choosePlayersGUI.getMove();
        } while (t == 0);
        if (t != -1) {
          choosePlayersGUI.select(t);
        } else {break;}
      } while (true);
      
      switch(choosePlayersGUI.getSelection1()) {
        case 1: players[0] = new HumanPlayer(1, brdGUI); break;
        case 3: players[0] = new RandomPlayer(); break;
        case 11: 
          if (game == 2) { // if connect4 then tournament, tictactoe doesn't have tournament
            players[0] = new TournamentPlayer8(1, 10); break;
          }
        default: players[0] = new LookAheadAIPlayer(1, choosePlayersGUI.getSelection1()-3);
      } switch(choosePlayersGUI.getSelection2()) {
        case 2: players[1] = new HumanPlayer(2, brdGUI); break;
        case 4: players[1] = new RandomPlayer(); break;
        case 12: 
          if (game == 2) {
            players[1] = new TournamentPlayer8(2, 10); break;
          }
        default: players[1] = new LookAheadAIPlayer(2, choosePlayersGUI.getSelection2()-4);
      }

      // play the game
      t = play(gameBrd, brdGUI, players);
      totWins[t-1]++;
      // replay or not
      endGUI.draw(t);

      do {
        t = endGUI.getMove();
      } while (t == 0);
      
      if (t == -1) {
        endGUI.draw(totWins);  
        break;
      }
    }
  }

  /*
   * Plays a two player game on Board b
   * At the conclusion of a game, this should return a 1 if player 1 wins,
   * a 2 is player 2 wins,
   * a 3 if there is a draw
   *
   */
  public static int play(Board b, BoardGUI g, Player[] players) {
    int move;

    while (true) {
      g.setTitleAndColor(players[b.nextTurn()].callSign(), StdDraw.BLACK);
      g.draw(b);
      move = players[b.nextTurn()].decideMove(b);

      if (move == -1) {
      //undo move:
        b.revert(b.numTotalMoves()-2);
      } else {
      //make move:
        b.makeMove(move);
        if (b.isWinning() != 0) {
          g.draw(b);
          return b.isWinning();
        }
      }
    }
  }
}
