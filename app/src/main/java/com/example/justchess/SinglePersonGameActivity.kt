package com.example.justchess

import com.example.justchess.bot.FirstBot
import com.example.justchess.controller.SinglePersonGameController
import com.example.justchess.engine.Game

class SinglePersonGameActivity : ChessGameActivity() {
    override fun buildController(game: Game): GameController {
        return SinglePersonGameController(game, FirstBot(1))
    }
}