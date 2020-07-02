package com.example.justchess.engine.game

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.Move
import com.example.justchess.engine.piece.King
import org.junit.Test

class GameUnitTest {
    private val whiteKing = King(Coordinate(0, 0), 0, null, true)
    private val blackKing = King(Coordinate(7, 7), 1, null, true)

    private fun twoKingGame(): Game {
        return DefaultGame(
            DefaultBoard(
                mapOf(
                    Pair(whiteKing.location, whiteKing),
                    Pair(blackKing.location, blackKing)
                ),
                whiteKing.location,
                blackKing.location
            ),
            0
        )
    }

    @Test
    fun can_move_piece() {
        val game = twoKingGame()
        val turn = game.applyMoves(listOf(Move(Coordinate(0, 1), whiteKing)))
        assert(game.getCurrentBoard().getPiece(Coordinate(0, 1)) != null)
        assert(game.getCurrentBoard().getPiece(Coordinate(0, 0)) == null)
        assert(turn.removedPieces.isEmpty())
    }

    @Test
    fun current_player_id_changes_after_player_turn() {
        val game = twoKingGame()
        game.applyMoves(listOf(Move(Coordinate(0, 1), whiteKing)))
        assert(game.playerTurn() == 1)
    }

    @Test
    fun can_undo_turn() {
        val game = twoKingGame()
        game.applyMoves(listOf(Move(Coordinate(0, 1), whiteKing)))
        assert(game.canUndo())
        assert(game.getTurnHistory().size == 1)
        game.undo()
        assert(game.playerTurn() == 0)
        assert(game.getCurrentBoard().getPiece(Coordinate(0, 0)) != null)
    }
}