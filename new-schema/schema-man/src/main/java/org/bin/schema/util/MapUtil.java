package org.bin.schema.util;

import java.util.Map;

/**
 * 
 * <p>Title:MapUtil</p>
 * <p>Description:Map类相关</p>
 * @author binH
 * @date 2017年3月27日 下午2:34:09
 */
public class MapUtil {

	/**
	 * 
	 * 生成Map
	 * @param clas
	 * @param objects
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public static Map build(Class clas,Object... objects) throws InstantiationException, IllegalAccessException{
		if(objects != null && objects.length > 0){
			Map<String, Object> map= null;
			Object obj = clas.newInstance();
			if(obj instanceof Map){
				map = (Map)obj;
				for (int i = 0; i < objects.length;) {
					String key = String.valueOf(objects[i]);
					Object value = objects[i+1];
					map.put(key, value);
					
					i += 2;
				}
			}
			
			return map;
		}
		return null;
	}
}
