package com.example.justchess.engine.factory

import com.example.justchess.engine.Board
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.GameFactory
import com.example.justchess.engine.PieceImageProvider
import com.example.justchess.engine.piece.*
import org.junit.Test

class GameFactoryUnitTest {
    private val emptyImageProvider: PieceImageProvider = PieceImageProvider(
        null,
        null,
        null,
        null,
        null,
        null
    )
    private val gameFactory: GameFactory = DefaultGameFactory(
        emptyImageProvider, emptyImageProvider
    )

    @Test
    fun create_new_game_32_pieces() {
        val game = gameFactory.createNewGame()
        assert(game.getCurrentBoard().getPieces().size == 32)
    }

    @Test
    fun create_new_game_16_pieces_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 16)
        assert(blackCount == 16)
    }

    @Test
    fun create_new_game_8_pawns_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<Pawn>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 8)
        assert(blackCount == 8)
    }

    @Test
    fun create_new_game_2_rooks_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<Rook>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 2)
        assert(blackCount == 2)
    }

    @Test
    fun create_new_game_2_knights_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<Knight>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 2)
        assert(blackCount == 2)
    }

    @Test
    fun create_new_game_2_bishops_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<Bishop>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 2)
        assert(blackCount == 2)
    }

    @Test
    fun create_new_game_1_queen_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<Queen>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 1)
        assert(blackCount == 1)
    }

    @Test
    fun create_new_game_1_king_each_player() {
        val game = gameFactory.createNewGame()
        var whiteCount = 0
        var blackCount = 0
        game.getCurrentBoard().getPieces().filterIsInstance<King>().forEach { piece ->
            if (piece.playerId == 0) {
                whiteCount += 1
            } else {
                blackCount += 1
            }
        }
        assert(whiteCount == 1)
        assert(blackCount == 1)
    }

    @Test
    fun create_new_game_no_pieces_in_middle() {
        val game = gameFactory.createNewGame()
        val board = game.getCurrentBoard()
        for (r in 2..5) {
            for (c in 0 until 8) {
                val coordinate = Coordinate(c, r)
                assert(board.getPiece(coordinate) == null)
            }
        }
    }

    @Test
    fun create_new_game_pieces_in_first_2_and_last_2_ranks() {
        val game = gameFactory.createNewGame()
        val board = game.getCurrentBoard()
        for (r in 0..1) {
            rowHasPieces(board, r)
        }
        for (r in 6..7) {
            rowHasPieces(board, r)
        }
    }

    private fun rowHasPieces(board: Board, r: Int) {
        for (c in 0 until 8) {
            val coordinate = Coordinate(c, r)
            assert(board.getPiece(coordinate) != null)
        }
    }
}