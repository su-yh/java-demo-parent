package com.suyh;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.suyh.entity.Person;
import com.suyh.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class TestTemp {

    @Test
    public void test01() {
        System.out.println("test01");

        Person person = new Person("id", "name", "sex");
        String serializableJson = JsonUtil.serializable(person);
        System.out.println("serializableJson: " + serializableJson);
        Person personRes = JsonUtil.deserialize(serializableJson, Person.class);
        System.out.println("personRes: " + personRes);
    }

    @Test
    public void test02() {
        Person person = new Person("id", "name", "sex");
        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person);
        String serializable = JsonUtil.serializable(list);
        System.out.println("serializable: " + serializable);
        ArrayNode jsonNodes = JsonUtil.deserializeToArrayNode(serializable);
        System.out.println("desc: " + jsonNodes);
    }

    @Test
    public void test03() {
        Person person = new Person("id", "name", "sex");
        String serializableJson = JsonUtil.serializable(person);
        System.out.println("serializableJson: " + serializableJson);
        Person personRes = JsonUtil.deserialize(serializableJson, Person.class);
        System.out.println("personRes: " + personRes);
    }
}
