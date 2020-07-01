package com.example.justchess.engine

import com.example.justchess.engine.mocks.FakeBoard
import com.example.justchess.engine.piece.Pawn
import org.junit.Assert.fail
import org.junit.Test

class PawnUnitTest {

    @Test
    fun can_create_pawn() {
        try {
            Pawn(
                Coordinate(0, 1),
                0,
                null,
                true
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
            null,
            true
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 3)
        assert(coordinates.contains(Coordinate(0, 0)))
        assert(coordinates.contains(Coordinate(1, 0)))
        assert(coordinates.contains(Coordinate(2, 0)))
    }

    @Test
    fun pawn_white_all_moves_valid_first_move() {
        val coordinates = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 4)
        assert(coordinates.contains(Coordinate(0, 5)))
        assert(coordinates.contains(Coordinate(1, 5)))
        assert(coordinates.contains(Coordinate(1, 4)))
        assert(coordinates.contains(Coordinate(2, 5)))
    }

    @Test
    fun pawn_white_all_moves_valid_first_move_forward_obstructed() {
        val coordinates = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidDestinations(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 5), 0, null, true)
            )
        )
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(0, 5)))
        assert(coordinates.contains(Coordinate(2, 5)))
    }

    @Test
    fun pawn_white_left_move_invalid() {
        val coordinates = Pawn(
            Coordinate(0, 1),
            0,
            null,
            true
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
            null,
            true
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
            null,
            true
        ).getValidDestinations(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 0),
                    0,
                    null,
                    true
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
            null,
            true
        ).getValidDestinations(FakeBoard(true))
        assert(coordinates.isEmpty())
    }

    @Test
    fun pawn_black_all_moves_valid() {
        val coordinates = Pawn(
            Coordinate(1, 1),
            1,
            null,
            true
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 3)
        assert(coordinates.contains(Coordinate(0, 2)))
        assert(coordinates.contains(Coordinate(1, 2)))
        assert(coordinates.contains(Coordinate(2, 2)))
    }

    @Test
    fun pawn_black_all_moves_valid_first_move() {
        val coordinates = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidDestinations(FakeBoard(false))
        assert(coordinates.size == 4)
        assert(coordinates.contains(Coordinate(0, 2)))
        assert(coordinates.contains(Coordinate(1, 2)))
        assert(coordinates.contains(Coordinate(1, 3)))
        assert(coordinates.contains(Coordinate(2, 2)))
    }

    @Test
    fun pawn_black_all_moves_valid_first_move_forward_obstructed() {
        // note that this test obstructs with a different color piece
        // we should not be able to capture forward
        val coordinates = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidDestinations(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 2), 0, null, true)
            )
        )
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(0, 2)))
        assert(coordinates.contains(Coordinate(2, 2)))
    }


    @Test
    fun pawn_black_left_move_invalid() {
        val coordinates = Pawn(
            Coordinate(0, 1),
            1,
            null,
            true
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
            null,
            true
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
            null,
            true
        ).getValidDestinations(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 2),
                    1,
                    null,
                    true
                )
            )
        )
        assert(coordinates.size == 2)
        assert(coordinates.contains(Coordinate(3, 2)))
        assert(!coordinates.contains(Coordinate(4, 2)))
        assert(coordinates.contains(Coordinate(5, 2)))
    }
}