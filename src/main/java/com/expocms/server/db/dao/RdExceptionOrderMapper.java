package com.expocms.server.db.dao;

import org.springframework.stereotype.Repository;

import com.expocms.server.db.model.RdOrder;

@Repository
public interface RdExceptionOrderMapper {

    int insert(RdOrder record);

}