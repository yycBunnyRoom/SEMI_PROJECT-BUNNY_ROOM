package com.yyc.bunnyroom.common;

public enum UserRole {

    GUEST("GUEST"),
    HOST("HOST"),
    ADMIN("ADMIN");
    private String role;
    UserRole(String role){this.role = role;}

    public String getRole(){return role;}

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
