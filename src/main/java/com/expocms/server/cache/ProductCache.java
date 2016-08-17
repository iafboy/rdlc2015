package com.expocms.server.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expocms.server.db.dao.RdOrderMapper;

final class ProductCache {
	
	private String productId_ = null;

	private Map<String, Object> userMap_ = null;
	
	public ProductCache(String productId) {
		this.productId_ = productId;
	}

	public void dirty() {}
	
	public int getUserNum(final RdOrderMapper rdOrderMapper, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		assert(this.productId_ != null && this.productId_.equals("") == false);
		
		if(this.userMap_ == null) {
			this.userMap_ = new HashMap<String, Object>();
			
			List<String> userList = rdOrderMapper.getUserListOfProduct(this.productId_);
			if(userList != null && userList.size() != 0) {
				for(int i = 0; i < userList.size(); i ++) {
					String id = userList.get(i);
					this.userMap_.put(id, null);
				}
			}
		}

		return this.userMap_.size();
	}
	
	public int incUserNum(final RdOrderMapper rdOrderMapper, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		assert(this.productId_ != null && this.productId_.equals("") == false);
		
		getUserNum(rdOrderMapper, userId);
		
		if(this.userMap_.containsKey(userId) == false)
			this.userMap_.put(userId, null);
		return this.userMap_.size();
	}
	
	public int decUserNum(final RdOrderMapper rdOrderMapper, final String userId) {
		if(rdOrderMapper == null)
			return 0;
		if(userId == null || userId.equals(""))
			return 0;
		
		assert(this.productId_ != null && this.productId_.equals("") == false);
		
		getUserNum(rdOrderMapper, userId);
		
		if(this.userMap_.containsKey(userId))
			this.userMap_.remove(userId);
		return this.userMap_.size();
	}

}
