package com.example.justchess.engine

interface GameFactory {
    /**
     * returns a new instance of game
     */
    fun createNewGame(): Game
}