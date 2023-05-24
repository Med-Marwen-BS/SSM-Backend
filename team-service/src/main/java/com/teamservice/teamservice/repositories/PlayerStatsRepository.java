package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.PlayerStats;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

public interface PlayerStatsRepository extends MongoRepository<PlayerStats,String> {
    List<PlayerStats> findByMatchId(String  matchId);
    List<PlayerStats> findByPlayerId(String  playerId);
    List<PlayerStats> findBy(String  playerId);

    @Aggregation(pipeline = {
            "{$group: {_id: {player: '$player', category: '$player.category'}, maxGoals: {$max: '$goals'}}}",
            "{$project: {player: '$_id.player.id', category: '$_id.category.id', maxGoals: 1, _id: 0}}"
    })
    List<Map<String, Object>> findMaxGoalsByPlayerAndCategory();

    @Aggregation(pipeline = {
            "{$group: {totalGoals: {$sum: '$goals'}, totalSaves: {$sum: '$saves'}, totalMinutes: {$sum: '$minutes'}, totalShots: {$sum: '$shots'}}}"
    })
    List<PlayerStats> groupAndSumByPlayer();
}
