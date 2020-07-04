package com.example.justchess

import com.example.justchess.engine.Board
import com.example.justchess.engine.Move

interface Bot {
    /**
     * the player id of the bot
     */
    val playerId: Int

    /**
     * returns the bot's next turn given the board state
     */
    fun getNextTurn(board: Board): Collection<Move>
}