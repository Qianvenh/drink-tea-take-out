package com.drinktea.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate);
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        ListOperations<Object, Object> listOperations = redisTemplate.opsForList();
        SetOperations<Object, Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<Object, Object> zSetOperations = redisTemplate.opsForZSet();
    }

    @Test
    public void testString() {
        // set get setex setnx
        redisTemplate.opsForValue().set("city", "Beijing");
        String city = (String) redisTemplate.opsForValue().get("city");
        System.out.println(city);
        redisTemplate.opsForValue().set("code", "1234", 3, TimeUnit.MINUTES);
        redisTemplate.opsForValue().setIfAbsent("lock", 1);
        redisTemplate.opsForValue().setIfAbsent("lock", 2);
    }

    @Test
    public void testHash() {
        // hset hget hdel hkeys hvals
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("1000", "name", "tom");
        hashOperations.put("1000", "age", 18);
        String name = (String) hashOperations.get("1000", "name");
        System.out.println(name);
        Integer age = (Integer) hashOperations.get("1000", "age");
        System.out.println(age);
        Set<Object> keys = hashOperations.keys("1000");
        System.out.println(keys);
        List<Object> values = hashOperations.values("1000");
        System.out.println(values);
    }
}
