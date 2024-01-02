package com.suyh0303.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    static {
        initMapper(DEFAULT_MAPPER);
    }

    // 博客参考：https://www.cnblogs.com/yuluoxingkong/p/7676089.html
    public static void initMapper(ObjectMapper mapper) {
        // 设置默认日期的格式化，优先级低于 @JsonFormat
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        // 序列化的时候对null 属性进行忽略，所有的null 属性都不会被序列化到json 中。
        // ignored non null field
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 反序列化时,遇到未知属性(那些没有对应的属性来映射的属性,并且没有任何setter或handler来处理这样的属性)时
        // 是否引起结果失败(通过抛JsonMappingException异常).
        // 此项设置只对那些已经尝试过所有的处理方法之后并且属性还是未处理
        // (这里未处理的意思是:最终还是没有一个对应的类属性与此属性进行映射)的未知属性才有影响.
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 允许出现特殊字符和转义符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许出现单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public static void custom(ObjectMapper mapper) {
        // 字段保留，将null值转为""
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider)
                    throws IOException {
                jsonGenerator.writeString("");
            }
        });
    }


    /**
     * 序列化对象
     *
     * @param object 目标对象
     * @return 返回json 字符串
     */
    public static String serializable(Object object) {
        return serializable(object, DEFAULT_MAPPER);
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
        return deserialize(json, clazz, DEFAULT_MAPPER);
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
       return deserializeToList(json, clazz, DEFAULT_MAPPER);
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
        return deserializeToList02(json, clazz, DEFAULT_MAPPER);
    }

    public static <T> List<T> deserializeToList02(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(List.class, clazz);
            List<T> list = mapper.readValue(json, javaType);
            return list;
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
        return deserializeToMap(json, kClazz, vClass, DEFAULT_MAPPER);
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
        return DEFAULT_MAPPER.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return DEFAULT_MAPPER.createArrayNode();
    }

    /**
     * 将一个语法正确的json 字符串转换成一个JsonNode 对象
     *
     * @param json 语法正确的json 字符串
     * @return 返回一个JsonNode 对象
     */
    public static JsonNode deserializeToJsonNode(String json) {
        return deserializeToJsonNode(json, DEFAULT_MAPPER);
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
        return deserializeToArrayNode(json, DEFAULT_MAPPER);
    }

    public static ArrayNode deserializeToArrayNode(String json, ObjectMapper mapper) {
        return (ArrayNode) deserializeToJsonNode(json);
    }
}