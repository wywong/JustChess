package xyz.wywong.justchess.engine.movement

import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate

class PlusBehavior(
    private val origin: Coordinate
) : LineBehavior() {
    fun possibleMoves(board: Board): Collection<Coordinate> {
        val moveCollector: ArrayList<Coordinate> = arrayListOf()
        collectDirection(origin, board, moveCollector, 0, -1)
        collectDirection(origin, board, moveCollector, 0, 1)
        collectDirection(origin, board, moveCollector, -1, 0)
        collectDirection(origin, board, moveCollector, 1, 0)
        return moveCollector
    }
}