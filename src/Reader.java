import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private int[][] ans;//the read file number array
    private int filenum;//file number

    public Reader(int filenum) {
        ans = new int[9][9];
        this.filenum = filenum;
        try {
            File file = new File("./res/s0" + filenum + "a.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int r = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] row = line.split(" ");

                if(r == 9)
                    break;
                for (int c = 0; c < row.length; c++) {
                    ans[r][c] = Integer.parseInt(row[c]);
                }
                r++;
            }
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public int[][] getAns() {
        return ans;
    }
}
