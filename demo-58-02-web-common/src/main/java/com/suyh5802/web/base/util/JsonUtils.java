package com.suyh5802.web.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {
    private static volatile ObjectMapper OBJECT_MAPPER;

    public static void initMapper(ObjectMapper mapper) {
        OBJECT_MAPPER = mapper;
    }

    /**
     * 序列化对象
     *
     * @param object 目标对象
     * @return 返回json 字符串
     */
    public static String serializable(Object object) {
        return serializable(object, OBJECT_MAPPER);
    }

    public static String serializable(Object object, ObjectMapper mapper) {
        String res = null;
        try {
            res = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("serializable object failed. object: " + object, e);
        }

        return res;
    }

    /**
     * 反序列化对象
     *
     * @param json  json 字符串
     * @param clazz 解析的对象类型
     * @param <T>   对象类型
     * @return 返回对象实体
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        return deserialize(json, clazz, OBJECT_MAPPER);
    }

    public static <T> T deserialize(String json, Class<T> clazz, ObjectMapper mapper) {
        T res = null;
        try {
            res = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("deserialize object failed. json string: " + json, e);
        }

        return res;
    }

    /**
     * 数组反序列化
     *
     * @param json  json 格式字符串
     * @param clazz 泛型类型
     * @param <T>   泛型
     * @return 返回List 对象
     */
    public static <T> List<T> deserializeToList(String json, Class<T> clazz) {
       return deserializeToList(json, clazz, OBJECT_MAPPER);
    }

    public static <T> List<T> deserializeToList(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            ArrayType arrayType = mapper.getTypeFactory().constructArrayType(clazz);
            return mapper.readValue(json, arrayType);
        } catch (JsonProcessingException e) {
            log.error("deserializeList object failed. json string: " + json, e);
        }

        return null;
    }

    public static <T> List<T> deserializeToList02(String json, Class<T> clazz) {
        if (!StringUtils.hasText(json)) {
            return Collections.emptyList();
        } else {
            return deserializeToList02(json, clazz, OBJECT_MAPPER);
        }
    }

    public static <T> List<T> deserializeToList02(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(List.class, clazz);
            return mapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            log.error("deserializeToList02 failed. json string: {}", json, e);
        }

        return null;
    }

    /**
     * 反序列化 Map
     *
     * @param json   json 格式字符串
     * @param kClazz map-key
     * @param vClass map-value
     * @param <K>    map-key class
     * @param <V>    map-value class
     * @return 返回map
     */
    public static <K, V> Map<K, V> deserializeToMap(String json, Class<K> kClazz, Class<V> vClass) {
        return deserializeToMap(json, kClazz, vClass, OBJECT_MAPPER);
    }

    public static <K, V> Map<K, V> deserializeToMap(String json, Class<K> kClazz, Class<V> vClass, ObjectMapper mapper) {
        try {
            MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, kClazz, vClass);
            return mapper.readValue(json, mapType);
        } catch (JsonProcessingException e) {
            log.error("deserializeMap object failed. json string: " + json, e);
        }

        return null;
    }

    public static ObjectNode createObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return OBJECT_MAPPER.createArrayNode();
    }

    /**
     * 将一个语法正确的json 字符串转换成一个JsonNode 对象
     *
     * @param json 语法正确的json 字符串
     * @return 返回一个JsonNode 对象
     */
    public static JsonNode deserializeToJsonNode(String json) {
        return deserializeToJsonNode(json, OBJECT_MAPPER);
    }

    public static JsonNode deserializeToJsonNode(String json, ObjectMapper mapper) {
        JsonNode res = null;
        try {
            res = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("deserializeMap object failed. json string: " + json, e);
        }

        return res;
    }

    /**
     * 将一个json 数组字符串转换成一个ArrayNode 对象
     * <p>
     * JsonNode 可以是数组，如果确定是数组则可以直接强制类型转换
     *
     * @param json 语法正确的Json 数组字符串
     * @return 返回一个ArrayNode 对象
     */
    public static ArrayNode deserializeToArrayNode(String json) {
        return deserializeToArrayNode(json, OBJECT_MAPPER);
    }

    public static ArrayNode deserializeToArrayNode(String json, ObjectMapper mapper) {
        return (ArrayNode) deserializeToJsonNode(json);
    }
}