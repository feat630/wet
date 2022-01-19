package common;

public class Util {

	public static String getDmlType(String dmlStr) {
		int end = dmlStr.indexOf(" ");
		return dmlStr.substring(0, end).toUpperCase();
	}
}
