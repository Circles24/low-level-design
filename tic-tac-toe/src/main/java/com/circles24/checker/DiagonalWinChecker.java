package com.circles24.checker;

import com.circles24.constants.Symbol;
import com.circles24.entity.Board;
import com.circles24.entity.Position;

public class DiagonalWinChecker implements WinChecker {
    @Override
    public Symbol getWinner(Board board) {
        int rows = board.getRows();
        int columns = board.getColumns();

        // assuming rows and columns are equals
        Symbol firstSymbol = board.getSymbolAt(new Position(0, 0));
        if (firstSymbol == Symbol.EMPTY) {
            return Symbol.EMPTY;
        }

        for (int i=1; i<rows; i++) {
            if (board.getSymbolAt(new Position(i, i)) != firstSymbol) {
                return Symbol.EMPTY;
            }
        }

        return firstSymbol;
    }
}
