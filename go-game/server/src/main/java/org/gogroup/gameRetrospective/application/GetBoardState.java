package org.gogroup.gameRetrospective.application;

import org.gogroup.game.domain.Board;
import org.gogroup.game.domain.GameStatus;
import org.gogroup.game.domain.PawnGroup;
import org.gogroup.game.domain.PawnGroupCollector;
import org.gogroup.gameRetrospective.domain.Move;
import org.gogroup.gameRetrospective.domain.MoveRepository;
import org.gogroup.gameRetrospective.domain.exception.NoMoreMovesException;
import org.gogroup.shared.readModel.GameModel;

import java.util.ArrayList;
import java.util.List;

public class GetBoardState {
    private final MoveRepository moveRepository;
    private final PawnGroupCollector pawnGroupCollector;

    public GetBoardState(MoveRepository moveRepository, PawnGroupCollector pawnGroupCollector) {
        this.moveRepository = moveRepository;
        this.pawnGroupCollector = pawnGroupCollector;
    }

    public GameModel execute(String gameId, String clientId, Integer moveId) throws NoMoreMovesException {
        Integer lastPosition = moveRepository.getLastMovePosition(gameId);
        if (lastPosition < moveId) {
            throw NoMoreMovesException.create();
        }

        List<Move> moveList = moveRepository.getWithPositionLowerThan(gameId, moveId);
        Move firstMove = moveList.get(0);

        Board board = new Board(firstMove.getBoardSize());
        moveList.forEach(move -> {
            board.makeMove(move.getClientId(), move.getX(), move.getY());
            this.applyAttack(board);
        });

        return new GameModel(
                gameId,
                clientId,
                firstMove.getClientId(),
                clientId,
                clientId,
                board.getFields(),
                GameStatus.FINISHED
        );
    }

    private void applyAttack(Board board) {
        ArrayList<PawnGroup> groups = this.pawnGroupCollector.collect(board);
        for (PawnGroup group : groups) {
            group.calculateBreaths(board);
            if (group.getBreaths() > 0) {
                continue;
            }

            group.getPawns().forEach(pawn -> {
                board.remove(pawn.getX(), pawn.getY());
            });
        }
    }
}
