package com.mindata.superheroes.utils;

import lombok.Builder;

@Builder
public class RestEndpoints {

    private static final String ROOT = "/superheroes";
    public static final String GET_ALL = ROOT;
    public static final String GET = ROOT;
    public static final String SEARCH = ROOT + "/search";

}
