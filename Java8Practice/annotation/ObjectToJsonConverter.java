package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {
	
	public String convertToJson(Object object) throws Exception {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

	private void checkIfSerializable(Object object) throws Exception {
		if(Objects.isNull(object))
			throw new Exception("The object to serialize is null");
		Class<?> clazz=object.getClass();
		if(!clazz.isAnnotationPresent(JsonSerializeble.class)) {
			throw new Exception(clazz.getSimpleName());
		}
	}
	
	private void initializeObject(Object object) throws Exception {
		Class<?> clazz=object.getClass();
		for(Method method:clazz.getDeclaredMethods()) {
			if(method.isAnnotationPresent(Init.class)) {
				method.setAccessible(true);
				method.invoke(object);
			}
		}
	}
	
	private String getJsonString(Object object) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz=object.getClass();
		Map<String,String> mp=new HashMap<String, String>();
		for(Field field:clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(JsonElement.class)) {
				field.setAccessible(true);
				mp.put(field.getName(), (String) field.get(object));
			}
		}
		
		String JSONString=mp.entrySet().
				stream().map(entry->"\""+entry.getKey()+"\":\""+entry.getValue()+"\":\"").collect(Collectors.joining(","));
		return "{"+JSONString+"}";
	}
}
