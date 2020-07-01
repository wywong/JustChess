package com.example.justchess.engine

import com.example.justchess.engine.mocks.FakeBoard
import com.example.justchess.engine.piece.Rook
import org.junit.Assert
import org.junit.Test

class RookUnitTest {
    @Test
    fun create_rook() {
        try {
            Rook(
                Coordinate(1, 1),
                0,
                null
            )
        } catch (e: Throwable) {
            Assert.fail("Failed to instantiate rook")
        }
    }

    @Test
    fun all_directions_valid_no_obstructions() {
        val rook = Rook(
            Coordinate(3, 3),
            0,
            null
        )

        val destinations = rook.getValidDestinations(FakeBoard(false))
        assert(destinations.size == 14)
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(3, z)))
        }
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, 3)))
        }
    }

    @Test
    fun top_obstructed_same_player() {
        val rook = Rook(
            Coordinate(3, 3),
            0,
            null
        )

        val destinations = rook.getValidDestinations(
            FakeBoard(
                false, Rook(Coordinate(3, 2), 0, null)
            )
        )
        assert(destinations.size == 11)
        for (z in 4 until 8) {
            assert(destinations.contains(Coordinate(3, z)))
        }
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, 3)))
        }
    }

    @Test
    fun top_obstructed_different_player() {
        val rook = Rook(
            Coordinate(3, 3),
            0,
            null
        )

        val destinations = rook.getValidDestinations(
            FakeBoard(
                false, Rook(Coordinate(3, 2), 1, null)
            )
        )
        assert(destinations.size == 12)
        assert(destinations.contains(Coordinate(3, 2)))
        for (z in 4 until 8) {
            assert(destinations.contains(Coordinate(3, z)))
        }
        for (z in 0 until 8) {
            if (z == 3) continue
            assert(destinations.contains(Coordinate(z, 3)))
        }
    }
}