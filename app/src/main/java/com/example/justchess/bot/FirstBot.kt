package com.example.justchess.bot

import com.example.justchess.Bot
import com.example.justchess.engine.Board
import com.example.justchess.engine.Move

/**
 * A simple bot that picks the first valid move set it finds
 */
class FirstBot(
    override val playerId: Int
) : Bot {

    override fun getNextTurn(board: Board): Collection<Move> {
        val pieceIterator = board.getPiecesForPlayer(playerId).iterator()
        while (pieceIterator.hasNext()) {
            val piece = pieceIterator.next()
            val validMoves = piece.getValidMoves(board)
            if (validMoves.isNotEmpty()) {
                return validMoves.first()
            }
        }
        return emptyList()
    }
}