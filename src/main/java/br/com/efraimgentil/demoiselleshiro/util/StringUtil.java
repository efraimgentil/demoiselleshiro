package br.com.efraimgentil.demoiselleshiro.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StringUtil implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 6780339293712451027L;

	public static String md5(String string) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			BigInteger bigInteger = new BigInteger(1, digest.digest(string
					.getBytes()));
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "1234560654123";
	}

}