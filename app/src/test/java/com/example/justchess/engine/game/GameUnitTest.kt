package com.example.justchess.engine.game

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.Move
import com.example.justchess.engine.mocks.FakeGameEventListener
import com.example.justchess.engine.piece.King
import com.example.justchess.engine.piece.Pawn
import com.example.justchess.engine.piece.Queen
import org.junit.Test

class GameUnitTest {
    private val whiteKing = King(Coordinate(0, 0), 0, null, true)
    private val blackKing = King(Coordinate(7, 7), 1, null, true)
    private val whitePawn = Pawn(
        Coordinate(2, 1),
        0,
        null,
        true
    )

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

    private fun gameWithPromotablePawn(): Game {
        return DefaultGame(
            DefaultBoard(
                mapOf(
                    Pair(whiteKing.location, whiteKing),
                    Pair(blackKing.location, blackKing),
                    Pair(whitePawn.location, whitePawn)
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

    @Test
    fun can_promote_pawn() {
        val target = Coordinate(2, 0)
        val game = gameWithPromotablePawn()
        game.setListener(
            FakeGameEventListener(
                Queen(target, 0, null, false)
            ), 0
        )
        game.applyMoves(
            listOf(Move(target, whitePawn))
        )
        assert(game.getCurrentBoard().getPiece(target) is Queen)
    }
}