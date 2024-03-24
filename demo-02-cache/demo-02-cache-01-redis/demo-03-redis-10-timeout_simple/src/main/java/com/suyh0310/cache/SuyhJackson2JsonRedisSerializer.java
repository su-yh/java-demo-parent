//package com.suyh0310.cache;
//
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//public class SuyhJackson2JsonRedisSerializer implements RedisSerializer<Object> {
//    static final byte[] EMPTY_ARRAY = new byte[0];
//
//    private final StringRedisSerializer stringRedisSerializer = StringRedisSerializer.UTF_8;
//    private final Jackson2JsonRedisSerializer<SuyhRedisSerialize> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(SuyhRedisSerialize.class);
//
//    @Override
//    public byte[] serialize(Object t) throws SerializationException {
//        if (t == null) {
//            return EMPTY_ARRAY;
//        }
//
//        if (String.class.isAssignableFrom(t.getClass())) {
//            return stringRedisSerializer.serialize((String) t);
//        }
//
//        return jackson2JsonRedisSerializer.serialize(t);
//    }
//
////    @Override
////    public byte[] serialize(Object o) throws SerializationException {
////        return new byte[0];
////    }
//
//    @Override
//    public Object deserialize(byte[] bytes) throws SerializationException {
//        return null;
//    }
////
////    @Override
////    public T deserialize(byte[] bytes) throws SerializationException {
////        if (String.class.isAssignableFrom(t.getClass())) {
////            return stringRedisSerializer.deserialize((String) t);
////        }
////        return super.deserialize(bytes);
////    }
//
//    @Override
//    public boolean canSerialize(Class<?> type) {
//        return SuyhRedisSerialize.class.isAssignableFrom(type) || String.class.isAssignableFrom(type);
//    }
//}
