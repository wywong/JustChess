package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

class Knight(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    private val moved: Boolean
) : BasePiece(moved) {
    override fun updateLocation(coordinate: Coordinate): Piece {
        return Knight(coordinate, playerId, image, true)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
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
                location.y - 3
            ),
            Coordinate(
                location.x - 1,
                location.y - 3
            )
        )
    }

    private fun rightDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x + 3,
                location.y - 1
            ),
            Coordinate(
                location.x + 3,
                location.y + 1
            )
        )
    }

    private fun bottomDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x + 1,
                location.y + 3
            ),
            Coordinate(
                location.x - 1,
                location.y + 3
            )
        )
    }

    private fun leftDestinations(): Collection<Coordinate> {
        return arrayListOf(
            Coordinate(
                location.x - 3,
                location.y + 1
            ),
            Coordinate(
                location.x - 3,
                location.y - 1
            )
        )
    }
}