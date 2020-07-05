package com.example.justchess.engine

interface GameEventListener {
    /**
     * handles a pawn promotion
     *
     * returns the [Piece] that the pawn was promoted to
     */
    fun handlePawnPromotion(coordinate: Coordinate): Piece

    /**
     * handles the checkmate event
     * @param playerId the id of checkmated player
     */
    fun handleCheckmate(playerId: Int)

    /**
     * handles the stalemate event
     */
    fun handleStalemate()
}