package com.example.justchess

import com.example.justchess.bot.RandomBot
import com.example.justchess.controller.SinglePersonGameController
import com.example.justchess.engine.Game

class SinglePersonGameActivity : ChessGameActivity() {
    override fun buildController(game: Game): GameController {
        return SinglePersonGameController(
            game,
            RandomBot(
                1,
                getGameFactory().blackPieceFactory
            )
        )
    }
}