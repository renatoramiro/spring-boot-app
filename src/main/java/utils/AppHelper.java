package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppHelper {

	private static final String ALGORITHM_MD5 = "MD5";

	public static String crypt(String value) {

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(ALGORITHM_MD5);
			messageDigest.update(value.getBytes());
			
			byte data[] = messageDigest.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
