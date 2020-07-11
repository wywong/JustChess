package xyz.wywong.justchess.engine.game

import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.piece.King
import xyz.wywong.justchess.engine.piece.Pawn
import xyz.wywong.justchess.engine.piece.Rook
import org.junit.Test

class BoardUnitTest {
    private val whiteKing = King(
        Coordinate(3, 3),
        0,
        null,
        true
    )

    private val whitePawn = Pawn(
        Coordinate(3, 2),
        0,
        null,
        true
    )

    private val blackKing = King(
        Coordinate(0, 0),
        1,
        null,
        true
    )

    private val blackRook = Rook(
        Coordinate(3, 0),
        1,
        null,
        true
    )

    @Test
    fun king_not_in_check() {
        val board = DefaultBoard(
            mapOf(
                Pair(whiteKing.location, whiteKing),
                Pair(blackKing.location, blackKing)
            ),
            whiteKing.location,
            blackKing.location,
            null
        )
        assert(!board.isKingInCheck(0))
        assert(!board.isKingInCheck(1))
    }

    @Test
    fun white_king_in_check() {
        val board = DefaultBoard(
            mapOf(
                Pair(whiteKing.location, whiteKing),
                Pair(blackKing.location, blackKing),
                Pair(blackRook.location, blackRook)
            ),
            whiteKing.location,
            blackKing.location,
            null
        )
        assert(board.isKingInCheck(0))
        assert(!board.isKingInCheck(1))
    }

    @Test
    fun white_king_in_check_obstruction() {
        val board = DefaultBoard(
            mapOf(
                Pair(whiteKing.location, whiteKing),
                Pair(blackKing.location, blackKing),
                Pair(blackRook.location, blackRook),
                Pair(whitePawn.location, whitePawn)
            ),
            whiteKing.location,
            blackKing.location,
            null
        )
        assert(!board.isKingInCheck(0))
        assert(!board.isKingInCheck(1))
    }

    @Test
    fun white_king_moved() {
        val board = DefaultBoard(
            mapOf(
                Pair(whiteKing.location, whiteKing),
                Pair(blackKing.location, blackKing)
            ),
            whiteKing.location,
            blackKing.location,
            null
        )
        val newCoordinate = Coordinate(3, 2)
        val updatedBoard = board.movePiece(newCoordinate, whiteKing) as DefaultBoard
        assert(updatedBoard.whiteKingLocation == newCoordinate)
        assert(updatedBoard.getPiece(newCoordinate)?.location == newCoordinate)
    }

    @Test
    fun black_king_moved() {
        val board = DefaultBoard(
            mapOf(
                Pair(whiteKing.location, whiteKing),
                Pair(blackKing.location, blackKing)
            ),
            whiteKing.location,
            blackKing.location,
            null
        )
        val newCoordinate = Coordinate(1, 0)
        val updatedBoard = board.movePiece(newCoordinate, blackKing) as DefaultBoard
        assert(updatedBoard.blackKingLocation == newCoordinate)
        assert(updatedBoard.getPiece(newCoordinate)?.location == newCoordinate)
    }
}