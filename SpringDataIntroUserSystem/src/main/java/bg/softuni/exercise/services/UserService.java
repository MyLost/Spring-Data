package bg.softuni.exercise.services;

import bg.softuni.exercise.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(Date date);

    void save(User user);

    long getUsersCount();

    List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound);
}
