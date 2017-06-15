package com.qq.connect.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class QQConnectConfig {
	private static Properties props = new Properties();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}

	static {
		try {
			InputStream is = null;
			Resource resource = resourceLoader.getResource("conf/qqconnectconfig.properties");
			is = resource.getInputStream();
			props.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
