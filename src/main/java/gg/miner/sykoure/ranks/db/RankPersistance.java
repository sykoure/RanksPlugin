package gg.miner.sykoure.ranks.db;

import gg.miner.sykoure.ranks.data.PlayerRank;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankPersistance {

    public static void main(String[] args) {
        ArrayList<PlayerRank> a = new ArrayList<>();
        for (int i = 1; i < 5; i++)
            a.add(new PlayerRank(Integer.toString(i * 2), i));

        truncateRanks("user_ranks");
        persistRanks(a);
        ArrayList<PlayerRank> b = retrieveRanks();

        for (PlayerRank p : b)
            System.out.println(p);
    }

    @SneakyThrows(SQLException.class)
    private static void truncateRanks(String dbName) {
        Connection connection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE " + dbName);
        preparedStatement.execute();
        preparedStatement.close();
        System.out.println("Table " + dbName + " truncated.");
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

    /**
     * Retrieves a list of PlayerRanks from the database
     *
     * @return The list of PlayerRank
     */
    @SneakyThrows(SQLException.class)
    private static ArrayList<PlayerRank> retrieveRanks() {
        ArrayList<PlayerRank> out = new ArrayList<>();
        String selectQuery = "SELECT * FROM user_ranks";

        Connection dbConnection = JdbcConnection.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next())
            out.add(new PlayerRank(result.getString("uuid"), result.getInt("rank_id")));

        preparedStatement.close();

        return out;
    }
}
