package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

class King(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    private val moved: Boolean
) : BasePiece(moved) {
    override fun updateLocation(coordinate: Coordinate): King {
        return King(coordinate, playerId, image, true)
    }

    override fun getPossible(board: Board): Collection<Coordinate> {
        val possibilities = hashSetOf<Coordinate>()
        for (xDelta in (-1..1)) {
            for (yDelta in (-1..1)) {
                possibilities.add(
                    Coordinate(
                        location.x + xDelta,
                        location.y + yDelta
                    )
                )
            }
        }
        possibilities.remove(location)
        val castleLeftCoordinate: Coordinate? = castleLeft(board)
        if (castleLeftCoordinate != null) {
            possibilities.add(castleLeftCoordinate)
        }
        val castleRightCoordinate: Coordinate? = castleRight(board)
        if (castleRightCoordinate != null) {
            possibilities.add(castleRightCoordinate)
        }
        return possibilities
    }

    private fun castleLeft(board: Board): Coordinate? {
        if (canCastle(board)) {
            val left1: Piece? = board.getPiece(
                Coordinate(location.x - 1, location.y)
            )
            val left2: Piece? = board.getPiece(
                Coordinate(location.x - 2, location.y)
            )
            val left3: Piece? = board.getPiece(
                Coordinate(location.x - 3, location.y)
            )
            val left4: Piece? = board.getPiece(
                Coordinate(location.x - 4, location.y)
            )
            val gapEmpty: Boolean = left1 == null && left2 == null && left3 == null
            val hasRookNotMoved: Boolean = left4 != null && !left4.hasMoved()
            if (gapEmpty && hasRookNotMoved) {
                return Coordinate(location.x - 2, location.y)
            }
        }
        return null
    }

    private fun castleRight(board: Board): Coordinate? {
        if (canCastle(board)) {
            val right1: Piece? = board.getPiece(
                Coordinate(location.x + 1, location.y)
            )
            val right2: Piece? = board.getPiece(
                Coordinate(location.x + 2, location.y)
            )
            val right3: Piece? = board.getPiece(
                Coordinate(location.x + 3, location.y)
            )
            val gapEmpty: Boolean = right1 == null && right2 == null
            val hasRookNotMoved: Boolean = right3 != null && !right3.hasMoved()
            if (gapEmpty && hasRookNotMoved) {
                return Coordinate(location.x + 2, location.y)
            }
        }
        return null
    }

    private fun canCastle(board: Board): Boolean {
        return !moved && !board.isKingInCheck(playerId)
    }
}