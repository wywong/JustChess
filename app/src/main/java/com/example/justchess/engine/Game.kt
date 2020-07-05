package com.example.justchess.engine

interface Game {
    /**
     * returns the current [Board]
     */
    fun getCurrentBoard(): Board

    /**
     * returns the turn history in turn order
     */
    fun getTurnHistory(): Collection<Turn>

    /**
     * updates the game state by applying the supplied moves
     */
    fun applyMoves(moves: Collection<Move>): Turn

    /**
     * sets event listener for player
     */
    fun setListener(listener: GameEventListener, playerId: Int)

    /**
     * returns true if there is at least one turn to undo
     */
    fun canUndo(): Boolean

    /**
     * updates the game state by reversing the latest turn
     */
    fun undo(): Turn

    /**
     * returns 0 if it is white's turn, 1 otherwise
     */
    fun playerTurn(): Int
}