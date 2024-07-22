package org.machinecoding.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    List<List<Cell>> grid;

    public Board() {
        this.size = 4;
        this.grid = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < 4; j++){
                grid.get(i).add(new Cell(i, j));
            }
        }
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void display(){
        for(List<Cell> x : grid) {
            for(Cell y : x) {
                y.printCell();
            }
            System.out.println();
        }
    }
}
