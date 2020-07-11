package xyz.wywong.justchess

import xyz.wywong.justchess.bot.RandomBot
import xyz.wywong.justchess.controller.SinglePersonGameController
import xyz.wywong.justchess.engine.Game

class SinglePersonGameActivity : ChessGameActivity() {
    override fun buildController(game: Game): GameController {
        return SinglePersonGameController(
            game,
            RandomBot(
                1,
                getGameFactory().blackPieceFactory
            )
        )
    }
}