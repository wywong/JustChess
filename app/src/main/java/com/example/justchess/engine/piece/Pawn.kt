package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

class Pawn(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    private val moved: Boolean
) : BasePiece(moved) {

    override fun updateLocation(coordinate: Coordinate): Pawn {
        return Pawn(coordinate, playerId, image, true)
    }

    override fun getPossibleCoordinates(board: Board): Collection<Coordinate> {
        val possibleDestinations: ArrayList<Coordinate> = ArrayList()
        if (canMoveForwardTwice(board)) {
            possibleDestinations.add(forwardTwice())
        }
        val topLeft = leftDiagonal(board)
        if (topLeft != null) {
            possibleDestinations.add(topLeft)
        }
        if (canMoveForward(board)) {
            possibleDestinations.add(forward())
        }
        val topRight = rightDiagonal(board)
        if (topRight != null) {
            possibleDestinations.add(topRight)
        }
        return possibleDestinations
    }

    private fun leftDiagonal(board: Board): Coordinate? {
        val coordinate = Coordinate(
            location.x - 1,
            location.y + yDelta()
        )
        val capturePiece = board.getPiece(coordinate)
        if (capturePiece != null && capturePiece.playerId != this.playerId) {
            return coordinate
        }
        return null
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
        return !moved && this.canMoveForward(board) && board.getPiece(
            Coordinate(
                location.x,
                location.y + 2 * yDelta()
            )
        ) == null
    }

    private fun forwardTwice(): Coordinate {
        return Coordinate(
            location.x,
            location.y + 2 * yDelta()
        )
    }

    private fun rightDiagonal(board: Board): Coordinate? {
        val coordinate = Coordinate(
            location.x + 1,
            location.y + yDelta()
        )
        val capturePiece = board.getPiece(coordinate)
        if (capturePiece != null && capturePiece.playerId != this.playerId) {
            return coordinate
        }
        return null
    }

    private fun yDelta(): Int {
        return if (playerId == 0) {
            -1
        } else {
            1
        }
    }

}
