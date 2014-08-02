package com.twu;

import java.io.*;

/**
 * Created by hanlei on 7/30/14.
 */
public class Game {

    protected Board board;
    private Player player1;
    private Player player2;
    private IOHandler handler;

    public Game(IOHandler handler, Board board, Player player1, Player player2){
        this.board = board;
        this.handler = handler;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getUserInput() {
        return handler.getUserInput();
    }

    public void play(){

        boolean player1Turn = true;

        while(!board.boardIsFull()){
            Player currentPlayer;
            int playerNum = 1;

            if(player1Turn){
                currentPlayer = player1;
                playerNum = 1;
            } else {
                currentPlayer = player2;
                playerNum = 2;
            }

            board.printBoard();
            handler.print("Player " + playerNum + " make a move: ");
            String userInput = getUserInput();

            while(!currentPlayer.takeTurn(Integer.parseInt(userInput))){
                handler.println("Location Already Taken!");
                handler.print("Player " + playerNum + " make a move: ");
                userInput = getUserInput();
            }

            if(board.gameIsWon()){
                board.printBoard();
                handler.println("Player " + playerNum + " Wins!");
                return;
            }

            player1Turn = !player1Turn;
        }

        handler.println("Game is a draw!");

    }

}
