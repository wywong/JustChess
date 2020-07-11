package xyz.wywong.justchess.controller

import xyz.wywong.justchess.GameController
import xyz.wywong.justchess.GameViewModel
import xyz.wywong.justchess.GameViewModelListener
import xyz.wywong.justchess.engine.Coordinate
import xyz.wywong.justchess.engine.Game
import xyz.wywong.justchess.engine.Move
import xyz.wywong.justchess.engine.Piece

abstract class BaseGameController(
    private val game: Game
) : GameController {
    private var viewModel: GameViewModel = GameViewModel(
        game.getCurrentBoard().getPieces(),
        null,
        emptyList()
    )
    private var validMovesLookup: Map<Coordinate, Collection<Move>> = emptyMap()
    private val listeners = mutableListOf<GameViewModelListener>()

    override fun playerTurn(): Int {
        return game.playerTurn()
    }

    override fun getPromotablePawnCoordinate(): Coordinate? {
        return game.getPromotablePawnCoordinate()
    }

    override fun addViewModelListener(listener: GameViewModelListener) {
        listeners.add(listener)
        listener.onViewModelChange(viewModel)
    }

    override fun getViewModel(): GameViewModel {
        return viewModel
    }

    override fun selectCoordinate(coordinate: Coordinate) {
        when {
            isCoordinateValidMove(coordinate) -> {
                executePlayerMoves(validMovesLookup[coordinate])
                postPlayerMoves()
            }
            coordinate == viewModel.selectedCoordinate -> clearSelection()
            else -> applySelection(coordinate)
        }
    }

    override fun promotePawn(promotedPiece: Piece) {
        game.promotePawn(promotedPiece)
        updateViewModel(
            GameViewModel(
                game.getCurrentBoard().getPieces(),
                null,
                emptyList()
            )
        )
        postPlayerMoves()
    }

    private fun updateViewModel(gameViewModel: GameViewModel) {
        viewModel = gameViewModel
        listeners.forEach { listener ->
            listener.onViewModelChange(viewModel)
        }
    }

    private fun isCoordinateValidMove(coordinate: Coordinate): Boolean {
        return viewModel.validDestinations.contains(coordinate)
    }

    protected fun executePlayerMoves(moves: Collection<Move>?) {
        if (!moves.isNullOrEmpty()) {
            game.applyMoves(moves)
            updateViewModel(
                GameViewModel(
                    game.getCurrentBoard().getPieces(),
                    null,
                    emptyList()
                )
            )
        }
    }

    protected open fun postPlayerMoves() {

    }

    private fun clearSelection() {
        updateViewModel(
            viewModel.copy(
                selectedCoordinate = null,
                validDestinations = emptyList()
            )
        )
        validMovesLookup = emptyMap()
    }

    private fun applySelection(coordinate: Coordinate) {
        val piece = game.getCurrentBoard().getPiece(coordinate)
        if (piece?.playerId == getCurrentPlayerId()) {
            val validMoves = piece.getValidMoves(game.getCurrentBoard())
            if (validMoves.isNotEmpty()) {
                validMovesLookup = validMoves
                    .map { moves -> Pair(moves.first().destination, moves) }
                    .toMap()
                val validDestinations = validMovesLookup.keys
                updateViewModel(
                    viewModel.copy(
                        selectedCoordinate = coordinate,
                        validDestinations = validDestinations
                    )
                )
            } else {
                clearSelection()
            }
        } else {
            clearSelection()
        }
    }

    private fun getCurrentPlayerId(): Int {
        return game.playerTurn()
    }

}