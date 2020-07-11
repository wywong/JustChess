package xyz.wywong.justchess

interface GameEventListener {
    /**
     * invoked when view model changes
     */
    fun onViewModelChange(gameViewModel: GameViewModel)

    /**
     * invoked when the game reaches a stalemate
     */
    fun onStalemate()

    /**
     * invoked when a player is checkmated
     */
    fun onCheckmate(checkedPlayerId: Int)
}