package com.baicai.corewith.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String md5(String strs) {
		byte[] source = strs.getBytes();
		char str[] = new char[16 * 2];
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return new String(str);

	}

	public static String SHA1(String strs) {
		byte[] _bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(strs.getBytes());
			_bytes = md.digest();
		} catch (NoSuchAlgorithmException ex) {

		}
		return new BigInteger(1, _bytes).toString(16);

	}

	public static String md5file(File file) throws Exception {
		byte[] _bytes = null;
		InputStream is = new FileInputStream(file);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = is.read(buffer)) > 0) {
			digest.update(buffer, 0, read);
		}
		_bytes = digest.digest();
		if (is != null) {
			is.close();
			is = null;
		}
		return new BigInteger(1, _bytes).toString(16);
	}

	public static String getCRC32(String str) {
		java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
		crc32.update(str.getBytes());
		String retcrc32 = Long.toHexString(crc32.getValue());
		return retcrc32;
	}
}
