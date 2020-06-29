package com.example.justchess.engine

interface Game {
    /**
     * returns the current [Board]
     */
    fun getCurrentBoard(): Board

    /**
     * returns the move history in the chronological order
     */
    fun getMoveHistory(): Collection<Move>

    /**
     * updates the game state by applying the move
     */
    fun movePiece(move: Move)

    /**
     * updates the game state by reversing the latest move
     */
    fun undo(): Move

    /**
     * returns 0 if it is white's turn, 1 otherwise
     */
    fun playerTurn(): Int
}