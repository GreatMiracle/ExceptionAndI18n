package com.example.demo.respository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class RedisRepo {

//
//    private static final String PREFIX = "REDIS_";
//
//    private HashOperations hashOperations;
//
//    public RedisRepo(RedisTemplate redisTemplate) {
//        this.hashOperations = redisTemplate.opsForHash();
//    }
//
//    public void create(Student transferReserveDTO, String transactionId) {
//        hashOperations.put(PREFIX + transferReserveDTO.getName(), transactionId, JsonF.toString(transferReserveDTO));
//    }
//
//    public void delete(String inputter, String transactionId) {
//        hashOperations.delete(PREFIX + inputter, transactionId);
//    }
//
//    public boolean get(String inputter, String transactionId) {
//        if (Objects.nonNull(hashOperations.get(PREFIX + inputter, transactionId))) {
//            return false;
//        }
//        return true;
//    }
//
//    public Object getByTransactionId(String inputter, String transactionId) {
//        return hashOperations.get(PREFIX + inputter, transactionId);
//    }
//
//    public Map<String, String> getAll(String key) {
//        return hashOperations.entries(key);
//    }

}
