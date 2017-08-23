import ecs100.*;
import java.awt.Color;
import java.util.Random;
import java.util.Arrays;
import java.util.Stack;

public class Board2048withUndo {

    private final int LIMIT = 7; // to determine the ration of twos over fours (the closer to 10 the more twos)
    private final int TARGET = 2048;

    private final int CELL;
    private int [][] mainArray;

    Stack <int [][]> myNums = new Stack <int [][]> ();

    public Board2048withUndo (int size) {
        CELL = size;
        mainArray = new int [CELL][CELL];
    }

    /** Return whether the magic target number has been achieved (greater than)*/
    public boolean hasReachedTarget() {
        for (int row=0; row<CELL; row++) {
            for (int col=0; col<CELL; col++) {
                if (mainArray[row][col] >= TARGET) return true;
            }
        }
        return false;
    }

    /** Return whether the game is over (true) or not (false) 
    If there is some room available, the game is not over.
    If there is no room available, need to check whether some adjacent tiles hold the same value
     */
    public boolean isGameOver() {
        /*# YOUR CODE HERE */
        for (int row=0; row<CELL; row++) {
            for (int col=0; col<CELL; col++) {
                if (mainArray[row][col] == 0) return false;
            }
        }

        //means no room left
        for (int row=1; row<CELL-1; row++) {
            for (int col=1; col<CELL-1; col++) {
                if (mainArray[row][col]==mainArray[row][col+1] || mainArray[row][col]==mainArray[row][col-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Return the number of empty tiles */
    private int numEmptyTiles() {
        int num = 0;
        for (int row=0; row<CELL; row++) {
            for (int col=0; col<CELL; col++) {
                if (mainArray[row][col] == 0) num++;
            }
        }
        return num;
    }

    /** Insert a random number (either 2 or 4) at a randon empty tile.
    Note that 7/10 times the number should be 2.
    An empty tile is one which holds the value 0
     */
    public void insertRandomTile() {
        if (numEmptyTiles() != 0) {
            int num;
            Random rand = new Random();
            int result = rand.nextInt(10);
            if(result<LIMIT) {
                //70% chance this would happen
                num = 2;
            }
            else {
                //30% chance this would happen
                num = 4;
            }

            boolean generated = false;
            while (generated == false) {
                int row = new Random().nextInt(5);
                int col = new Random().nextInt(5);
                if (mainArray[row][col]== 0) {
                    mainArray[row][col]=num;
                    generated = true;
                }
            }
        }
    }

    /** Move the tiles left. 
    Each time 2 tiles with the same number touch, the number are added and the two tiles merge on 
    the left side. An empty tile is then added on the right hand side of the board.
     */
    public void left() {
        for (int row=0; row<CELL; row++) {//
            int [] rowArray = new int [CELL];
            int rowArrCounter = 0;
            for (int col=0; col<CELL; col++) {
                if (mainArray[row][col] != 0) {
                    rowArray[rowArrCounter] = mainArray[row][col];
                    rowArrCounter++;
                }
            }

            for (int col=0; col<CELL; col++) {//
                mainArray[row][col] = rowArray[col];
            }

            for (int col=0; col<CELL-1; col++) {
                if (mainArray[row][col] == mainArray[row][col+1]) {
                    mainArray[row][col] += mainArray[row][col+1];
                    mainArray[row][col+1] = 0;
                }
            }

            Arrays.fill(rowArray, 0);
            rowArrCounter = 0;
            for (int col=0; col<CELL; col++) {
                if (mainArray[row][col] != 0) {
                    rowArray[rowArrCounter] = mainArray[row][col];
                    rowArrCounter++;
                }
            }

            for (int col=0; col<CELL; col++) {//
                mainArray[row][col] = rowArray[col];
            }
        }
    }

    /** Move the tiles right. 
    Each time 2 tiles with the same number touch, the number are added and the two tiles merge on 
    the right side. An empty tile is then added on the left hand side of the board.
     */
    public void right() {
        for (int row=0; row<CELL; row++) {//
            int [] rowArray = new int [CELL];
            int rowArrCounter = CELL-1;
            for (int col=CELL-1; col>=0; col--) {
                if (mainArray[row][col] != 0) {
                    rowArray[rowArrCounter] = mainArray[row][col];
                    rowArrCounter--;
                }
            }

            for (int col=0; col<CELL; col++) {//
                mainArray[row][col] = rowArray[col];
            }

            for (int col=CELL-1; col>0; col--) {
                if (mainArray[row][col] == mainArray[row][col-1]) {
                    mainArray[row][col] += mainArray[row][col-1];
                    mainArray[row][col-1] = 0;
                }
            }

            Arrays.fill(rowArray, 0);
            rowArrCounter = CELL-1;
            for (int col=CELL-1; col>=0; col--) {
                if (mainArray[row][col] != 0) {
                    rowArray[rowArrCounter] = mainArray[row][col];
                    rowArrCounter--;
                }
            }

            for (int col=0; col<CELL; col++) {//
                mainArray[row][col] = rowArray[col];
            }
        }
    }

    /** Move the tiles up. 
    Each time 2 tiles with the same number touch, the number are added and the two tiles merge on 
    the up side. An empty tile is then added on the down side of the board.
     */
    public void up () {
        for (int col=0; col<CELL; col++) {//
            int [] colArray = new int [CELL];
            int colArrCounter = 0;
            for (int row=0; row<CELL; row++) {
                if (mainArray[row][col] != 0) {
                    colArray[colArrCounter] = mainArray[row][col];
                    colArrCounter++;
                }
            }

            for (int row=0; row<CELL; row++) {//
                mainArray[row][col] = colArray[row];
            }

            for (int row=0; row<CELL-1; row++) {
                if (mainArray[row][col] == mainArray[row+1][col]) {
                    mainArray[row][col] += mainArray[row+1][col];
                    mainArray[row+1][col] = 0;
                }
            }

            Arrays.fill(colArray, 0);
            colArrCounter = 0;
            for (int row=0; row<CELL; row++) {
                if (mainArray[row][col] != 0) {
                    colArray[colArrCounter] = mainArray[row][col];
                    colArrCounter++;
                }
            }

            for (int row=0; row<CELL; row++) {//
                mainArray[row][col] = colArray[row];
            }
        }
    }

    /** Move the tiles right. 
    Each time 2 tiles with the same number touch, the number are added and the two tiles merge on 
    the right side. An empty tile is then added on the left hand side of the board.
     */
    public void down() {
        for (int col=0; col<CELL; col++) {//
            int [] colArray = new int [CELL];
            int colArrCounter = CELL-1;
            for (int row=CELL-1; row>=0; row--) {
                if (mainArray[row][col] != 0) {
                    colArray[colArrCounter] = mainArray[row][col];
                    colArrCounter--;
                }
            }

            for (int row=0; row<CELL; row++) {//
                mainArray[row][col] = colArray[row];
            }

            for (int row=CELL-1; row>0; row--) {
                if (mainArray[row][col] == mainArray[row-1][col]) {
                    mainArray[row][col] += mainArray[row-1][col];
                    mainArray[row-1][col] = 0;
                }
            }

            Arrays.fill(colArray, 0);
            colArrCounter = CELL-1;
            for (int row=CELL-1; row>=0; row--) {
                if (mainArray[row][col] != 0) {
                    colArray[colArrCounter] = mainArray[row][col];
                    colArrCounter--;
                }
            }

            for (int row=0; row<CELL; row++) {//
                mainArray[row][col] = colArray[row];
            }
        }
    }

    public void undoStack () {
        int [][] undo = new int [CELL][CELL];

        for (int row = 0; row <CELL; row++) {
            for (int col = 0; col < CELL; col++) {
                undo[row][col]= mainArray[row][col];
            }
        }

        myNums.push(undo);
    }

    public void undo () {
        if (!myNums.empty()) {
            int [][] undo = new int [CELL][CELL];
            undo = myNums.pop();

            for (int row = 0; row <CELL; row++) {
                for (int col = 0; col < CELL; col++) {            
                    mainArray[row][col] = undo[row][col];
                }
            }
        }
    }

    public String toString() {
        String ans = "  ";
        for (int row = 0; row < CELL; row++) {
            for (int col = 0; col < CELL; col++) {
                ans += mainArray[row][col];
            }
        }
        return ans;
    }

    // layout of the board
    private final int boardLeft = 80;    // left edge of the board
    private final int boardTop = 40;     // top edge of the board
    private final int tileSize = 50;     // width of tiles in the board

    public void redraw() {
        UI.clearGraphics();
        for (int row = 0; row < CELL; row++) {
            for (int col = 0; col < CELL; col++) {
                drawTile(row, col);
            }
        }
        UI.repaintGraphics();
    }

    private void drawTile(int row, int col) {
        int shiftBy = 3;
        double left = boardLeft+col*tileSize;
        double top = boardTop+row*tileSize;

        // Fill the rectangle with a colour matching the value of the tile
        UI.setColor(getColor(mainArray[row][col]));
        UI.fillRect(left,top,tileSize,tileSize);

        // Outline the rectangle
        UI.setColor(Color.black);
        UI.drawRect(left,top,tileSize,tileSize);

        // Display the number
        UI.setFontSize(20);
        if (mainArray[row][col] == 0) return;
        if (mainArray[row][col] >= 16) UI.setColor(Color.white);
        double x = left + tileSize * 0.3;
        double y = top + tileSize * 0.6;
        UI.drawString(""+mainArray[row][col], x, y);
    }

    private Color getColor(int value) {
        switch (value) {
            case 0 : { return Color.white; }     
            case 2 : { return Color.gray; }    
            case 4 : { return Color.orange; }  
            case 8 : { return Color.red; }   
            case 16 : { return Color.cyan; }     
            case 32 : { return Color.blue; }
            case 64 : { return Color.green; }
            default: {return Color.black;}
        }
    }
}