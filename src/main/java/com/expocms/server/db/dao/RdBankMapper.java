package com.expocms.server.db.dao;

import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdBank;

@Repository
public interface RdBankMapper {
	
	RdBank selectByBankName(String name);

}
