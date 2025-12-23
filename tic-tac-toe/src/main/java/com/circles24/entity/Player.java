package com.circles24.entity;

import com.circles24.constants.Symbol;

public interface Player {

    String getName();

    Symbol getSymbol();

    Move getNextMove();
}
