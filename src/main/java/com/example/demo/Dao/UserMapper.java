package com.example.demo.Dao;

import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    User selectByAccount(String UserAccount);

    int InsertNoexist(User user);

    User getUserById(Long userid);

    User getUserByName(String UserName);

    User getUserByAccount(String UserAccount);

    int AddUser(User user);

    int DeleteUser(Long userid);

    int UpdateUser(User user);

    List<User> getUserList(User user);

    List<String> getUserAuthorityByName(String username);

    Long IsExistEmail(String email);

    List<User> searchUser(@Param("key") String key, @Param("pagenum") Integer pagenum, @Param("size") Integer size);

    Long CountSearchUser(@Param("key") String key);

    List<User> getRecomendUser(@Param("userid") Long userid, @Param("size") Long size);

    HashMap<String, Object> getUserIsFans(@Param("userid") Long userid, @Param("likeuserid") Long size);

    List<User> getAllUser(@Param("pagenum") Long pagenum, @Param("size") Long size);

    Long getAllUserCount();

    Long Batchdeletion(@Param("array") List<Long> array);
}