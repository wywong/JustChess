package com.example.justchess.engine

import com.example.justchess.engine.piece.Pawn
import org.junit.Assert.fail
import org.junit.Test

private class FakeBoard(
    private val kingChecked: Boolean,
    private val piece: Piece? = null
) : Board {
    override fun getPiece(location: Coordinate): Piece? {
        return if (this.piece?.location == location) {
            this.piece
        } else {
            null
        }
    }

    override fun getPieces(): Collection<Piece> {
        return ArrayList()
    }

    override fun movePiece(piece: Piece, coordinate: Coordinate): Board {
        return this
    }

    override fun isKingInCheck(playerId: Int): Boolean {
        return kingChecked
    }

}

class PawnUnitTest {

    @Test
    fun can_create_pawn() {
        try {
            Pawn(
                Coordinate(0, 1),
                0,
                null
            )
        } catch (e: Throwable) {
            fail("Failed to instantiate pawn")
        }
    }

    @Test
    fun pawn_white_all_moves_valid() {
        val coordinates = Pawn(
            Coordinate(1, 1),
            0,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 3)
        assert(coordinates.contains(Coordinate(0, 0)))
        assert(coordinates.contains(Coordinate(1, 0)))
        assert(coordinates.contains(Coordinate(2, 0)))
    }

    @Test
    fun pawn_white_left_move_invalid() {
        val coordinates = Pawn(
            Coordinate(0, 1),
            0,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(0, 0)))
        assert(coordinates.contains(Coordinate(1, 0)))
    }

    @Test
    fun pawn_white_right_move_invalid() {
        val coordinates = Pawn(
            Coordinate(7, 1),
            0,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(7, 0)))
        assert(coordinates.contains(Coordinate(6, 0)))
    }

    @Test
    fun pawn_white_cannot_move_into_white_piece() {
        val coordinates = Pawn(
            Coordinate(4, 1),
            0,
            null
        ).getValidDestinations(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 0),
                    0,
                    null
                )
            )
        )
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(3, 0)))
        assert(!coordinates.contains(Coordinate(4, 0)))
        assert(coordinates.contains(Coordinate(5, 0)))
    }

    @Test
    fun pawn_cannot_move_if_king_will_be_checked_after() {
        val coordinates = Pawn(
            Coordinate(4, 1),
            0,
            null
        ).getValidDestinations(FakeBoard(true))
        assert(coordinates.isEmpty())
    }

    @Test
    fun pawn_black_all_moves_valid() {
        val coordinates = Pawn(
            Coordinate(1, 1),
            1,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 3)
        assert(coordinates.contains(Coordinate(0, 2)))
        assert(coordinates.contains(Coordinate(1, 2)))
        assert(coordinates.contains(Coordinate(2, 2)))
    }

    @Test
    fun pawn_black_left_move_invalid() {
        val coordinates = Pawn(
            Coordinate(0, 1),
            1,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(0, 2)))
        assert(coordinates.contains(Coordinate(1, 2)))
    }

    @Test
    fun pawn_black_right_move_invalid() {
        val coordinates = Pawn(
            Coordinate(7, 1),
            1,
            null
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(7, 2)))
        assert(coordinates.contains(Coordinate(6, 2)))
    }

    @Test
    fun pawn_black_cannot_move_into_black_piece() {
        val coordinates = Pawn(
            Coordinate(4, 1),
            1,
            null
        ).getValidDestinations(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 2),
                    1,
                    null
                )
            )
        )
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(3, 2)))
        assert(!coordinates.contains(Coordinate(4, 2)))
        assert(coordinates.contains(Coordinate(5, 2)))
    }
}