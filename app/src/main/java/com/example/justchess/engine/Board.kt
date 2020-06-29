package com.example.justchess.engine

/**
 * represents a chess board
 */
interface Board {
    /**
     * returns the piece at location if it exists, otherwise null
     */
    fun getPiece(location: Coordinate): Piece?

    /**
     * returns an collection of all chess [Piece]s still on the board
     */
    fun getPieces(): Collection<Piece>

    /**
     * returns a board with piece moved to coordinate
     * WARNING the returned board state may not be valid
     */
    fun movePiece(piece: Piece, coordinate: Coordinate): Board

    /**
     * returns true if player's king is in check
     */
    fun isKingInCheck(playerId: Int): Boolean
}