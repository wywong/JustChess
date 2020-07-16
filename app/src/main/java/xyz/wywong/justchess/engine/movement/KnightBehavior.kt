package xyz.wywong.justchess.engine.piece.movement

import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate

class KnightBehavior(private val location: Coordinate) {
    fun possibleMoves(board: Board): Collection<Coordinate> {
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