package com.example.justchess.engine.piece

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.mocks.FakeBoard
import org.junit.Assert.fail
import org.junit.Test

class KingUnitTest {
    @Test
    fun can_create_king() {
        try {
            King(
                Coordinate(1, 1),
                0,
                null,
                true
            )
        } catch (e: Throwable) {
            fail("Failed to instantiate king")
        }
    }

    @Test
    fun all_moves_valid() {
        val king = King(
            Coordinate(1, 1),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 8)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    2
                )
            )
        )
    }

    @Test
    fun top_moves_not_valid() {
        val king = King(
            Coordinate(1, 0),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    1
                )
            )
        )
    }

    @Test
    fun bottom_moves_not_valid() {
        val king = King(
            Coordinate(1, 7),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    7
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    7
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    6
                )
            )
        )
    }

    @Test
    fun left_moves_not_valid() {
        val king = King(
            Coordinate(0, 1),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    2
                )
            )
        )
    }

    @Test
    fun right_moves_not_valid() {
        val king = King(
            Coordinate(7, 1),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    2
                )
            )
        )
    }

    @Test
    fun top_left_moves() {
        val king = King(
            Coordinate(0, 0),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    1
                )
            )
        )
    }

    @Test
    fun top_right_moves() {
        val king = King(
            Coordinate(7, 0),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    1
                )
            )
        )
    }

    @Test
    fun bottom_left_moves() {
        val king = King(
            Coordinate(0, 7),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    7
                )
            )
        )
    }

    @Test
    fun bottom_right_moves() {
        val king = King(
            Coordinate(7, 7),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 3)
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    6
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    6,
                    7
                )
            )
        )
    }

    @Test
    fun cannot_move_to_check() {
        val king = King(
            Coordinate(1, 1),
            0,
            null,
            true
        )
        val validMoves = king.getValidMoves(FakeBoard(true))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun can_castle_left() {
        val king = King(
            Coordinate(4, 7),
            0,
            null,
            false
        )
        val validMoves = king.getValidMoves(
            FakeBoard(
                false,
                Rook(
                    Coordinate(0, 7),
                    0,
                    null,
                    false
                )
            )
        )
        assert(validMoves.size == 6)
        val castleMoveCoordinates = validMoves.find { moves ->
            moves.size == 2
        }?.map { move -> move.destination } ?: emptyList()
        assert(castleMoveCoordinates.contains(Coordinate(2, 7)))
        assert(castleMoveCoordinates.contains(Coordinate(3, 7)))
    }

    @Test
    fun can_castle_right() {
        val king = King(
            Coordinate(4, 7),
            0,
            null,
            false
        )
        val validMoves = king.getValidMoves(
            FakeBoard(
                false,
                Rook(
                    Coordinate(7, 7),
                    0,
                    null,
                    false
                )
            )
        )
        val castleMoveCoordinates = validMoves.find { moves ->
            moves.size == 2
        }?.map { move -> move.destination } ?: emptyList()
        assert(castleMoveCoordinates.contains(Coordinate(6, 7)))
        assert(castleMoveCoordinates.contains(Coordinate(5, 7)))
    }

    @Test
    fun cannot_castle_left_if_rook_moved() {
        val king = King(
            Coordinate(4, 7),
            0,
            null,
            false
        )
        val validMoves = king.getValidMoves(
            FakeBoard(
                false,
                Rook(
                    Coordinate(0, 7),
                    0,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
    }

    @Test
    fun cannot_castle_right_if_rook_moved() {
        val king = King(
            Coordinate(4, 7),
            0,
            null,
            false
        )
        val validMoves = king.getValidMoves(
            FakeBoard(
                false,
                Rook(
                    Coordinate(7, 7),
                    0,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 5)
    }

    @Test
    fun cannot_castle_if_checked() {
        val king = King(
            Coordinate(4, 7),
            0,
            null,
            false
        )
        val validMoves = king.getValidMoves(
            FakeBoard(
                true,
                Rook(
                    Coordinate(0, 7),
                    0,
                    null,
                    false
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

}