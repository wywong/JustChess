package com.example.justchess

interface GameViewModelListener {
    /**
     * invoked when view model changes
     */
    fun onViewModelChange(gameViewModel: GameViewModel)
}