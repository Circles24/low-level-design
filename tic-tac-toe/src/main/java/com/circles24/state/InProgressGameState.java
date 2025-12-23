package com.circles24.state;

import com.circles24.entity.Game;
import com.circles24.entity.Move;

public class InProgressGameState implements GameState {
    @Override
    public void handleMove(Game game, Move move) {
        // validate if move is valid
        // correct player
        // position is empty

        // pass the move to game
        // ask the game to check for winner
        // if winner modify state to winner state
    }
}
