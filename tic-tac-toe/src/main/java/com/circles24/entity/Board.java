package com.circles24.entity;

import com.circles24.constants.Symbol;

public class Board {
    private final int rows;
    private final int columns;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private final Cell[][] grid;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
    }

    public Symbol getSymbolAt(Position position) {
        return grid[position.getRow()][position.getColumn()].getSymbol();
    }

    public boolean isEmpty(Position position) {
        return grid[position.getRow()][position.getColumn()].isEmpty();
    }
}
