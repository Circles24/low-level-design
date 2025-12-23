package com.circles24.checker;

import com.circles24.constants.Symbol;
import com.circles24.entity.Board;

public interface WinChecker {
    Symbol getWinner(Board board);
}
