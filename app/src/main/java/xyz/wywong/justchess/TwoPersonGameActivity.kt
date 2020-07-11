package xyz.wywong.justchess

import xyz.wywong.justchess.controller.TwoPersonGameController
import xyz.wywong.justchess.engine.Game

class TwoPersonGameActivity : ChessGameActivity() {
    override fun buildController(game: Game): GameController {
        return TwoPersonGameController(
            game
        )
    }
}