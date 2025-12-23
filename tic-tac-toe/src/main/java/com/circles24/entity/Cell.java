package com.circles24.entity;

import com.circles24.constants.Symbol;

public class Cell {
    private final int row;
    private final int column;
    private Symbol symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.symbol = Symbol.EMPTY;
    }

    public boolean isEmpty() {
        return this.symbol == Symbol.EMPTY;
    }

    public Symbol getSymbol() {
        return this.symbol;
    }
}
