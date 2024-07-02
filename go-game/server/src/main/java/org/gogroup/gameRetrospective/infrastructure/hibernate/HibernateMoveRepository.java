package org.gogroup.gameRetrospective.infrastructure.hibernate;

import jakarta.persistence.criteria.*;
import org.gogroup.gameRetrospective.domain.Move;
import org.gogroup.gameRetrospective.domain.MoveRepository;

import java.util.List;

public class HibernateMoveRepository implements MoveRepository
{
    private final Connection connection;

    public HibernateMoveRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Move move)
    {
        this.connection.save(move);
    }

    @Override
    public List<String> getGameIds() {
        CriteriaBuilder criteriaBuilder = this.connection.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Move> root = criteriaQuery.from(Move.class);

        criteriaQuery.select(root.get("gameId")).distinct(true);

        return (List<String>) this.connection.executeCriteriaQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Move> getWithPositionLowerThan(String gameId, int position) {
        CriteriaBuilder criteriaBuilder = this.connection.getCriteriaBuilder();
        CriteriaQuery<Move> criteriaQuery = criteriaBuilder.createQuery(Move.class);

        Root<Move> root = criteriaQuery.from(Move.class);
        Predicate condition = criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("movePosition"), position),
                criteriaBuilder.equal(root.get("gameId"), gameId)
        );

        criteriaQuery.select(root).where(condition);
        return (List<Move>) this.connection.executeCriteriaQuery(criteriaQuery).getResultList();
    }

    @Override
    public Integer getLastMovePosition(String gameId) {
        CriteriaBuilder criteriaBuilder = this.connection.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);

        Root<Move> root = criteriaQuery.from(Move.class);
        Predicate gameIdCondition = criteriaBuilder.equal(root.get("gameId"), gameId);

        Expression<Integer> maxMovePosition = criteriaBuilder.max(root.get("movePosition"));

        criteriaQuery.select(maxMovePosition).where(gameIdCondition);
        return (Integer) this.connection.executeCriteriaQuery(criteriaQuery).getSingleResult();
    }
}
