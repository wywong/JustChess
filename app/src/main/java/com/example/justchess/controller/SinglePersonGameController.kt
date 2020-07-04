package com.example.justchess.controller

import com.example.justchess.Bot
import com.example.justchess.engine.Game

/**
 * controller for a single person game against a bot
 */
class SinglePersonGameController(
    private val game: Game,
    private val bot: Bot
) : BaseGameController(game) {
    override fun postPlayerMoves() {
        super.postPlayerMoves()
        executeBotMove()
    }

    private fun executeBotMove() {
        game.applyMoves(
            bot.getNextTurn(game.getCurrentBoard())
        )
    }
}