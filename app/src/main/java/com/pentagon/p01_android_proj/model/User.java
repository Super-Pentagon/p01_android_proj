package com.pentagon.p01_android_proj.model;

public class User {

    private String password;
    private String username;

    public User() {
    }

    public static UserBuilder init() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        String password;
        String username;

        private UserBuilder() {}

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            return user;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
