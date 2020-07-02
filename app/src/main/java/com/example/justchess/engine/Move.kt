package com.example.justchess.engine

/**
 * Contains information recording a single movement of a chess piece
 *
 * @param destination the location the [piece] is being moved to
 * @param piece the [Piece] that is being moved
 *
 */
data class Move(
    val destination: Coordinate,
    val piece: Piece
) {
    /**
     * the original location of the moved [piece]
     */
    val origin: Coordinate = piece.location
}
