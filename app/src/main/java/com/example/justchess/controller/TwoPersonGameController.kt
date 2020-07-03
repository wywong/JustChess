package com.example.justchess.controller

import com.example.justchess.GameController
import com.example.justchess.GameViewModel
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.Move

/**
 * A controller for a two person game
 */
class TwoPersonGameController(
    private val game: Game
) : GameController {
    private var viewModel: GameViewModel = GameViewModel(
        game.getCurrentBoard().getPieces(),
        null,
        emptyList()
    )
    private var validMovesLookup: Map<Coordinate, Collection<Move>> = emptyMap()

    override fun getViewModel(): GameViewModel {
        return viewModel
    }

    override fun selectCoordinate(coordinate: Coordinate) {
        when {
            isCoordinateValidMove(coordinate) -> executeMoves(coordinate)
            coordinate == viewModel.selectedCoordinate -> clearSelection()
            else -> applySelection(coordinate)
        }
    }

    private fun isCoordinateValidMove(coordinate: Coordinate): Boolean {
        return viewModel.validDestinations.contains(coordinate)
    }

    private fun executeMoves(coordinate: Coordinate) {
        val moves = validMovesLookup[coordinate]
        if (!moves.isNullOrEmpty()) {
            game.applyMoves(moves)
            viewModel = GameViewModel(
                game.getCurrentBoard().getPieces(),
                null,
                emptyList()
            )
        }
    }

    private fun clearSelection() {
        viewModel = viewModel.copy(
            selectedCoordinate = null,
            validDestinations = emptyList()
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
                viewModel = viewModel.copy(
                    selectedCoordinate = coordinate,
                    validDestinations = validDestinations
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
