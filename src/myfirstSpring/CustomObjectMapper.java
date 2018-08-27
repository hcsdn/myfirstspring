package myfirstSpring;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
public class CustomObjectMapper extends ObjectMapper {    
    
	private ObjectMapper objectMapper = null;
    public CustomObjectMapper() {    
    	
    	if (objectMapper== null){
    		objectMapper= new ObjectMapper();
    		//当找不到对应的序列化器时 忽略此字段
    		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    		//使Jackson JSON支持Unicode编码非ASCII字符
    		SimpleModule module = new SimpleModule();
    		module.addSerializer(String.class, new StringUnicodeSerializer());
    		objectMapper.registerModule(module);
    		//设置null值不参与序列化(字段不被显示)
    		objectMapper.setSerializationInclusion(Include.NON_NULL);
    		//支持结束
    	}

    	
    }    
} 
