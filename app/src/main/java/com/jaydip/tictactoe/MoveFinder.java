package com.jaydip.tictactoe;

public class MoveFinder {
    static char opponent = 'o';
    static char player = 'x';

    public static class Move {
        int col;
        int row;
    }

    public static Boolean isMovesLeft(char[][] cArr) {
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                if (cArr[i][i2] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    public static int evaluate(char[][] cArr) {
        for (int i = 0; i < 3; i++) {
            char[] cArr2 = cArr[i];
            char c = cArr2[0];
            char c2 = cArr2[1];
            if (c == c2 && c2 == cArr2[2]) {
                if (c == player) {
                    return 10;
                }
                if (c == opponent) {
                    return -10;
                }
            }
        }
        for (int i2 = 0; i2 < 3; i2++) {
            char c3 = cArr[0][i2];
            char c4 = cArr[1][i2];
            if (c3 == c4 && c4 == cArr[2][i2]) {
                if (c3 == player) {
                    return 10;
                }
                if (c3 == opponent) {
                    return -10;
                }
            }
        }
        char[] cArr3 = cArr[0];
        char c5 = cArr3[0];
        char c6 = cArr[1][1];
        if (c5 == c6 && c6 == cArr[2][2]) {
            if (c5 == player) {
                return 10;
            }
            if (c5 == opponent) {
                return -10;
            }
        }
        char c7 = cArr3[2];
        if (c7 == c6 && c6 == cArr[2][0]) {
            if (c7 == player) {
                return 10;
            }
            if (c7 == opponent) {
                return -10;
            }
        }
        return 0;
    }

    public static int minimax(char[][] cArr, int i, Boolean bool) {
        int evaluate = evaluate(cArr);
        if (evaluate == 10 || evaluate == -10) {
            return evaluate;
        }
        if (!isMovesLeft(cArr).booleanValue()) {
            return 0;
        }
        if (bool.booleanValue()) {
            int i2 = -1000;
            for (int i3 = 0; i3 < 3; i3++) {
                for (int i4 = 0; i4 < 3; i4++) {
                    char[] cArr2 = cArr[i3];
                    if (cArr2[i4] == '_') {
                        cArr2[i4] = player;
                        i2 = Math.max(i2, minimax(cArr, i + 1, Boolean.valueOf(!bool.booleanValue())));
                        cArr[i3][i4] = '_';
                    }
                }
            }
            return i2;
        }
        int i5 = 1000;
        for (int i6 = 0; i6 < 3; i6++) {
            for (int i7 = 0; i7 < 3; i7++) {
                char[] cArr3 = cArr[i6];
                if (cArr3[i7] == '_') {
                    cArr3[i7] = opponent;
                    i5 = Math.min(i5, minimax(cArr, i + 1, Boolean.valueOf(!bool.booleanValue())));
                    cArr[i6][i7] = '_';
                }
            }
        }
        return i5;
    }

    public static Move findBestMove(char[][] cArr) {
        Move move = new Move();
        move.row = -1;
        move.col = -1;
        int i = -1000;
        for (int i2 = 0; i2 < 3; i2++) {
            for (int i3 = 0; i3 < 3; i3++) {
                char[] cArr2 = cArr[i2];
                if (cArr2[i3] == '_') {
                    cArr2[i3] = player;
                    int minimax = minimax(cArr, 0, false);
                    cArr[i2][i3] = '_';
                    if (minimax > i) {
                        move.row = i2;
                        move.col = i3;
                        i = minimax;
                    }
                }
            }
        }
        return move;
    }
}
