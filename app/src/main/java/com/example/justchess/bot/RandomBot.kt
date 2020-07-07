package com.example.justchess.bot

import com.example.justchess.Bot
import com.example.justchess.ChessUtil
import com.example.justchess.engine.*

/**
 * A bot that picks a move at random
 */
class RandomBot(
    override val playerId: Int,
    private val pieceFactory: PieceFactory
) : Bot {
    override fun getNextTurn(board: Board): Collection<Move> {
        val pieces = board.getPiecesForPlayer(playerId)
        for (piece in pieces.shuffled()) {
            val validMoves = piece.getValidMoves(board)
            if (validMoves.isNotEmpty()) {
                return validMoves.random()
            }
        }
        return emptyList()
    }

    override fun getPromotedPiece(coordinate: Coordinate, board: Board): Piece {
        return pieceFactory.createPromotedPiece(coordinate, ChessUtil.promotionIds.random())
    }
}