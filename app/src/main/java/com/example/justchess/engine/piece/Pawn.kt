package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

class Pawn(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?
) : BasePiece() {

    override fun updateLocation(coordinate: Coordinate): Pawn {
        return Pawn(coordinate, playerId, image)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        val possibleDestinations: ArrayList<Coordinate> = ArrayList()
        possibleDestinations.add(leftDiagonal())
        possibleDestinations.add(forward())
        possibleDestinations.add(rightDiagonal())
        return possibleDestinations
    }

    private fun leftDiagonal(): Coordinate {
        return Coordinate(
            location.x - 1,
            location.y + yDelta()
        )
    }

    private fun forward(): Coordinate {
        return Coordinate(
            location.x,
            location.y + yDelta()
        )
    }

    private fun rightDiagonal(): Coordinate {
        return Coordinate(
            location.x + 1,
            location.y + yDelta()
        )
    }

    private fun yDelta(): Int {
        return if (playerId == 0) {
            -1
        } else {
            1
        }
    }

}
