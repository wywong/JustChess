package com.example.justchess.engine

interface GameFactory {
    /**
     * returns a new instance of game
     */
    fun createNewGame(): Game

    /**
     * returns the white piece factory used by this game
     */
    val whitePieceFactory: PieceFactory

    /**
     * returns the black piece factory used by this game
     */
    val blackPieceFactory: PieceFactory
}