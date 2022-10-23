package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintAllMinionNames {

    private static final String SELECT_ALL_MINIONS_QUERY = "select * from minions m order by id;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection conn = DB.getConnection();

        PreparedStatement statement = conn.prepareStatement(SELECT_ALL_MINIONS_QUERY);
        ResultSet result = statement.executeQuery();

        LinkedList<String> minionNames= new LinkedList<>();
        while(result.next()) {
            minionNames.add(result.getString("name"));
        }

        LinkedList<String> firstHalfMinionNames = new LinkedList<>();
        LinkedList<String> secondHalfMinionNames =  new LinkedList<>();
        ArrayDeque<String> mergedMinions;
        int minuonsNumber = minionNames.size();
        for (int i = 0; i < minuonsNumber / 2; i++) {
            firstHalfMinionNames.add(minionNames.get(i));
        }

        for (int i = minuonsNumber; i > minuonsNumber / 2; i--) {
            secondHalfMinionNames.add(minionNames.get(i-1));
        }

        Collections.reverse(secondHalfMinionNames);
        mergedMinions = Stream.concat(firstHalfMinionNames.stream(), secondHalfMinionNames.stream()).collect(Collectors.toCollection(ArrayDeque::new));

        while (!mergedMinions.isEmpty()) {
            System.out.println(mergedMinions.pollFirst());
            System.out.println(mergedMinions.pollLast());
        }

    }
}
