package com.circles24.entity;

import com.circles24.constants.Symbol;

import java.util.Scanner;

public class CLIPlayer implements Player {
    private final String name;
    private final Symbol symbol;
    private final Scanner scanner;

    public CLIPlayer(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public Move getNextMove() {
        int row = scanner.nextInt();
        int col = scanner.nextInt();
    }
}
