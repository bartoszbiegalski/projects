package org.gogroup.gameRetrospective.application;

import org.gogroup.gameRetrospective.domain.MoveRepository;

import java.util.List;

public class GetPreviousGames {
    private MoveRepository moveRepository;

    public GetPreviousGames(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }
    public List<String> execute()
    {
        return this.moveRepository.getGameIds();
    }
}
