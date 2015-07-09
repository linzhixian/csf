package com.lzx.framework.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterUtil {
		
	public static boolean IsCharacterValid(String userName,String filePath)  throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(filePath)), "GBK"));
		
		String strText = in.readLine();
		
		for (int i = 0; i < userName.length(); i++) {
			int charValue = userName.charAt(i);
			if(!(strText.charAt(charValue) == '1')){
				return false;
			}			
		}
		return true;
	}
}
