import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    /*format for in
    string[] arr = line.split(" "); splits the string at spaces
     */
    public static void main(String[] args) {
        int [][] test = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (r==4 && c==4){
                    test[r][c] = 1;
                }else{
                    test[r][c] = 0;
                }
            }
        }
        Reader file = new Reader(4);
        Solver solver = new Solver(file.getAns());

        for (int r = 0; r < file.getAns().length; r++) {
            for (int c = 0; c < file.getAns()[0].length; c++) {
                System.out.print(file.getAns()[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();


        int[][] ans = solver.masterMethod();
        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                System.out.print(ans[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                System.out.print(solver.cells[r][c] + ", ");
            }
            System.out.println();
        }

        System.out.println(solver.checkAll());
    }
}
