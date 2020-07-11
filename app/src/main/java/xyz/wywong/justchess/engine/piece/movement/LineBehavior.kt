package xyz.wywong.justchess.engine.piece.movement

import xyz.wywong.justchess.engine.Board
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Piece

abstract class LineBehavior {
    protected fun collectDirection(
        origin: Coordinate,
        board: Board,
        collector: ArrayList<Coordinate>,
        xDelta: Int,
        yDelta: Int
    ) {
        var piece: Piece? = null
        var currentCoordinate = origin
        while (piece == null) {
            currentCoordinate = currentCoordinate.copy(
                x = currentCoordinate.x + xDelta,
                y = currentCoordinate.y + yDelta
            )
            val inBounds = Coordinate.inBounds(currentCoordinate)
            if (inBounds) {
                collector.add(currentCoordinate)
            } else {
                break
            }
            piece = board.getPiece(currentCoordinate)
        }
    }
}