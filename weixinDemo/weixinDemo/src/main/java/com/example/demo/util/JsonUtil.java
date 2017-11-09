package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    /**
     * json解析
     * @param json
     * @param T
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> T decode(String json,Class<T> T)throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,T);
    }

    /**
     * json编码
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String encode(Object o)throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }
}
