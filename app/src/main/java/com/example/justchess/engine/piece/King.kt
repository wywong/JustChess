package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate

class King(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?
) : BasePiece() {
    override fun updateLocation(coordinate: Coordinate): King {
        return King(coordinate, playerId, image)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
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
        return possibilities
    }

}