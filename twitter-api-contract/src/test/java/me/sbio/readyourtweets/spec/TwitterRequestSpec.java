package me.sbio.readyourtweets.spec;

import com.jayway.restassured.specification.RequestSpecification;

public interface TwitterRequestSpec {

    RequestSpecification asRequestSpecification();

    String path();
}
