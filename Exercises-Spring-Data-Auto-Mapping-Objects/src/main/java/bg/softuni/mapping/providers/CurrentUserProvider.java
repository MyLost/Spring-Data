package bg.softuni.mapping.providers;

import bg.softuni.mapping.dtos.UserDto;
import bg.softuni.mapping.dtos.UserLoginDto;

public class CurrentUserProvider {

    private CurrentUserProvider() {}

    private static UserDto currentUser;

    public static void setCurrentUser(UserDto user) {

        if(currentUser == null) {
            CurrentUserProvider.currentUser = user;
        }
    }

    public static UserDto getCurrentUser() {
        
        return currentUser;
    }

    public static Boolean isUserLoggedIn() {

        return currentUser != null;
    }
}
