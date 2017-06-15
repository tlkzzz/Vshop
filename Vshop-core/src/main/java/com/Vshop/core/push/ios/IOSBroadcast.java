package com.Vshop.core.push.ios;

import com.Vshop.core.push.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast() {
		try {
			this.setPredefinedKeyValue("type", "broadcast");	
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
