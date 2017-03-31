package org.bin.schema.util;
/**
 * 
 * <p>Title:StringUtil</p>
 * <p>Description:字符类操作</p>
 * @author binH
 * @date 2017年3月27日 下午2:34:49
 */
public class StringUtil {
	/**
	 * 
	 * 线程安全字符相加
	 * @param objs
	 * @return
	 */
	public static String strAddSynch(Object... objs){
		if(objs != null && objs.length > 0){
			StringBuffer buffer = new StringBuffer();
			for (Object object : objs) {
				buffer.append(object);
			}
			return buffer.toString();
		}
		return null;
	}
	/**
	 * 
	 * 非线程安全字符相加
	 * @param objs
	 * @return
	 */
	public static String strAdd(Object... objs){
		if(objs != null && objs.length > 0){
			StringBuilder builder = new StringBuilder();
			for (Object object : objs) {
				builder.append(object);
			}
			return builder.toString();
		}
		return null;
	}
}
