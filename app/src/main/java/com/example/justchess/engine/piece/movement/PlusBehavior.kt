package com.example.justchess.engine.piece.movement

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

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