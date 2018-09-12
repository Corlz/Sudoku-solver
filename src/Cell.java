import java.util.ArrayList;

public class Cell {
    private boolean[] pos;//array of possibilities if one false it is no longer a possibility
    private int ans;//definite answer
    private boolean finished;

    public Cell(int ans) {
        if (ans > 0 && ans <= 9){
            this.ans = ans;
            finished =true;
        }else{
            start();
        }
    }

    public Cell() {
        start();
    }

    private void start(){
        ans = 0;
        pos = new boolean[9];
        for (int i = 0; i < 9; i++) {
            pos[i]= true;
        }
        finished = false;
    }

    public void remove(int i){//removes i from the list of possibilities and sets ans if there is only one possibility left.
        pos[i-1]=false;
        int done = 1;
        int indexTrue = -1;
        for (int j = 0; j < pos.length; j++) {
            if (pos[j]= true){
                done--;
                indexTrue = j;
            }
        }
        if (done==0){
            ans = indexTrue+1;
            finished = true;
        }
    }

    public boolean[] getPos() {
        return pos;
    }

    public int getAns() {
        return ans;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setAns(int ans) {
        if (ans > 0 && ans <= 9){
            this.ans = ans;
        }
    }
}
