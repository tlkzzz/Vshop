package com.Vshop.core.push.android;

import com.Vshop.core.push.AndroidNotification;


public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast() {
		try {
			this.setPredefinedKeyValue("type", "broadcast");	
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
