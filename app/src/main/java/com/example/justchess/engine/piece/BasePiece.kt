package com.example.justchess.engine.piece

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

abstract class BasePiece : Piece {
    private val tilesPerSide: Int = 8

    override fun getValidDestinations(board: Board): Collection<Coordinate> {
        val validDestinations: ArrayList<Coordinate> = ArrayList()
        val possibleDestinations: Collection<Coordinate> = getPossibleDestinations()
        for (targetCoordinate in possibleDestinations) {
            if (this.isMoveLegal(targetCoordinate, board)) {
                validDestinations.add(targetCoordinate)
            }
        }
        return validDestinations
    }

    protected abstract fun getPossibleDestinations(): Collection<Coordinate>

    protected fun isMoveLegal(targetCoordinate: Coordinate, board: Board): Boolean {
        return if (isCoordinateInBounds(targetCoordinate)) {
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

    private fun isCoordinateInBounds(coordinate: Coordinate): Boolean {
        return (coordinate.x in 0 until tilesPerSide) &&
                (coordinate.y in 0 until tilesPerSide)
    }
}