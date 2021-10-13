package com.lancoo.utils;

import java.util.Random;

public class RandomCodeUtil {

	// 获取数据字母子组合的编码
	public String getCharAndNumCode(int length) {

		StringBuffer valSb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				valSb.append((char) (choice + random.nextInt(26)));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				// 数字
				valSb.append(String.valueOf(random.nextInt(10)));
			}
		}
		return valSb.toString();
	}
}
