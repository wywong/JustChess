package xyz.wywong.justchess.engine.game

import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Game
import xyz.wywong.justchess.engine.Move
import xyz.wywong.justchess.engine.piece.King
import xyz.wywong.justchess.engine.piece.Pawn
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
                blackKing.location,
                null
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
                blackKing.location,
                null
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