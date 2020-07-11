package xyz.wywong.justchess.engine.piece

import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.mocks.FakeBoard
import org.junit.Assert.fail
import org.junit.Test

class KnightUnitTest {
    @Test
    fun can_create_knight() {
        try {
            Knight(
                Coordinate(0, 1),
                0,
                null,
                true
            )
        } catch (e: Throwable) {
            fail("Failed to instantiate knight")
        }
    }

    @Test
    fun all_moves_valid() {
        val origin = Coordinate(3, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 8)
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
                    4,
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
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    4
                )
            )
        )
    }

    @Test
    fun top_moves_not_valid() {
        val origin = Coordinate(2, 1)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
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
                    3
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    3
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    0
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    2
                )
            )
        )
    }

    @Test
    fun bottom_moves_not_valid() {
        val origin = Coordinate(2, 6)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    5
                )
            )
        )
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
                    1,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    7
                )
            )
        )
    }

    @Test
    fun left_moves_not_valid() {
        val origin = Coordinate(1, 2)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
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
                    3,
                    1
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    3,
                    3
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    0,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    4
                )
            )
        )
    }

    @Test
    fun right_moves_not_valid() {
        val origin = Coordinate(6, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 6)
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    1
                )
            )
        )
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
                    4,
                    2
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    5,
                    5
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    7,
                    5
                )
            )
        )
    }

    @Test
    fun no_moves_if_causes_king_to_be_checked() {
        val origin = Coordinate(3, 3)
        val knight = Knight(
            origin,
            0,
            null,
            true
        )
        val validMoves = knight.getValidMoves(FakeBoard(true))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }
}