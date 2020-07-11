package xyz.wywong.justchess.bot

import xyz.wywong.justchess.Bot
import xyz.wywong.justchess.ChessUtil
import xyz.wywong.justchess.engine.*

/**
 * A simple bot that picks the first valid move set it finds
 */
class FirstBot(
    override val playerId: Int,
    private val pieceFactory: PieceFactory
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

    override fun getPromotedPiece(coordinate: Coordinate, board: Board): Piece {
        return pieceFactory.createPromotedPiece(coordinate, ChessUtil.queenId)
    }
}