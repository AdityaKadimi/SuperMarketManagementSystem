package com.uncc.ssdi.supermarket_management_system.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	public static String asJSONString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
}
