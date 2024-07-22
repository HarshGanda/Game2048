package org.machinecoding.models;

public class Cell {
    int row;
    int col;
    CellState cellState;
    int value;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
        this.value = 0;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void printCell(){
        if(this.cellState.equals(CellState.EMPTY)){
            System.out.print("| - |");
        } else if (this.cellState.equals(CellState.FILLED)){
            System.out.print("| " + this.value + " |");
        }
    }
}
