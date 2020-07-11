package xyz.wywong.justchess.engine.game

import xyz.wywong.justchess.ChessUtil
import xyz.wywong.justchess.engine.*
import xyz.wywong.justchess.engine.piece.Pawn
import java.util.*

class DefaultGame(
    initialBoard: Board,
    initialPlayerTurn: Int
) : Game {
    private var currentBoard: Board = initialBoard
    private var currentPlayer: Int = initialPlayerTurn
    private var turnHistory: ArrayDeque<Turn> = ArrayDeque()
    private var promotablePawnCoordinate: Coordinate? = null

    override fun getCurrentBoard(): Board {
        return currentBoard
    }

    override fun getTurnHistory(): Collection<Turn> {
        return turnHistory
    }

    override fun applyMoves(moves: Collection<Move>): Turn {
        val removedPieces = getRemovedPieces(moves)
        currentBoard = currentBoard.applyMoves(moves)
        val turn = Turn(
            moves,
            removedPieces
        )
        turnHistory.push(turn)
        nextTurn()
        return turn
    }

    override fun canUndo(): Boolean {
        return turnHistory.isNotEmpty()
    }

    override fun undo(): Turn {
        if (turnHistory.isNotEmpty()) {
            val turn = turnHistory.pop()
            currentBoard = turn.moves.fold(currentBoard) { board, move ->
                board.movePiece(
                    move.origin,
                    move.piece
                )
            }
            currentBoard = turn.removedPieces.fold(currentBoard) { board, piece ->
                board.movePiece(
                    piece.location,
                    piece
                )
            }
            changePlayer()
            return turn
        } else {
            throw Exception("No turn to undo")
        }
    }

    override fun playerTurn(): Int {
        return currentPlayer
    }

    override fun checkmatedPlayerId(): Int? {
        val hasNoMoves = !currentBoard.getPiecesForPlayer(currentPlayer)
            .any { piece -> piece.getValidMoves(currentBoard).isNotEmpty() }
        return if (hasNoMoves && currentBoard.isKingInCheck(currentPlayer)) {
            currentPlayer
        } else {
            null
        }
    }

    override fun isStalemate(): Boolean {
        val hasNoMoves = !currentBoard.getPiecesForPlayer(currentPlayer)
            .any { piece -> piece.getValidMoves(currentBoard).isNotEmpty() }
        return hasNoMoves && !currentBoard.isKingInCheck(currentPlayer)
    }

    override fun getPromotablePawnCoordinate(): Coordinate? {
        return promotablePawnCoordinate
    }

    override fun promotePawn(promotedPiece: Piece): Turn {
        val turn = applyMoves(
            listOf(
                Move(
                    promotedPiece.location,
                    promotedPiece
                )
            )
        )
        promotablePawnCoordinate = null
        return turn
    }

    private fun getRemovedPieces(moves: Collection<Move>): Collection<Piece> {
        return moves.mapNotNull { move ->
            val captureLocation = move.captureLocation(currentBoard)
            currentBoard.getPiece(captureLocation)
        }
    }

    /**
     * checks for specific events:
     * * pawn promotion - this will get the new promoted piece from the event listener
     *   and place it in the pawn's current location
     * * next player is checkmated - this will notify the game event listener that the game is over
     * * otherwise - change current player to the other player
     */
    private fun nextTurn() {
        val promotablePiece = findPromotablePawn()
        when {
            promotablePiece != null ->
                promotablePawnCoordinate = promotablePiece.location
            else -> changePlayer()
        }
    }

    /**
     * returns a promotable pawn for the current player if it exists,
     *         otherwise return null
     */
    private fun findPromotablePawn(): Piece? {
        return currentBoard.getPiecesForPlayer(currentPlayer)
            .find { piece ->
                val y = piece.location.y
                val isPawn = piece is Pawn
                val atFinalRow = (y == 0 || y == 7)
                isPawn && atFinalRow
            }
    }


    private fun changePlayer() {
        currentPlayer = otherPlayerId()
    }

    private fun otherPlayerId(): Int {
        return ChessUtil.getOtherPlayerId(currentPlayer)
    }
}