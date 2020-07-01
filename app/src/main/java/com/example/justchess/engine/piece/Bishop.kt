package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece
import com.example.justchess.engine.piece.movement.DiagonalBehavior

class Bishop(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?
) : BasePiece() {
    private val diagonalBehavior: DiagonalBehavior = DiagonalBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Bishop(coordinate, playerId, image)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        return diagonalBehavior.possibleMoves(board)
    }
}