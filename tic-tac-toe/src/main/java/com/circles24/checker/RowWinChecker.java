package com.circles24.checker;

import com.circles24.constants.Symbol;
import com.circles24.entity.Board;
import com.circles24.entity.Position;

public class RowWinChecker implements WinChecker {
    @Override
    public Symbol getWinner(Board board) {
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int i=0; i<rows; i++) {
            Symbol firstSymbol = board.getSymbolAt(new Position(i, 0));
            if (firstSymbol == Symbol.EMPTY) {
                continue;
            }

            boolean allMatch = true;

            for (int j=1; j<columns; j++) {
                if (board.getSymbolAt(new Position(i, j)) != firstSymbol) {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch) {
                return firstSymbol;
            }
        }

        return Symbol.EMPTY;
    }
}
