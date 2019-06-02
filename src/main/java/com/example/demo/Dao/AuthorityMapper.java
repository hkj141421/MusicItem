package com.example.demo.Dao;

import com.example.demo.Entity.Authority;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityMapper {
    int deleteByPrimaryKey(Long roleid);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long roleid);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}