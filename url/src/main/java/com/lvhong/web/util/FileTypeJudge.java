package com.lvhong.web.util;

import java.io.IOException;
import java.io.InputStream;

public class FileTypeJudge {

	public final static String XLS_TYPE = "d0cf11e0a1b11ae10000";
	public final static String XLSX_TYPE = "504b0304140006000800";
	public final static String DOC = "d0cf11e0a1b11ae10000";
	public final static String DOCX = "504b0304140006000800";
	public final static String PDF = "255044462d312e350d0a";
	public final static String RAR = "526172211a0700cf9073";
	public final static String XML = "3c3f786d6c2076657273";
	public final static String JPG = "ffd8ffe000104a464946";
	public final static String PNG = "89504e470d0a1a0a0000";
	public final static String GIF = "47494638396126026f01";
	public final static String TIF = "49492a00227105008037";
	public final static String BMP_16 = "424d228c010000000000";
	public final static String BMP_24 = "424d8240090000000000";
	public final static String BMP_256 = "424d8e1b030000000000";
	public final static String DWG = "41433130313500000000";
	public final static String PSD = "38425053000100000000";
	public final static String RTF = "7b5c727466315c616e73";
	public final static String HTML = "3c21444f435459504520";
	public final static String EML = "46726f6d3a203d3f6762";
	public final static String MDB = "5374616E64617264204A";
	public final static String PS = "252150532D41646F6265";
	public final static String WAV = "52494646e27807005741";
	public final static String AVI = "52494646d07d60074156";
	public final static String RM = "2E524D46";
	public final static String MPG = "000001ba210001000180";
	public final static String MOV = "6D6F6F76";
	public final static String ASF = "3026B2758E66CF11";
	public final static String MID = "4d546864000000060001";
	public final static String GZ = "1f8b0800000000000000";
	public final static String RMVB = "2e524d46000000120001";
	public final static String FLV = "464c5601050000000900";
	public final static String MP4 = "00000020667479706d70";
	public final static String MP3 = "49443303000000002176";
	public final static String WMV = "3026b2758e66cf11a6d9";
	public final static String ZIP = "504b0304140000000800";
	public final static String INI = "235468697320636f6e66";
	public final static String JAR = "504b03040a0000000000";
	public final static String EXE = "4d5a9000030000000400";
	public final static String JSP = "3c25402070616765206c";
	public final static String MF = "4d616e69666573742d56";
	public final static String SQL = "494e5345525420494e54";
	public final static String JAVA = "7061636b616765207765";
	public final static String BAT = "406563686f206f66660d";
	public final static String PROPERTIES = "6c6f67346a2e726f6f74";
	public final static String CLASS = "cafebabe0000002e0041";
	public final static String CHM = "49545346030000006000";
	public final static String MXP = "04000000010000001300";

	public static String bytesToHexString(InputStream ins) throws IOException {
		byte[] src = new byte[10];
		ins.read(src, 0, src.length);
		ins.reset();
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
