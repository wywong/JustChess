package com.example.justchess.controller

import com.example.justchess.GameController
import com.example.justchess.GameViewModel
import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.Game
import com.example.justchess.engine.Move
import com.example.justchess.engine.Piece

abstract class BaseGameController(
    final override val game: Game
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
        postPlayerMoves()
        viewModel = GameViewModel(
            game.getCurrentBoard().getPieces(),
            null,
            emptyList()
        )
    }

    private fun isCoordinateValidMove(coordinate: Coordinate): Boolean {
        return viewModel.validDestinations.contains(coordinate)
    }

    protected fun executePlayerMoves(moves: Collection<Move>?) {
        if (!moves.isNullOrEmpty()) {
            game.applyMoves(moves)
            viewModel = GameViewModel(
                game.getCurrentBoard().getPieces(),
                null,
                emptyList()
            )
        }
    }

    protected open fun postPlayerMoves() {

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