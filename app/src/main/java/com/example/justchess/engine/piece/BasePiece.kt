package com.example.justchess.engine.piece

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Move
import com.example.justchess.engine.Piece

abstract class BasePiece(private val moved: Boolean) : Piece {

    override fun hasMoved(): Boolean {
        return moved
    }

    override fun getValidMoves(board: Board): Collection<Collection<Move>> {
        return getPossibleMoves(board)
            .filter { moves ->
                isKingCheckedAfterMoves(moves, board)
            }
    }

    override fun getPossibleMoves(board: Board): Collection<Collection<Move>> {
        return getPossible(board).filter { moves ->
            areMovesValid(moves, board)
        }
    }

    override fun getPossibleDestinations(board: Board): Collection<Coordinate> {
        return getPossibleCoordinates(board).filter { coordinate ->
            isDestinationValid(coordinate, board)
        }
    }

    protected open fun getPossible(board: Board): Collection<Collection<Move>> {
        return getPossibleCoordinates(board)
            .map { coordinate ->
                listOf(Move(coordinate, this))
            }
    }

    protected abstract fun getPossibleCoordinates(board: Board): Collection<Coordinate>

    /**
     * returns true if all moves are possible, otherwise false
     */
    private fun areMovesValid(moves: Collection<Move>, board: Board): Boolean {
        val allPossible: Boolean = moves.all { move -> isMoveValid(move, board) }
        return moves.isNotEmpty() && allPossible
    }

    /**
     * returns true if the destination of the move is in the bounds of the board
     *         and if the destination coordinate does not contain a piece with the same player id
     *         otherwise false
     */
    private fun isMoveValid(move: Move, board: Board): Boolean {
        return isDestinationValid(move.destination, board)
    }

    private fun isDestinationValid(destination: Coordinate, board: Board): Boolean {
        val destinationInBounds = Coordinate.inBounds(destination)
        val targetPiece: Piece? = board.getPiece(destination)
        val destinationEmptyOrDifferentPlayer = targetPiece?.playerId != this.playerId
        return destinationInBounds && destinationEmptyOrDifferentPlayer
    }

    /**
     * returns true if the king is checked after applying the moves, otherwise false
     */
    private fun isKingCheckedAfterMoves(moves: Collection<Move>, board: Board): Boolean {
        val prospectiveBoard = moves.fold(board, { updatedBoard, move ->
            updatedBoard.movePiece(move.destination, move.piece)
        })
        return !prospectiveBoard.isKingInCheck(this.playerId)
    }
}