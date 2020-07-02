package com.example.justchess.engine

/**
 * @param moves the pieces that moved this turn, multiple movements are needed to support castling
 * @param removedPieces the pieces that were removed this turn
 *                      this could contain a captured piece or a pawn that was promoted
 */
data class Turn(
    val moves: Collection<Move>,
    val removedPieces: Collection<Piece>
)