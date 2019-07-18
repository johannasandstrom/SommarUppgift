package cardDeckClasses;

public class Position {
    private int row;
    private int col;
    public final int MAX_ROWS_COLS = 7;
    private boolean isPlacedInPyramid = false;

    public Position(){
        if(!isPlacedInPyramid){
            int row = -1;
            int col = -1;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isPlacedInPyramid() {
        return isPlacedInPyramid;
    }

    public void setPlacedInPyramid(boolean placedInPyramid) {
        isPlacedInPyramid = placedInPyramid;
    }
}
