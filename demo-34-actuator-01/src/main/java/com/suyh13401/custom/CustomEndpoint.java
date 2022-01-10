package com.suyh13401.custom;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestControllerEndpoint(id = "suyh-custom")
public class CustomEndpoint {
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public String name(@PathVariable("name") String name) {
        return name;
    }
}
