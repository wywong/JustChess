package xyz.wywong.justchess.engine.piece

import android.graphics.Bitmap
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece
import xyz.wywong.justchess.engine.piece.movement.DiagonalBehavior

class Bishop(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    override val score: Int = 3
    private val diagonalBehavior: DiagonalBehavior = DiagonalBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Bishop(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        return diagonalBehavior.possibleMoves(board)
    }
}