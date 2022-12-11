import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final char[][] board = new char[ROW][COL];
    private static char currentPlayer;



    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int move;

        int row, col;

        boolean gameOver;

        boolean validMove;

        do {
            clearBoard();

            currentPlayer = 'X';

            move = 0;

            gameOver = false;

            do {
                display();
                do {
                    row = SafeInput.getRangedInt(scanner, "Please enter the row coordinate for your move", 1, 3) - 1;

                    col = SafeInput.getRangedInt(scanner, "Please enter the column coordinate for your move", 1, 3) - 1;

                    validMove = isValidMove(row, col);

                    if(!validMove)
                    {
                        System.out.println("That's not a valid move, try again.");
                    }
                } while(!validMove);

                board[row][col] = currentPlayer;

                move++;

                if(move >= 5)
                {
                    if(isWin(currentPlayer))
                    {
                        gameOver = true;

                        System.out.println(currentPlayer + " wins!");
                    }
                }

                if(move >= 7 && !gameOver)
                {
                    if(isTie())
                    {
                        gameOver = true;

                        System.out.println("The game is a tie!");
                    }
                }

                if(currentPlayer == 'X')
                {
                    currentPlayer = 'O';
                }
                else
                {
                    currentPlayer = 'X';
                }

            }while(!gameOver);

        }while(SafeInput.getYNConfirm(scanner, "Do you want to play again?"));


    }

    private static void clearBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = ' ';
            }
        }
    }

    private static void display()
    {
        for(int i = 0; i < board.length; i++)
        {
            System.out.print(" | ");
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j]);

                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        boolean validMove = false;

        if(board[row][col] == ' ')
        {
            validMove = true;
        }
        return validMove;
    }

    private static boolean isWin(char player)
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(char player)
    {
        boolean hasWon = false;

        for(int i = 0; i < board.length && !hasWon; i++)
        {
            if((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player))
            {
                hasWon = true;
            }
        }
        return hasWon;
    }

    private static boolean isRowWin(char player)
    {
        boolean hasWon = false;

        for(int i = 0; i < board.length && !hasWon; i++)
        {
            if((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player))
            {
                hasWon = true;
            }
        }
        return hasWon;
    }

    private static boolean isDiagonalWin(char player)
    {
        boolean hasWon = false;

        if((board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player))
        {
            hasWon = true;
        }
        if((board[0][2] == player) && (board[1][1] == player) && (board[2][0] == player))
        {
            hasWon = true;
        }
        return hasWon;
    }

    private static boolean isTie()
    {
        boolean isTie = false;

        for(char[] row : board)
        {
            if(row[0] == 'X' || row[1] == 'X' || row[2] == 'X')
            {
                if(row[0] == 'O' || row[1] == 'O' || row[2] == 'O')
                {
                    isTie = true;
                }
            }
        }

        for(int i = 0; i < board.length && !isTie; i++)
        {
            if((board[0][i] == 'X')|| (board[1][i] == 'X') || (board[2][i] == 'X'))
            {
                if((board[0][i] == 'O')|| (board[1][i] == 'O') || (board[2][i] == 'O'))
                {
                    isTie = true;
                }
            }
        }

        if(!isTie)
        {
            if(board[0][0] == 'X' || board[1][1] == 'X' || board[2][2] == 'X')
            {
                if(board[0][0] == 'O' || board[1][1] == 'O' || board[2][2] == 'O')
                {
                    isTie = true;
                }
            }
        }

        if(!isTie)
        {
            if(board[0][2] == 'X' || board[1][1] == 'X' || board[2][0] == 'X')
            {
                if(board[0][2] == 'O' || board[1][1] == 'O' || board[2][0] == 'O')
                {
                    isTie = true;
                }
            }
        }

        return isTie;
    }
}

