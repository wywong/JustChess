package com.example.justchess.controller

import com.example.justchess.engine.Coordinate
import com.example.justchess.engine.GameFactory
import com.example.justchess.engine.PieceImageProvider
import com.example.justchess.engine.factory.DefaultGameFactory
import org.junit.Test

class TwoPersonGameControllerUnitTest {
    private val emptyImageProvider: PieceImageProvider = PieceImageProvider(
        null,
        null,
        null,
        null,
        null,
        null
    )
    private val gameFactory: GameFactory = DefaultGameFactory(
        emptyImageProvider, emptyImageProvider
    )

    @Test
    fun can_select_white_pieces_during_white_turn() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        assert(controller.getViewModel().selectedCoordinate == coordinate)
    }

    @Test
    fun can_should_have_two_valid_moves_selected_piece() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        assert(controller.getViewModel().validDestinations.size == 2)
    }

    @Test
    fun piece_should_move_after_selecting_valid_move() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val origin = Coordinate(0, 6)
        controller.selectCoordinate(origin)
        val destination = Coordinate(0, 4)
        controller.selectCoordinate(destination)
        val pieces = controller.getViewModel().pieces
        assert(pieces.all { piece -> origin != piece.location })
        assert(pieces.any { piece -> destination != piece.location })
    }

    @Test
    fun should_clear_current_selection_if_selecting_same_coordinate_again() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        controller.selectCoordinate(coordinate)
        assert(controller.getViewModel().selectedCoordinate == null)
        assert(controller.getViewModel().validDestinations.isEmpty())
    }

    @Test
    fun should_clear_current_selection_if_selecting_other_player_piece() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        controller.selectCoordinate(Coordinate(0, 1))
        assert(controller.getViewModel().selectedCoordinate == null)
        assert(controller.getViewModel().validDestinations.isEmpty())
    }

    @Test
    fun should_clear_current_selection_if_selecting_piece_no_moves() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        controller.selectCoordinate(Coordinate(4, 7))
        assert(controller.getViewModel().selectedCoordinate == null)
        assert(controller.getViewModel().validDestinations.isEmpty())
    }

    @Test
    fun should_clear_current_selection_if_selecting_empty_cell() {
        val game = gameFactory.createNewGame()
        val controller = TwoPersonGameController(game)
        val coordinate = Coordinate(0, 6)
        controller.selectCoordinate(coordinate)
        controller.selectCoordinate(Coordinate(4, 4))
        assert(controller.getViewModel().selectedCoordinate == null)
        assert(controller.getViewModel().validDestinations.isEmpty())
    }
}