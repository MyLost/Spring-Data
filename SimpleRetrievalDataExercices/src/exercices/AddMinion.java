package exercices;

import com.sun.jdi.ClassNotPreparedException;
import database.DB;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class AddMinion {

    private static final String TOWN_QUERY = "select name from towns";

    private static final String INSERT_TOWN_QUERY = "insert into towns (name) values(?)";

    private static final String SELCT_TOWN_QUERY = "select id from towns where name=?";

    private static final String INSERT_INTO_MINIONS_QUERY = "insert into minions (name, age, town_id) values (?,?,?)";

    private static final String SELECT_FROM_MINIONS_QUERY = "select * from minions where name = ?";

    private static final String SELECT_FROM_VILLAIN_QUERH = "select * from villains v";

    private static final String INSERT_INTO_VILLAIN_QUERY = "insert into villains(name, evilness_factor) values(?,?)";

    public static final String VILLAIN_QUARY = "select * from villains v where name =?";

    public static final String INSERT_INTO_MINION_VILLAIN = "insert into minions_villains(minion_id, villain_id) values (?,?)";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DB.getConnection();
        String[] minionTokens = Arrays.stream(scanner.nextLine().split(":")[1].trim().split("\\s+")).map(item -> item.trim()).toArray(String[]::new);

        String minionName = minionTokens[0];
        Integer minionAge = Integer.parseInt(minionTokens[1]);
        String minionTown = minionTokens[2];

        PreparedStatement statement = connection.prepareStatement(TOWN_QUERY);
        ResultSet result = statement.executeQuery();

        boolean townFlag = false;

        while(result.next()) {
            if(result.getString("name").equals(minionTown)) townFlag = true;
        }

        if(!townFlag) {
            statement = connection.prepareStatement(INSERT_TOWN_QUERY);
            statement.setString(1, minionTown);
            statement.execute();
        }

        PreparedStatement townstatement = connection.prepareStatement(SELCT_TOWN_QUERY);
        townstatement.setString(1, minionTown);
        Integer townId = null;
        result = townstatement.executeQuery();
        result.next();
        townId = result.getInt("id");

        PreparedStatement insertStatement = connection.prepareStatement(INSERT_INTO_MINIONS_QUERY);
        insertStatement.setString(1, minionName);
        insertStatement.setInt(2, minionAge);
        insertStatement.setInt(3, townId);
        insertStatement.execute();

        String villainName = scanner.nextLine().split(":")[1].trim();

        PreparedStatement minionStatement = connection.prepareStatement(SELECT_FROM_MINIONS_QUERY);
        minionStatement.setString(1, minionName);

        ResultSet minionSet = minionStatement.executeQuery();
        minionSet.next();
        Integer minionId = minionSet.getInt("id");

        PreparedStatement villainStatement = connection.prepareStatement(SELECT_FROM_VILLAIN_QUERH);
        ResultSet villainResult = villainStatement.executeQuery();

        boolean villianFlag = false;
        while(villainResult.next()) {
            String villainNameFromDb  = villainResult.getString("name");
            if (villainNameFromDb.equals(villainName)) villianFlag = true;
        }
            Integer villianId = null;
            if (villianFlag) {
                villianId = villainResult.getInt("id");
            } else {
                PreparedStatement insertVillain = connection.prepareStatement(INSERT_INTO_VILLAIN_QUERY);
                insertVillain.setString(1, villainName);
                insertVillain.setString(2, "evil");
                insertVillain.execute();

                PreparedStatement villainStatementTwo = connection.prepareStatement(VILLAIN_QUARY);
                villainStatementTwo.setString(1, villainName);
                ResultSet villainResultTwo = villainStatementTwo.executeQuery();
                villainResultTwo.next();
                villianId = villainResultTwo.getInt("id");
            }

        PreparedStatement minionsVillains = connection.prepareStatement(INSERT_INTO_MINION_VILLAIN);
        minionsVillains.setInt(1, minionId);
        minionsVillains.setInt(2, villianId);
        minionsVillains.execute();
    }
}
