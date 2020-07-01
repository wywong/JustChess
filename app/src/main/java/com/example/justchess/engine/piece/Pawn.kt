package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

class Pawn(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    private val moved: Boolean
) : BasePiece() {

    override fun updateLocation(coordinate: Coordinate): Pawn {
        return Pawn(coordinate, playerId, image, true)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        val possibleDestinations: ArrayList<Coordinate> = ArrayList()
        if (canMoveForwardTwice(board)) {
            possibleDestinations.add(forwardTwice())
        }
        possibleDestinations.add(leftDiagonal())
        if (canMoveForward(board)) {
            possibleDestinations.add(forward())
        }
        possibleDestinations.add(rightDiagonal())
        return possibleDestinations
    }

    private fun leftDiagonal(): Coordinate {
        return Coordinate(
            location.x - 1,
            location.y + yDelta()
        )
    }

    private fun canMoveForward(board: Board): Boolean {
        return board.getPiece(
            Coordinate(
                location.x,
                location.y + yDelta()
            )
        ) == null
    }

    private fun forward(): Coordinate {
        return Coordinate(
            location.x,
            location.y + yDelta()
        )
    }

    private fun canMoveForwardTwice(board: Board): Boolean {
        return !moved && this.canMoveForward(board)
    }

    private fun forwardTwice(): Coordinate {
        return Coordinate(
            location.x,
            location.y + 2 * yDelta()
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
