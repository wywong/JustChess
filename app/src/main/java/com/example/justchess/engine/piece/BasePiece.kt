package com.example.justchess.engine.piece

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

abstract class BasePiece : Piece {

    override fun getValidDestinations(board: Board): Collection<Coordinate> {
        val validDestinations: ArrayList<Coordinate> = ArrayList()
        val possibleDestinations: Collection<Coordinate> = getPossibleDestinations(board)
        for (targetCoordinate in possibleDestinations) {
            if (this.isMoveLegal(targetCoordinate, board)) {
                validDestinations.add(targetCoordinate)
            }
        }
        return validDestinations
    }

    protected abstract fun getPossibleDestinations(board: Board): Collection<Coordinate>

    private fun isMoveLegal(targetCoordinate: Coordinate, board: Board): Boolean {
        return if (Coordinate.inBounds(targetCoordinate)) {
            val targetPiece: Piece? = board.getPiece(targetCoordinate)
            if (targetPiece?.playerId == this.playerId) {
                return false
            }
            val prospectiveBoard = board.movePiece(this, targetCoordinate)
            !prospectiveBoard.isKingInCheck(this.playerId)
        } else {
            false
        }
    }

}