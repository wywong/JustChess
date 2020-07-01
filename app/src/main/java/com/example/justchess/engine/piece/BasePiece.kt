package com.example.justchess.engine.piece

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Piece

abstract class BasePiece(private val moved: Boolean) : Piece {

    override fun hasMoved(): Boolean {
        return moved
    }

    override fun getValidDestinations(board: Board): Collection<Coordinate> {
        return getPossibleDestinations(board)
            .filter{
                coordinate -> isKingCheckedAfterMove(coordinate, board)
            }
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        return getPossible(board).filter {
            coordinate -> isMovePossible(coordinate, board)
        }
    }

    protected abstract fun getPossible(board: Board): Collection<Coordinate>

    private fun isMovePossible(targetCoordinate: Coordinate, board: Board): Boolean {
        return if (Coordinate.inBounds(targetCoordinate)) {
            val targetPiece: Piece? = board.getPiece(targetCoordinate)
            return targetPiece?.playerId != this.playerId
        } else {
            false
        }
    }

    private fun isKingCheckedAfterMove(targetCoordinate: Coordinate, board: Board): Boolean {
        val prospectiveBoard = board.movePiece(targetCoordinate, this)
        return !prospectiveBoard.isKingInCheck(this.playerId)
    }
}