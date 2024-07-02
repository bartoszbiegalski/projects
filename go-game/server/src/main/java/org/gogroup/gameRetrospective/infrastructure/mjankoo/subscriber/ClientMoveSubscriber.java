package org.gogroup.gameRetrospective.infrastructure.mjankoo.subscriber;

import org.gogroup.game.domain.event.ClientMovedEvent;
import org.gogroup.gameRetrospective.domain.Move;
import org.gogroup.gameRetrospective.domain.MoveRepository;
import org.gogroup.shared.readModel.GameModel;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientMoveSubscriber implements EventSubscriberInterface
{
    private MoveRepository moveRepository;

    public ClientMoveSubscriber(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(ClientMovedEvent.class, new ArrayList<>(
                    Arrays.asList("saveMove", 0)
            ));
        }};
    }

    public void saveMove(ClientMovedEvent event)
    {
        GameModel gameModel = event.getGameModel();

        Integer position = this.moveRepository.getLastMovePosition(gameModel.getGameId());
        if (position == null) {
            position = 0;
        } else {
            position++;
        }

        Move move = new Move();
        move.setGameId(event.getGameId());
        move.setClientId(event.getClientId());
        move.setY(event.getY());
        move.setX(event.getX());
        move.setMovePosition(position);
        move.setBoardSize(gameModel.getBoard().getBoard().length);

        this.moveRepository.save(move);
    }
}
