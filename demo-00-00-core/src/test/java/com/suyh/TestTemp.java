package com.suyh;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.suyh.entity.Person;
import com.suyh.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        Person person01 = new Person("id", "name", "sex");
        List<Person> list = new ArrayList<>();
        person01.setCreatedDate(new Date());
        list.add(person01);
        Person person02 = new Person("id02", "name02", "sex02");
        person02.setUpdateDate(new Date());
        list.add(person02);
        String serializable = JsonUtil.serializable(list);
        System.out.println("serializable: " + serializable);
        ArrayNode jsonNodes = JsonUtil.deserializeToArrayNode(serializable);
        System.out.println("deserialize: " + jsonNodes);
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
