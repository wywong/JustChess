package com.example.justchess.bot

import com.example.justchess.Bot
import com.example.justchess.ChessUtil
import com.example.justchess.engine.*
import java.util.*

private class Choice(
    val moves: Collection<Move>,
    val board: Board,
    var score: Int,
    val parent: Choice?
)

class MinimaxBot(
    override val playerId: Int,
    private val pieceFactory: PieceFactory
) : Bot {
    private val turnsAhead: Int = 3

    override fun getNextTurn(board: Board): Collection<Move> {
        val levels: ArrayDeque<Collection<Choice>> = ArrayDeque()
        var previousLevel: Collection<Choice> = getChoices(board, playerId, null)
        levels.add(previousLevel)
        var currentPlayer = ChessUtil.getOtherPlayerId(playerId)
        while (levels.size < turnsAhead) {
            val level = mutableListOf<Choice>()
            for (choice in previousLevel) {
                level.addAll(
                    getChoices(
                        choice.board,
                        currentPlayer,
                        choice
                    )
                )
            }
            levels.add(level)
            previousLevel = level
            currentPlayer = ChessUtil.getOtherPlayerId(playerId)
        }
        val bestChoice = levels.last.maxBy { choice ->
            choice.score
        }
        return if (bestChoice != null) {
            var rootChoice = bestChoice
            while (rootChoice?.parent != null) {
                rootChoice = rootChoice.parent
            }
            return rootChoice!!.moves
        } else {
            emptyList()
        }
    }

    override fun getPromotedPiece(coordinate: Coordinate, board: Board): Piece {
        return pieceFactory.createPromotedPiece(coordinate, ChessUtil.queenId)
    }

    private fun getChoices(board: Board, playerId: Int, parent: Choice?): Collection<Choice> {
        val pieces: Collection<Piece> = board.getPiecesForPlayer(playerId)
        val choices = arrayListOf<Choice>()
        for (piece in pieces) {
            val validMoves = piece.getValidMoves(board)
            for (moves in validMoves) {
                choices.add(
                    Choice(
                        moves,
                        board.applyMoves(moves),
                        (parent?.score ?: 0) + moveScoreDelta(board, moves),
                        parent
                    )
                )
            }
        }
        return choices
    }

    private fun moveScoreDelta(board: Board, moves: Collection<Move>): Int {
        return moves.fold(0) { totalDelta, move ->
            totalDelta + move.scoreDelta(board, playerId)
        }
    }
}