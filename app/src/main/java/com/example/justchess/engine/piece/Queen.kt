package com.example.justchess.engine.piece

import android.graphics.Bitmap
import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece
import com.example.justchess.engine.piece.movement.DiagonalBehavior
import com.example.justchess.engine.piece.movement.PlusBehavior

class Queen(
    override val location: Coordinate,
    override val playerId: Int,
    override val image: Bitmap?
) : BasePiece() {
    private val diagonalBehavior = DiagonalBehavior(location)
    private val plusBehavior = PlusBehavior(location)

    override fun updateLocation(coordinate: Coordinate): Piece {
        return Queen(coordinate, playerId, image)
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        val moves = arrayListOf<Coordinate>()
        moves.addAll(diagonalBehavior.possibleMoves(board))
        moves.addAll(plusBehavior.possibleMoves(board))
        return moves
    }
}