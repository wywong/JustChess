package com.example.justchess.engine.piece.movement

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

class DiagonalBehavior(
    private val origin: Coordinate
) : LineBehavior() {
    fun possibleMoves(board: Board): Collection<Coordinate> {
        val moveCollector: ArrayList<Coordinate> = arrayListOf()
        collectDirection(origin, board, moveCollector, -1, -1)
        collectDirection(origin, board, moveCollector, -1, 1)
        collectDirection(origin, board, moveCollector, 1, -1)
        collectDirection(origin, board, moveCollector, 1, 1)
        return moveCollector
    }
}