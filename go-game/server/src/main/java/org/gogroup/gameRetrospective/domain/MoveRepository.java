package org.gogroup.gameRetrospective.domain;

import java.util.List;

public interface MoveRepository {
    public void save(Move move);

    public List<String> getGameIds();

    List<Move> getWithPositionLowerThan(String gameId, int position);

    Integer getLastMovePosition(String gameId);
}
