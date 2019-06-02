package com.example.demo.Service;

import com.example.demo.Entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
public interface UserService {
    List<User> getUserList(User user);

    User getUserById(Long userid);

    User getUserByName(String username);

    User getUserByAccount(String account);

    Integer InsertUser(User user);

    Integer UpdateUser(User user);

    Integer DeleteUser(Long userid);

    List<String> getUserAuthority(String username);

    boolean IsExistEmail(String email);

    List<User> searchPage(String key, Integer pagenum, Integer size);

    Long CountSearchPage(String key);

    List<User> getRecommedUser(Long userid, Long size);

    HashMap<String, Object> getUserIsFans(Long userid, Long likeuserid);

    List<User> getUserPage(Long pagenum, Long size);

    Long getUserCount();

    Long Batchdeletion(List<Long> array);

    int updateAllColum(User user);
}
