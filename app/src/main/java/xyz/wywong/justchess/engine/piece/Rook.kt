package xyz.wywong.justchess.engine.piece

import android.graphics.Bitmap
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece
import xyz.wywong.justchess.engine.movement.PlusBehavior

class Rook(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    override val score: Int = 5
    private val plusBehavior: PlusBehavior = PlusBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Rook(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        return plusBehavior.possibleMoves(board)
    }
}