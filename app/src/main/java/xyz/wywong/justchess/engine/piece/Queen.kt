package xyz.wywong.justchess.engine.piece

import android.graphics.Bitmap
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece
import xyz.wywong.justchess.engine.piece.movement.DiagonalBehavior
import xyz.wywong.justchess.engine.piece.movement.PlusBehavior

class Queen(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    override val score: Int = 9
    private val diagonalBehavior = DiagonalBehavior(location)
    private val plusBehavior = PlusBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Queen(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        val moves = arrayListOf<Coordinate>()
        moves.addAll(diagonalBehavior.possibleMoves(board))
        moves.addAll(plusBehavior.possibleMoves(board))
        return moves
    }
}