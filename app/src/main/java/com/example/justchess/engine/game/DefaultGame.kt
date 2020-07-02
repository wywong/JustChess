package com.example.justchess.engine.game

import com.example.justchess.ChessUtil
import com.example.justchess.engine.*
import java.util.*

class DefaultGame(
    initialBoard: Board,
    initialPlayerTurn: Int
) : Game {
    private var currentBoard: Board = initialBoard
    private var currentPlayer: Int = initialPlayerTurn
    private var turnHistory: ArrayDeque<Turn> = ArrayDeque()

    override fun getCurrentBoard(): Board {
        return currentBoard
    }

    override fun getTurnHistory(): Collection<Turn> {
        return turnHistory
    }

    override fun applyMoves(moves: Collection<Move>): Turn {
        val removedPieces = getRemovedPieces(moves)
        currentBoard = applyMovesToBoard(moves)
        val turn = Turn(
            moves,
            removedPieces
        )
        turnHistory.push(turn)
        changePlayer()
        return turn
    }

    override fun canUndo(): Boolean {
        return turnHistory.isNotEmpty()
    }

    override fun undo(): Turn {
        if (turnHistory.isNotEmpty()) {
            val turn = turnHistory.pop()
            currentBoard = turn.moves.fold(currentBoard) { board, move ->
                board.movePiece(
                    move.origin,
                    move.piece
                )
            }
            currentBoard = turn.removedPieces.fold(currentBoard) { board, piece ->
                board.movePiece(
                    piece.location,
                    piece
                )
            }
            changePlayer()
            return turn
        } else {
            throw Exception("No turn to undo")
        }
    }

    override fun playerTurn(): Int {
        return currentPlayer
    }

    private fun getRemovedPieces(moves: Collection<Move>): Collection<Piece> {
        return moves.mapNotNull { move ->
            currentBoard.getPiece(move.destination)
        }
    }

    private fun applyMovesToBoard(moves: Collection<Move>): Board {
        return moves.fold(currentBoard) { board, move ->
            board.movePiece(move.destination, move.piece)
        }
    }

    private fun changePlayer() {
        currentPlayer = ChessUtil.getOtherPlayerId(currentPlayer)
    }

}