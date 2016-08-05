package javasrc.component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectUtils {
	/**
	 * 把对象的属性值存入字符数组。
	 * @param object 对象。
	 * @param ignorenum 忽略最后exceptnum个属性。
	 * @return 字符数组。*/
	public static List<String> tolist(Object object,int ignorenum){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> list=new ArrayList<>();
		Field[]  fields=object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length-ignorenum; i++) {
			fields[i].setAccessible(true);
			try {
				if (fields[i].get(object)==null) {
					list.add("");
				} else {
					if (fields[i].getType()==Date.class) {
						String dateString=simpleDateFormat.format(fields[i].get(object));
						if (dateString.indexOf(" 00:00:00")!=-1) {
							dateString=dateString.replace(" 00:00:00", "");
						}
						list.add(dateString);
					} else {
						list.add(ShortDecimalFormat(fields[i].get(object).toString()));
					}
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 获取第一个异常。*/
	public static Throwable findcause(Throwable throwable) {
		if (throwable.getCause()!=null) {
			return findcause(throwable.getCause());
		}else {
			return throwable;
		}
	}
	
	public static String ShortDecimalFormat(String string){
		StringBuilder re=new StringBuilder();
		char c;
		try {
			BigDecimal bigDecimal=new BigDecimal(string);
			re.append(bigDecimal.setScale(2, RoundingMode.HALF_UP).toPlainString());
			for (int i = re.length()-1; i >0; i--) {
				c=re.charAt(i);
				if (c=='0') {
					re.deleteCharAt(i);
				} else if (c=='.') {
					re.deleteCharAt(i);
					break;
				} else {
					break;
				}
			}
		} catch (NumberFormatException e) {
			re.append(string);
		}
		return re.toString();
	}
}
