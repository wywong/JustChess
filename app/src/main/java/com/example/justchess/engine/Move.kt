package com.example.justchess.engine

/**
 *
 * Contains information recording a single movement of a chess piece
 *
 * @param origin the original location of the moved [piece]
 * @param destination the location the [piece] is being moved to
 * @param piece the [Piece] that is being moved
 * @param eliminatedPiece the [Piece] that is eliminated by this move if applicable
 *
 */
data class Move(
    val origin: Coordinate,
    val destination: Coordinate,
    val piece: Piece,
    val eliminatedPiece: Piece?
)
