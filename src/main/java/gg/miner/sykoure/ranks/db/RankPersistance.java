package gg.miner.sykoure.ranks.db;

import gg.miner.sykoure.ranks.data.PlayerRank;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankPersistance {

    public static void main(String[] args) {
        ArrayList<PlayerRank> a = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            a.add(new PlayerRank(Integer.toString(i * 2), i));

        persistRanks(a);
    }

    /**
     * Stores a list of PlayerRank into the database
     *
     * @param list The list of PlayerRank
     */
    @SneakyThrows(SQLException.class)
    private static void persistRanks(ArrayList<PlayerRank> list) {
        String insertQuery = "INSERT INTO user_ranks (uuid, rank_id) VALUES (?,?)";

        Connection dbConnection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery);


        for (PlayerRank rank : list) {
            preparedStatement.setString(1, rank.getUuid());
            preparedStatement.setInt(2, rank.getRankId());

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }
}
