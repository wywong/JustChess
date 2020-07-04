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
     * @param playerId the id of the player
     * returns a collection of all chess [Piece]s for the specified player
     */
    fun getPiecesForPlayer(playerId: Int): Collection<Piece>

    /**
     * returns a board with piece moved to coordinate
     * WARNING the returned board state may not be valid
     */
    fun movePiece(coordinate: Coordinate, piece: Piece): Board

    /**
     * returns true if player's king is in check
     */
    fun isKingInCheck(playerId: Int): Boolean
}