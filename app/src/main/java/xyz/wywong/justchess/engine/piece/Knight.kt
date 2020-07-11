package xyz.wywong.justchess.engine.piece

import android.graphics.Bitmap
import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece

class Knight(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    override val score: Int = 3
    override fun updateLocation(coordinate: Coordinate): Piece {
        return Knight(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        val possibleDestinations: ArrayList<Coordinate> = ArrayList()
        possibleDestinations.addAll(topDestinations())
        possibleDestinations.addAll(rightDestinations())
        possibleDestinations.addAll(bottomDestinations())
        possibleDestinations.addAll(leftDestinations())
        return possibleDestinations
    }

    private fun topDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x + 1,
                location.y - 2
            ),
            Coordinate(
                location.x - 1,
                location.y - 2
            )
        )
    }

    private fun rightDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x + 2,
                location.y - 1
            ),
            Coordinate(
                location.x + 2,
                location.y + 1
            )
        )
    }

    private fun bottomDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x + 1,
                location.y + 2
            ),
            Coordinate(
                location.x - 1,
                location.y + 2
            )
        )
    }

    private fun leftDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x - 2,
                location.y + 1
            ),
            Coordinate(
                location.x - 2,
                location.y - 1
            )
        )
    }
}