package com.circles24.checker;

import com.circles24.constants.Symbol;
import com.circles24.entity.Board;
import com.circles24.entity.Position;

public class ColWinChecker implements WinChecker {
    @Override
    public Symbol getWinner(Board board) {
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int i=0; i<columns; i++) {
            Symbol firstSymbol = board.getSymbolAt(new Position(0, i));
            if (firstSymbol == Symbol.EMPTY) {
                continue;
            }

            boolean allMatch = true;

            for (int j=1; j<rows; j++) {
                if (board.getSymbolAt(new Position(j, i)) != firstSymbol) {
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
