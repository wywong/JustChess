package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece
import com.example.justchess.engine.piece.movement.PlusBehavior

class Rook(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?,
    moved: Boolean
) : BasePiece(moved) {
    private val plusBehavior: PlusBehavior = PlusBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Rook(coordinate, playerId, image, true)
    }

    override fun getPossible(board: Board): Collection<Coordinate> {
        return plusBehavior.possibleMoves(board)
    }
}