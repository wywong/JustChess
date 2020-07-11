package xyz.wywong.justchess.bot

import xyz.wywong.justchess.Bot
import xyz.wywong.justchess.ChessUtil
import xyz.wywong.justchess.engine.*

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