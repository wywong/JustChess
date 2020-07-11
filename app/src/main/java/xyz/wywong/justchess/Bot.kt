package xyz.wywong.justchess

import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Move
import xyz.wywong.justchess.engine.Piece

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