package com.rshipp_a2.rshipp_a2;

import java.util.Random;

/**
 * TicTacToe game logic.
 * Supports 1-human/1-computer and 2-human games. Does NOT support 2-computer games.
 */
public class TicTacToeGame {
    public static final int EMPTY = 0;
    public static final int X = 1;
    public static final int O = 2;
    private final int DRAW = 3;
    private int[] board;
    private boolean xIsHuman, oIsHuman;

    public TicTacToeGame(int[] board, boolean xIsHuman, boolean oIsHuman) {
        if(board == null) {
            this.board = new int[]{
                    EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY,
                    EMPTY, EMPTY, EMPTY,
            };
        } else {
            this.board = board;
        }
        this.xIsHuman = xIsHuman;
        this.oIsHuman = oIsHuman;

        System.out.println("==");
        System.out.println(xIsHuman);
        System.out.println(oIsHuman);
        System.out.println("==");
    }

    public int[] xTurn(int square) {
        board[square] = X;
        return board;
    };

    public int[] oTurn(int square) {
        board[square] = O;
        return board;
    }

    public int[] computerTurn() {
        // Pick an empty spot
        int rand = new Random().nextInt(9);
        while (board[rand] != EMPTY) {
            rand = new Random().nextInt(9);
        }
        // Attempt to take a turn
        if (!xIsHuman) {
            xTurn(rand);
        } else if (!oIsHuman) {
            oTurn(rand);
        }
        return board;
    }

    public boolean xWon() {
        return X == winner();
    }

    public boolean oWon() {
        return O == winner();
    }

    public boolean draw() {
        return DRAW == winner();
    }

    private int winner() {
        int[][] winCases = new int[][]{
                {0, 1, 2},
                {0, 4, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {2, 4, 6},
                {3, 4, 5},
                {6, 7, 8},
        };
        // Check for a winner
        for (int[] winCase : winCases) {
            if (board[winCase[0]] == board[winCase[1]] && board[winCase[0]] == board[winCase[2]]) {
                if (board[winCase[0]] != EMPTY) {
                    return board[winCase[0]];
                }
            }
        }
        // Check for a draw
        for(int square : board) {
            if (square == EMPTY) {
                return EMPTY;
            }
        }
        return DRAW;
    }
}
