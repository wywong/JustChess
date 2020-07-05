package com.example.justchess

import com.example.justchess.controller.TwoPersonGameController
import com.example.justchess.engine.Game

class TwoPersonGameActivity : ChessGameActivity() {
    override fun buildController(game: Game): GameController {
        return TwoPersonGameController(
            game
        )
    }
}