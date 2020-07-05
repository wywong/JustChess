package com.example.justchess

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Move
import com.example.justchess.engine.Piece

interface Bot {
    /**
     * the player id of the bot
     */
    val playerId: Int

    /**
     * returns the bot's next turn given the board state
     */
    fun getNextTurn(board: Board): Collection<Move>

    /**
     * returns the piece that this bot chooses for promotion
     */
    fun getPromotedPiece(coordinate: Coordinate, board: Board): Piece
}