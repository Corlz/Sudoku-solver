import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    /*format for in
    string[] arr = line.split(" "); splits the string at spaces
     */
    public static void main(String[] args) {
        Reader file = new Reader(1);
        Solver solver = new Solver(file.getAns());
        int[][] ans = solver.masterMethod();
        for (int r = 0; r < ans.length; r++) {
            for (int c = 0; c < ans[0].length; c++) {
                System.out.print(ans[r][c] + " ");
            }
            System.out.println();
        }
    }
}
