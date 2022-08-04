package user;

import com.github.javafaker.Faker;

public class User {
    private static String name;
    private static String email;
    private static String password;
    public static final String SHORT_PASS = "123";

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(){
    }

    public static User createUserRandom(){
        Faker faker= new Faker();
        return new User(faker.name().name(),faker.internet().emailAddress(),faker.internet().password(7,20));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
