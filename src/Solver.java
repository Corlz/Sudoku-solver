


public class Solver {
    private Cell[][] cells; //
    private int[][] endingConfig;
    private int compFinished;

    public Solver(int[][] startconfig) {
        cells = new Cell[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c] = new Cell(startconfig[r][c]);
            }
        }
        endingConfig = new int[9][9];
    }

    public int[][] masterMethod(){
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                threeByThreeEliminator(r, c);
                rowEliminate(r, c);
                colEliminate(r, c);
            }
        }

        while(compFinished < 80){
            compFinished = 0;
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    threeByThreeChecker(r, c);
                    rowChecker(r, c);
                    colChecker(r, c);
                    if(cells[r][c].isFinished()){
                        compFinished++;
                    }
                }

            }
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                endingConfig[r][c] = cells[r][c].getAns();
            }
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
        //System.out.println(cells[r][c]);
    }

    public void threeByThreeChecker(int r, int c){
        int whichBoxC = c/3; int whichBoxR = r/3;
        boolean[] remaining = new boolean[9];

        for (int i = 0; i < remaining.length; i++) {
            remaining[i] = cells[r][c].getPos()[i];
        }

        if(!cells[r][c].isFinished()){
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
            rowEliminate(r, c);
            colEliminate(r, c);
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
        boolean [] remaining = new boolean[9];//numbers remaining that aren't posibilities in other cells
        for (int i = 0; i < 9; i++) {
            remaining[i] = cells[r][c].getPos()[i];// this is going to point to the same piece of ram how do I get them to point to diffrent places?
        }
        for (int col = 0; col < 9; col++) {
            if (col != c && !cells[r][col].isFinished()){
                for (int i = 0; i < 9; i++) {
                    if (cells[r][col].getPos()[i]){
                        remaining[i] = false;
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
            colEliminate(r,c);
            threeByThreeEliminator(r, c);
        }
    }

    public void colChecker (int r, int c){
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
            colEliminate(r,c);
            threeByThreeEliminator(r,c);
        }
    }
}
