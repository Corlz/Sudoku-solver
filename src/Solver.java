


public class Solver {
    private int[][] startconfig; // strating configuration of the puzzle
    private Cell[][] cells;

    public Solver(int[][] startconfig) {
        this.startconfig = startconfig;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c] = new Cell(startconfig[r][c]);//sets
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
        boolean [] remaining = new boolean[9];//numbers remaining that aren't posibilities in other cells
        for (int i = 0; i < 9; i++) {
            remaining[i] = cells[r][c].getPos()[i];// this is going to point tothe same peice of ram how do I get them to point to diffrent places?
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
        }
    }
}