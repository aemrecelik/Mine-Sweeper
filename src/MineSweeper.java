import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    static String board[][];
    static int mine;
    static int mineLocation[][];

    void init(int x, int y) {
        board = new String[x][y];
        mineLocation = new int[x][y];
        mine = (x * y) / 4;
        mineGenerator(x, y, mine);
        boardGenerator(x, y);
        System.out.println("Mayınların Konumu");
        printBoardWithMines(x, y);
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");
        startGame(x, y);

    }

    private void startGame(int x, int y) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(x, y);

            System.out.print("Satır Giriniz : \n");
            int row = scanner.nextInt();
            System.out.print("Sütun Giriniz : \n");
            int col = scanner.nextInt();
            if (isMine(row, col)) {
                gameOver(x, y);
                break;
            } else {
                board[row][col] = checkMines(row, col, x, y);
                if (checkWin(x, y)) {
                    gameWin(x, y);
                    break;
                }
            }
        }
    }

    private void gameWin(int x, int y) {
        printBoard(x, y);
        System.out.println("Congratzz You Win!!!");
        System.out.println("=======================");
    }

    private boolean checkWin(int x, int y) {
        int mineCounter = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == "-") {
                    mineCounter++;
                }

            }
        }
        if (mine == mineCounter) {
            return true;
        } else
            return false;
    }

    private String checkMines(int row, int col, int x, int y) {
        int sagUst = (col < y - 1 && row > 0 && mineLocation[row - 1][col + 1] == -1) ? 1 : 0;
        int ust = (row > 0 && mineLocation[row - 1][col] == -1) ? 1 : 0;
        int alt = (row < x - 1 && mineLocation[row + 1][col] == -1) ? 1 : 0;
        int sag = (col < y - 1 && mineLocation[row][col + 1] == -1) ? 1 : 0;
        int sol = (col > 0 && mineLocation[row][col - 1] == -1) ? 1 : 0;
        int sagAlt = (col < y - 1 && row < x - 1 && mineLocation[row + 1][col + 1] == -1) ? 1 : 0;
        int solAlt = (col > 0 && row < x - 1 && mineLocation[row + 1][col - 1] == -1) ? 1 : 0;
        int solUst = (col > 0 && row > 0 && mineLocation[row - 1][col - 1] == -1) ? 1 : 0;


        return String.valueOf(sag + sol + alt + ust + sagAlt + sagUst + solAlt + solUst);
    }

    private void printBoard(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isMine(int row, int col) {
        if (mineLocation[row][col] == -1) {
            return true;
        } else
            return false;
    }

    private void gameOver(int x, int y) {
        printBoardWithMines(x, y);
        System.out.println("Game Over");
    }

    private void printBoardWithMines(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (mineLocation[i][j] == -1) {
                    board[i][j] = "*";
                }
                System.out.print(board[i][j] + " ");
                board[i][j] = "-";
            }
            System.out.println();
        }
        System.out.println("=======================");
    }

    private void boardGenerator(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = "-";
            }
        }
    }

    private void mineGenerator(int x, int y, int mine) {
        Random rand = new Random();
        int mineTemp = mine;
        while (mineTemp > 0) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (mineTemp==0)
                        break;
                    if(mineLocation[i][j]!=-1)
                        mineLocation[i][j] = rand.nextInt() % 2;
                    if (mineLocation[i][j] == -1) {
                        mineTemp--;
                        System.out.println(mineTemp);
                        if(mineTemp==0)
                            break;
                    }
                    if (mineTemp==0)
                        break;
                    else if(mineTemp!=0 && i==-1){
                        i=0;
                        j=0;
                    }
                }
            }
        }
    }
}
