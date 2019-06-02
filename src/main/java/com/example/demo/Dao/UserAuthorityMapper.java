package com.example.demo.Dao;

import com.example.demo.Entity.UserAuthorityKey;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityMapper {
    int deleteByPrimaryKey(UserAuthorityKey key);

    int insert(UserAuthorityKey record);

    int insertSelective(UserAuthorityKey record);

    int AddSuperAuthority(Long userid);

    int AddAdminAuthority(Long userid);

    int AddUserAuthority(Long userid);
}