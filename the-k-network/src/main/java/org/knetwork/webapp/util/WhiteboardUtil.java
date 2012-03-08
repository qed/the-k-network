package org.knetwork.webapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class WhiteboardUtil {

	private static final String URL_AUTH_ROOT = "https://knetwork.tutortrove.com/api_v1/SSO/whiteboard";
	private static final String PRIVATE_KEY = "9b578551e3509fb425fab9f6c501af87";
	private static final String CONSUMER_KEY = "152";

	public static void main(String[] args) {
		System.out.println(WhiteboardUtil.generateWhiteboardUrl("someSessionId", "Test Whiteboard", "bob"));
	}
	
	public static String generateWhiteboardUrl(String learningSessionId,
			String whiteboardTitle, String userName) {
		
		String url = "";
		
		String whiteboard_hash = learningSessionId;
		String whiteboard_title = whiteboardTitle;
		String user_type = "tutor";
		String user_id = userName;
		String user_name = userName;

		String oauth_signature = "";
		String oauth_signature_method = "HMAC-SHA1";
		String oauth_timestamp = Long.toString(System.nanoTime());
		String oauth_version = "1.0";
		String oauth_nonce = a64BitRandomString();
		String oauth_consumer_key = "152";

	    try 
	    {

	        String[][] data = { 

	                { "oauth_consumer_key", oauth_consumer_key },
	                { "oauth_nonce", oauth_nonce },
	                { "oauth_signature_method", oauth_signature_method },
	                { "oauth_timestamp", oauth_timestamp },
	                { "oauth_version", oauth_version },
	        		{ "user_id", user_id },
	                { "user_name", user_name },
	        		{ "user_type", user_type },
	        		{ "whiteboard_hash", whiteboard_hash },
	        		{ "whiteboard_title", whiteboard_title }
	        };

	        /**
	         * Generation of the signature base string
	         */
	        String signature_base_string = "GET&"
	                + URLEncoder.encode(URL_AUTH_ROOT, "UTF-8") + "&";
	        for (int i = 0; i < data.length; i++) 
	        {
	                signature_base_string += data[i][0] + "%3D"
	                        + StringUtils.replace(data[i][1] + "%26", " ","%2520");
	        }
	        
	        signature_base_string = signature_base_string.substring(0,
	                signature_base_string.length() - 3);
	        
	        System.out.println(signature_base_string);
	        
	        oauth_signature = computeHmac(signature_base_string, PRIVATE_KEY);
	        
	        url = URL_AUTH_ROOT + "?";
	        for (int i = 0; i < data.length; i++) {
	        	url += data[i][0] + "=" + StringUtils.replace(data[i][1], " ","%20") + "&";
	        }
	        url += "oauth_signature=" + oauth_signature;
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
		
		return url;
	}

	private static String computeHmac(String baseString, String key)
			throws NoSuchAlgorithmException, InvalidKeyException,
			IllegalStateException, UnsupportedEncodingException {
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(key.getBytes(),
				mac.getAlgorithm());
		mac.init(secret);
		byte[] digest = mac.doFinal(baseString.getBytes());
		String s = Base64.encodeBase64String(digest);
		return s;
	}

	private static String a64BitRandomString() {
		StringBuffer sb = new StringBuffer();
		Random generator = new Random();
		for (int i = 0; i < 32; i++) {
			Integer r = generator.nextInt();
			if (r < 0) {
				r = r * -1;
			}
			r = r % 16;
			sb.append(Integer.toHexString(r));
		}
		return sb.toString();
	}

}
