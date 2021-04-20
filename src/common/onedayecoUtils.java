package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class onedayecoUtils {
	
	public static String getSha512(String memberPw) {
		String encryptedPassword = null;
		
		//1.암호화 
		MessageDigest md = null;
		try {
				md=MessageDigest.getInstance("SHA-512");
				//암호화 알고리즘 
		}catch (NoSuchAlgorithmException e ) {
			e.printStackTrace();
		}
		byte[] bytes=null;
		try {
			bytes = memberPw.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		md.update(bytes);
		byte[] encryptedBytes = md.digest();
		System.out.println("암호화 처리후:" +  new String(encryptedBytes));
		//2. 문자 인코딩 처리
		encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("인코딩 처리후 : " + encryptedPassword);
		
		return  encryptedPassword;
	}

}
