import java.awt.*;
import java.util.ArrayList;

public class Solver {
    public Cell[][] cells; //
    private int[][] endingConfig;
    private int compFinished;
    private ArrayList<Point> randCells;//contains points with corisponding x,y locations to the cells that were set randomly

    public Solver(int[][] startconfig) {
        cells = new Cell[9][9];
        randCells = new ArrayList<Point>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c] = new Cell(startconfig[r][c]);
            }
        }
        endingConfig = new int[9][9];
    }

    public int[][] masterMethod(){
        eliminate();

//        while(compFinished < 80){
//            compFinished = 0;
        for (int i = 0; i < 100; i++) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {

                    threeByThreeChecker(r, c);
                    rowChecker(r, c);
                    colChecker(r, c);
                    if(cells[r][c].isFinished()){
                        compFinished+=1;
                    }
                }
            }
        }
        randomCell(0,0,0);

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                endingConfig[r][c] = cells[r][c].getAns();
                //System.out.print(cells[r][c] + " ");
            }
            System.out.println();
        }



        return endingConfig;
    }

    public void threeByThreeEliminator(int r, int c){
        int whichBoxC = c/3; int whichBoxR = r/3;

        if(!cells[r][c].isFinished()){
            for (int row = whichBoxR*3; row < ((whichBoxR+1)*3); row++) {
                for (int col = whichBoxC*3; col < ((whichBoxC+1)*3); col++) {
                    if (cells[row][col].isFinished()){
                        cells[r][c].remove(cells[row][col].getAns());
                    }
                }
            }
        }
    }

    public void threeByThreeChecker(int r, int c){
        if (!cells[r][c].isFinished()){
            int whichBoxC = c/3; int whichBoxR = r/3;
            boolean[] remaining = new boolean[9];

            for (int i = 0; i < remaining.length; i++) {
                remaining[i] = cells[r][c].getPos()[i];
            }


            for (int i = 0; i < remaining.length; i++) {
                for (int row = whichBoxR*3; row < ((whichBoxR+1)*3); row++) {
                    for (int col = whichBoxC*3; col < ((whichBoxC+1)*3); col++) {
                        if(cells[r][c]!= cells[row][col]){
                            if(cells[row][col].getPos()[i]){
                                remaining[i] = false;
                            }
                        }
                    }
                }
            }

            int numPos = 0;//number of posibilities left
            int lastPos = -1; // most recent posibility
            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i]){
                    numPos++;
                    lastPos = i;
                }
            }
            if (numPos == 1){
                cells[r][c].setAns(lastPos+1);
                eliminate();
            }
        }
    }

    public void eliminate(){
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                threeByThreeEliminator(r,c);
                rowEliminate(r, c);
                colEliminate(r, c);
            }
        }
    }

    public void rowEliminate(int r, int c){//eliminates the posibilities of the finished cells in that row
        if (!cells[r][c].isFinished()){
            for (int col = 0; col < 9; col++) {
                if (cells[r][col].isFinished()){
                    cells[r][c].remove(cells[r][col].getAns());
                }
                if (cells[r][c].isFinished()){
                    col=9;
                }
            }
        }
    }

    public void colEliminate(int r, int c){//eliminates the posibilities of the finished cells in that col
        if (!cells[r][c].isFinished()){
            for (int row = 0; row < 9; row++) {
                if (cells[row][c].isFinished()){
                    cells[r][c].remove(cells[row][c].getAns());
                }
                if (cells[r][c].isFinished()){
                    row=9;
                }
            }
        }
    }

    public void rowChecker (int r, int c){
        if (!cells[r][c].isFinished()){
            boolean [] remaining = new boolean[9];//numbers remaining that aren't posibilities in other cells
            for (int i = 0; i < 9; i++) {
                remaining[i] = cells[r][c].getPos()[i];
            }
            for (int col = 0; col < 9; col++) {
                if (col != c && !cells[r][col].isFinished()){
                    for (int i = 0; i < 9; i++) {
                        if (cells[r][col].getPos()[i]){
                            remaining[i] = false;
                        }
                    }
                }else if (cells[r][col].isFinished()){
                    remaining[cells[r][col].getAns()-1] = false;
                    cells[r][c].remove(cells[r][col].getAns());
                }
            }
            int numPos = 0;//number of posibilities left
            int lastPos = -1; // most recent posibility
            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i]){
                    numPos++;
                    lastPos = i;
                }
            }
            if (numPos == 1){
                cells[r][c].setAns(lastPos+1);
                eliminate();
            }
        }
    }

    public void colChecker (int r, int c){
        if (!cells[r][c].isFinished()){
            boolean [] remaining = new boolean[9];//numbers remaining that aren't posibilities in other cells
            for (int i = 0; i < 9; i++) {
                remaining[i] = cells[r][c].getPos()[i];
            }
            for (int row = 0; row < 9; row++) {
                if (row != r && !cells[row][c].isFinished()){
                    for (int i = 0; i < 9; i++) {
                        if (cells[row][c].getPos()[i]){
                            remaining[i] = false;
                        }
                    }
                }else if (cells[row][c].isFinished()){
                    remaining[cells[row][c].getAns()-1] = false;
                    cells[r][c].remove(cells[row][c].getAns());
                }
            }
            int numPos = 0;//number of posibilities left
            int lastPos = -1; // index of most recent posibility
            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i]){
                    numPos++;
                    lastPos = i;
                }
            }
            if (numPos == 1){
                cells[r][c].setAns(lastPos+1);
                eliminate();
            }
        }
    }

    //From here down is stuff inorder to solve leftover stuff

    public boolean checkCell(int r,int c){//checks a cell to see if the answer in it obey the rules. Returns a boolean
        if (!cells[r][c].isFinished()){
            return false;
        }
        for (int row = 0; row < 9; row++) {
            if (row!=r){
                if (cells[r][c].getAns() == cells[row][c].getAns()){
                    return false;
                }
            }
        }
        for (int col = 0; col < 9; col++) {
            if (col!=c){
                if (cells[r][c].getAns() == cells[r][col].getAns()){
                    return false;
                }
            }
        }
        int whichBoxC = c/3;
        int whichBoxR = r/3;
        for (int row = whichBoxR*3; row < ((whichBoxR+1)*3); row++) {
            for (int col = whichBoxC * 3; col < ((whichBoxC + 1) * 3); col++) {
                if (row!=r&&col!=c){
                    if (cells[r][c].getAns() == cells[row][col].getAns()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkAll(){//check all cells to see if they are correct.
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!checkCell(r,c)){
                    return false;
                }
            }
        }
        return true;
    }

    public void random(){

//        for (int i = 0; i < 1000; i++) {
//            Point next = randomCell()
//        }
    }

    public void randomCell(int r, int c, double etempt){
        int nextR;
        int nextC;
        if (c == 8){
            nextR = r+1;
            nextC = 0;
        }else{
            nextR = r;
            nextC = c+1;
        }
        if (!cells[r][c].isFinished()){
            for (int i = 0; i < 9; i++) {
                if (cells[r][c].getPos()[i]){
                    cells[r][c].setAns(i+1);
                    if (checkCell(r,c)){
                        i=10;
                        randCells.add(new Point(r,c));
                    }
                }
            }
            if (!cells[r][c].isFinished()){//resets cells that were random if none of the possibilities worked
                for (int i = 0; i < randCells.size(); i++) {
                    cells[randCells.get(i).x][randCells.get(i).y].setAns(0);
                }
                nextC = 0;
                nextR = 0;
            }
        }
        if ((r<=8&&nextC<9)||etempt<10000000000000000000.0){
            if (nextR != 9 && nextC != 9)
                randomCell(nextR, nextC, etempt+1);
        }

    }
}
