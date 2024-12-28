import java.util.Scanner;

class Tictactoe {
    static char[][] board;

    // Constructor to initialize the board
    public Tictactoe() {
        board = new char[3][3];
        initBoard(); // Initialize the board within the constructor
    }

    // Method to initialize the board with spaces
    static void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Method to display the board
    static void dispBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Method to place a mark on the board
    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (board[row][col] == ' ') { // Check if the spot is empty
                board[row][col] = mark;
            } else {
                System.out.println("Spot already taken");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    static boolean checkColWin() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Method to check for a diagonal win
    static boolean checkCrossWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Inner class for Humanplayer
    static class Humanplayer {
        String name;
        char mark;

        Humanplayer(String name, char mark) {
            this.name = name;
            this.mark = mark;
        }

        void makeMove() {
            Scanner scan = new Scanner(System.in);
            int row;
            int col;

            do {
                System.out.println("Enter the row and col ");
                row = scan.nextInt();
                col = scan.nextInt();
            } while (!isValidMove(row, col));
            Tictactoe.placeMark(row, col, mark);
        }

        boolean isValidMove(int row, int col) {
            if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                return Tictactoe.board[row][col] == ' ';
            }
            return false;
        }
    }

    // Main method 
    public static void main(String[] args) {
        Tictactoe t = new Tictactoe();

        Humanplayer p1 = new Humanplayer("Bagath", 'X');
        Humanplayer p2 = new Humanplayer("Neha", 'O');
        Humanplayer cp = p1;

        while (true) {
            System.out.println(cp.name + ", your turn");
            cp.makeMove();
            Tictactoe.dispBoard();
            if (Tictactoe.checkColWin() || Tictactoe.checkCrossWin() || Tictactoe.checkRowWin()) {
                System.out.println(cp.name + " has won!");
                break;
            } else {
                cp = (cp == p1) ? p2 : p1;
            }
        }
    }
}
