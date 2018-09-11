


public class Solver {
    private int[][] startconfig; // strating configuration of the puzzle
    private Cell[][] cells;

    public Solver(int[][] startconfig) {
        this.startconfig = startconfig;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cells[r][c] = new Cell(startconfig[r][c]);
            }
        }
    }
}
