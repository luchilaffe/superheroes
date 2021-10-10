package com.mindata.superheroes.utils;

import lombok.Builder;

@Builder
public class RestEndpoints {

    private static final String SUPER_HEROES = "/superheroes";
    public static final String GET_ALL = SUPER_HEROES;
    public static final String GET = SUPER_HEROES;
    public static final String SEARCH = SUPER_HEROES + "/search";
    public static final String UPDATE = SUPER_HEROES;
    public static final String DELETE = SUPER_HEROES;

    private static final String USERS = "/users";
    public static final String USER_ADD = USERS;
    public static final String USER_AUTH = USERS + "/authenticate";

    private RestEndpoints() {}

}
