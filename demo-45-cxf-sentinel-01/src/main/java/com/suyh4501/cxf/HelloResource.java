package com.suyh4501.cxf;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class HelloResource {

    @GET
    public HelloEntity sayHello() {
        return new HelloEntity("hello");
    }

    @GET
    @Path("/{id}")
    public HelloEntity get(@PathParam(value = "id") Long id) {
        return new HelloEntity(id, "hello");
    }

    @GET
    @Path("/list")
    public List<HelloEntity> getAll() {
        return IntStream.rangeClosed(1, 1000)
                .mapToObj(i -> new HelloEntity((long)i, "hello"))
                .collect(Collectors.toList());
    }

    @Path("/ex")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String exception() {
        throw new RuntimeException("test exception mapper");
    }
}
