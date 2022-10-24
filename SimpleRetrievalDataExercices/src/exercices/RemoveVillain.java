package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    private static final String SELECT_VILLAINS_QUERY = "select * from villains v where v.id  = ?";

    private static final String UPDATE_MINIONS_VILLASINS_QUERY = "update minions_villains mv set mv.villain_id = null where mv.villain_id = ?";

    private static final String DELETE_VILLAIN_QUERY = "delete v from villains v where v.id = ?";


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter villain id");
            Integer villainId = Integer.parseInt(scanner.nextLine());

            PreparedStatement statement = conn.prepareStatement(SELECT_VILLAINS_QUERY);
            statement.setInt(1, villainId);
            ResultSet result = statement.executeQuery();
            result.next();
            if(result.getRow() > 0) {
                String villainName = result.getString("name");
                statement = conn.prepareStatement(UPDATE_MINIONS_VILLASINS_QUERY);
                statement.setInt(1, villainId);
                Integer numberOfMinions = statement.executeUpdate();
                statement = conn.prepareStatement(DELETE_VILLAIN_QUERY);
                statement.setInt(1, villainId);
                statement.execute();
                System.out.println(String.format("%s was deleted", result.getString("name")));
                System.out.println(String.format("%d minions released", numberOfMinions));
            } else {
                System.out.println("No such villain was found");
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }
}
