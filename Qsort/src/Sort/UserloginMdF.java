package Sort;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserloginMdF {

	public static void main(String[] args) {
		Md5("123");
	}

	private static void Md5(String userpassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(userpassword.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}

			System.out.println("result: " + buf.toString());// 32λ�ļ���

			System.out.println("result: " + buf.toString().substring(8, 24));// 16λ�ļ���

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
