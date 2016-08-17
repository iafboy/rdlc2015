package com.expocms.server.util;

import java.util.HashMap;
import java.util.Map;

public final class SyncObjects {
	
	private static SyncObjects inst_ = null;

	private Map<String, Object> userObjects_ = null;
	private Map<String, Object> productObjects_ = null;
	
	private SyncObjects() {
		this.userObjects_ = new HashMap<String, Object>();
		this.productObjects_ = new HashMap<String, Object>();
	}
	
	public static SyncObjects instance() {
		if(inst_ == null)
			inst_ = new SyncObjects();
		return inst_;
	}

	public Object getUserSyncObject(final String key) {
		synchronized(this.userObjects_) {
			Object obj = this.userObjects_.get(key);
			if(obj == null) {
				obj = new Object();
				this.userObjects_.put(key, obj);
			}
			return obj;
		}
	}
	
	public Object getProductSyncObject(final String key) {
		synchronized(this.productObjects_) {
			Object obj = this.productObjects_.get(key);
			if(obj == null) {
				obj = new Object();
				this.productObjects_.put(key, obj);
			}
			return obj;
		}
	}
	
}
