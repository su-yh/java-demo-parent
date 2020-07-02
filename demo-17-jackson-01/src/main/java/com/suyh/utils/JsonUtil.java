package com.suyh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtil {
    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 序列化对象
     *
     * @param object 目标对象
     * @return 返回json 字符串
     */
    public static String serializable(Object object) {
        String res = null;
        try {
            res = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("serializable object failed. object: " + object, e);
        }

        return res;
    }

    /**
     * 反序列化对象
     *
     * @param json json 字符串
     * @param clazz 解析的对象类型
     * @param <T> 对象类型
     * @return 返回对象实体
     */
    public static <T> T deserialize(String json, Class<T> clazz) {
        T res = null;
        try {
            res = MAPPER.readValue(json, clazz);
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
     * @param <T>  泛型
     * @return 返回List 对象
     */
    public static <T> List<T> deserializeToList(String json, Class<T> clazz) {
        try {
            ArrayType arrayType = MAPPER.getTypeFactory().constructArrayType(clazz);
            return MAPPER.readValue(json, arrayType);
        } catch (JsonProcessingException e) {
            log.error("deserializeList object failed. json string: " + json, e);
        }

        return null;
    }

    /**
     * 反序列化 Map
     *
     * @param json json 格式字符串
     * @param kClazz map-key
     * @param vClass map-value
     * @param <K> map-key class
     * @param <V> map-value class
     * @return 返回map
     */
    public static <K, V> Map<K, V> deserializeToMap(String json, Class<K> kClazz, Class<V> vClass) {
        try {
            MapType mapType = MAPPER.getTypeFactory().constructMapType(Map.class, kClazz, vClass);
            return MAPPER.readValue(json, mapType);
        } catch (JsonProcessingException e) {
            log.error("deserializeMap object failed. json string: " + json, e);
        }

        return null;
    }

    public static ObjectNode createObjectNode() {
        return MAPPER.createObjectNode();
    }
    public static ArrayNode createArrayNode() {
        return MAPPER.createArrayNode();
    }

    /**
     * 将一个语法正确的json 字符串转换成一个JsonNode 对象
     *
     * @param json 语法正确的json 字符串
     * @return 返回一个JsonNode 对象
     */
    public static JsonNode deserializeToJsonNode(String json) {
        JsonNode res = null;
        try {
            res = MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("deserializeMap object failed. json string: " + json, e);
        }

        return res;
    }

    /**
     * 将一个json 数组字符串转换成一个ArrayNode 对象
     *
     * JsonNode 可以是数组，如果确定是数组则可以直接强制类型转换
     * @param json 语法正确的Json 数组字符串
     * @return 返回一个ArrayNode 对象
     */
    public static ArrayNode deserializeToArrayNode(String json) {
        return (ArrayNode) deserializeToJsonNode(json);
    }
}
