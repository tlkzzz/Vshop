package com.easemob.server.example.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * PropertiesUtils
 * 
 * @author Lynch 2014-09-15
 *
 */
public class PropertiesUtils {

	private static Properties props = new Properties();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	public static Properties getProperties() {

		try {
			InputStream inputStream = null;
			Resource resource = resourceLoader.getResource("conf/RestAPIConfig.properties");
			inputStream = resource.getInputStream();
			props.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return props;
	}

}
