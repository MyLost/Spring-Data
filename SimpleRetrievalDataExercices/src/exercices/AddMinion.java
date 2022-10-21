package exercices;

import com.sun.jdi.ClassNotPreparedException;
import database.DB;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class AddMinion {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        Connection connection = DB.getConnection();
        String[] minionTokens = Arrays.stream(scanner.nextLine().split(":")[1].trim().split("\\s+")).map(item -> item.trim()).toArray(String[]::new);

        String minionName = minionTokens[0];
        Integer minionAge = Integer.parseInt(minionTokens[1]);
        String minionTown = minionTokens[2];

        PreparedStatement statement = connection.prepareStatement("select name from towns");
        ResultSet result = statement.executeQuery();

        boolean townFlag = false;

        while(result.next()) {
            if(result.getString("name").equals(minionTown)) townFlag = true;
        }

        if(!townFlag) {
            statement = connection.prepareStatement("insert into towns (name) values(?)");
            statement.setString(1, minionTown);
            statement.execute();
        }

        PreparedStatement townstatement = connection.prepareStatement("select id from towns where name=?");
        townstatement.setString(1, minionTown);
        Integer townId = null;
        result = townstatement.executeQuery();
        result.next();
        townId = result.getInt("id");

        PreparedStatement insertStatement = connection.prepareStatement("insert into minions (name, age, town_id) values (?,?,?)");
        insertStatement.setString(1, minionName);
        insertStatement.setInt(2, minionAge);
        insertStatement.setInt(3, townId);
        insertStatement.execute();

        String villainName = scanner.nextLine().split(":")[1].trim();

        PreparedStatement minionStatement = connection.prepareStatement("select * from minions where name = ?");
        minionStatement.setString(1, minionName);

        ResultSet minionSet = minionStatement.executeQuery();
        minionSet.next();
        Integer minionId = minionSet.getInt("id");

        PreparedStatement villainStatement = connection.prepareStatement("select * from villains v");
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
                PreparedStatement insertVillain = connection.prepareStatement("insert into villains(name, evilness_factor) values(?,?)");
                insertVillain.setString(1, villainName);
                insertVillain.setString(2, "evil");
                insertVillain.execute();

                PreparedStatement villainStatementTwo = connection.prepareStatement("select * from villains v where name =?");
                villainStatementTwo.setString(1, villainName);
                ResultSet villainResultTwo = villainStatementTwo.executeQuery();
                villainResultTwo.next();
                villianId = villainResultTwo.getInt("id");
            }

        PreparedStatement minionsVillains = connection.prepareStatement("insert into minions_villains(minion_id, villain_id) values (?,?)");
        minionsVillains.setInt(1, minionId);
        minionsVillains.setInt(2, villianId);
        minionsVillains.execute();
    }
}
