package xyz.wywong.justchess.engine.piece

import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.mocks.FakeBoard
import org.junit.Assert
import org.junit.Test

class QueenUnitTest {
    @Test
    fun create_rook() {
        try {
            Queen(
                Coordinate(1, 1),
                0,
                null,
                true
            )
        } catch (e: Throwable) {
            Assert.fail("Failed to instantiate queen")
        }
    }

    @Test
    fun all_directions_valid_no_obstructions() {
        val queen = Queen(
            Coordinate(3, 3),
            0,
            null,
            true
        )
        val validMoves = queen.getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 27)
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(
                destinations.contains(
                    Coordinate(
                        3,
                        z
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        3
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        z
                    )
                )
            )
        }
        // top right
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
                    5,
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
        // bottom left
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    5
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
    }

    @Test
    fun top_right_obstructed_same_player() {
        val queen = Queen(
            Coordinate(3, 3),
            0,
            null,
            true
        )
        val validMoves = queen.getValidMoves(
            FakeBoard(
                false,
                Queen(Coordinate(4, 2), 0, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 24)
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(
                destinations.contains(
                    Coordinate(
                        3,
                        z
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        3
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        z
                    )
                )
            )
        }
        // bottom left
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    5
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
    }

    @Test
    fun top_right_obstructed_different_player() {
        val queen = Queen(
            Coordinate(3, 3),
            0,
            null,
            true
        )
        val validMoves = queen.getValidMoves(
            FakeBoard(
                false,
                Queen(Coordinate(4, 2), 1, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 25)
        assert(
            destinations.contains(
                Coordinate(
                    4,
                    2
                )
            )
        )
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(
                destinations.contains(
                    Coordinate(
                        3,
                        z
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        3
                    )
                )
            )
            assert(
                destinations.contains(
                    Coordinate(
                        z,
                        z
                    )
                )
            )
        }
        // bottom left
        assert(
            destinations.contains(
                Coordinate(
                    2,
                    4
                )
            )
        )
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    5
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
    }
}