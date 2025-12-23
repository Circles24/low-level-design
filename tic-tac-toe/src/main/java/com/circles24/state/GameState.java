package com.circles24.state;

import com.circles24.entity.Game;
import com.circles24.entity.Move;

public interface GameState {
    void handleMove(Game game, Move move);
}
