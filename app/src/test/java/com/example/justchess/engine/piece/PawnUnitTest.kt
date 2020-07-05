package com.example.justchess.engine.piece

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.game.DefaultBoard
import com.example.justchess.engine.mocks.FakeBoard
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
    fun pawn_white_single_forward_valid() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 1)
        assert(
            destinations.contains(
                Coordinate(
                    1,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_forward_moves_valid_first_move() {
        val validMoves = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    1,
                    4
                )
            )
        )
    }

    @Test
    fun pawn_white_first_move_forward_obstructed() {
        val validMoves = Pawn(
            Coordinate(1, 6),
            0,
            null,
            false
        ).getValidMoves(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 5), 0, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun pawn_white_left_move_invalid() {
        val validMoves = Pawn(
            Coordinate(0, 1),
            0,
            null,
            true
        ).getValidMoves(
            DefaultBoard(
                mapOf(
                    Pair(
                        Coordinate(1, 0),
                        King(Coordinate(1, 0), 1, null, true)
                    )
                ),
                Coordinate(1, 0),
                Coordinate(7, 7),
                null
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    1,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_right_move_invalid() {
        val validMoves = Pawn(
            Coordinate(7, 1),
            0,
            null,
            true
        ).getValidMoves(
            DefaultBoard(
                mapOf(
                    Pair(
                        Coordinate(6, 0),
                        King(Coordinate(6, 0), 1, null, true)
                    )
                ),
                Coordinate(6, 0),
                Coordinate(7, 7),
                null
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    6,
                    0
                )
            )
        )
    }

    @Test
    fun pawn_white_cannot_move_into_white_piece() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            0,
            null,
            true
        ).getValidMoves(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 0),
                    0,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun pawn_cannot_move_if_king_will_be_checked_after() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            0,
            null,
            true
        ).getValidMoves(FakeBoard(true))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun pawn_black_forward_move_valid() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            true
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isNotEmpty())
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
    fun pawn_black_all_forward_moves_valid_first_move() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidMoves(FakeBoard(false))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    3
                )
            )
        )
    }

    @Test
    fun pawn_black_all_moves_valid_first_move_forward_obstructed() {
        val validMoves = Pawn(
            Coordinate(1, 1),
            1,
            null,
            false
        ).getValidMoves(
            FakeBoard(
                false,
                Pawn(Coordinate(1, 2), 0, null, true)
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }


    @Test
    fun pawn_black_left_move_invalid() {
        val validMoves = Pawn(
            Coordinate(0, 1),
            1,
            null,
            true
        ).getValidMoves(DefaultBoard(
            mapOf(
                Pair(
                    Coordinate(1, 2),
                    King(Coordinate(1, 2), 0, null, false)
                )
            ),
            Coordinate(1, 2),
            Coordinate(7, 7),
            null
        ))
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_right_move_invalid() {
        val validMoves = Pawn(
            Coordinate(7, 1),
            1,
            null,
            true
        ).getValidMoves(
            DefaultBoard(
                mapOf(
                    Pair(
                        Coordinate(6, 2),
                        King(Coordinate(6, 2), 0, null, true)
                    )
                ),
                Coordinate(6, 2),
                Coordinate(0, 0),
                null
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
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
                    2
                )
            )
        )
    }

    @Test
    fun pawn_black_cannot_move_into_black_piece() {
        val validMoves = Pawn(
            Coordinate(4, 1),
            1,
            null,
            true
        ).getValidMoves(
            FakeBoard(
                false, Pawn(
                    Coordinate(4, 2),
                    1,
                    null,
                    true
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.isEmpty())
    }

    @Test
    fun pawn_can_en_passant() {
        val whitePawn = Pawn(
            Coordinate(4, 4),
            0,
            null,
            true
        )
        val validMoves = Pawn(
            Coordinate(5, 4),
            1,
            null,
            true
        ).getValidMoves(
            FakeBoard(
                false, null, Pair(
                    Pawn(
                        Coordinate(4, 2),
                        0,
                        null,
                        false
                    ), whitePawn
                )
            )
        )
        val destinations = validMoves.flatMap { moves ->
            moves.map { move -> move.destination }
        }
        assert(destinations.size == 2)
        assert(destinations.contains(Coordinate(4, 5)))
        assert(destinations.contains(Coordinate(5, 5)))
    }
}