package xyz.wywong.justchess.engine.piece

import android.graphics.Bitmap
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece
import xyz.wywong.justchess.engine.piece.movement.KnightBehavior

class Knight(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    private val knightBehavior = KnightBehavior(location)

    override val score: Int = 3

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Knight(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        return knightBehavior.possibleMoves(board)
    }
}