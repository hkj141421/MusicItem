package com.example.demo.ServiceImp;

import com.example.demo.Dao.UserAuthorityMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper umapper;
    @Autowired
    UserAuthorityMapper userAuthorityMapper;

    @Override
    public List<User> getUserList(User user) {
        return umapper.getUserList(user);
    }

    @Override
    public User getUserById(Long userid) {
        return umapper.getUserById(userid);
    }

    @Override
    public User getUserByName(String username) {
        return umapper.getUserByName(username);
    }

    @Override
    public User getUserByAccount(String account) {
        return umapper.getUserByAccount(account);
    }

    @Override
    public Integer InsertUser(User user) {
        int num = umapper.AddUser(user);
        userAuthorityMapper.AddUserAuthority(user.getUserid());
        return num;
    }

    @Override
    public Integer UpdateUser(User user) {
        return umapper.UpdateUser(user);
    }

    @Override
    public Integer DeleteUser(Long userid) {
        return umapper.DeleteUser(userid);
    }

    @Override
    public List<String> getUserAuthority(String username) {
        return umapper.getUserAuthorityByName(username);
    }

    @Override
    public boolean IsExistEmail(String email) {
        Long num = umapper.IsExistEmail(email);
        if (num != null && num == 0) return false;
        else return true;

    }

    @Override
    public List<User> searchPage(String key, Integer pagenum, Integer size) {
        return umapper.searchUser("%" + key + "%", (pagenum - 1) * size, size);
    }

    @Override
    public Long CountSearchPage(String key) {
        return umapper.CountSearchUser("%" + key + "%");
    }

    @Override
    public List<User> getRecommedUser(Long userid, Long size) {
        return umapper.getRecomendUser(userid, size);
    }

    @Override
    public HashMap<String, Object> getUserIsFans(Long userid, Long likeuserid) {
        return umapper.getUserIsFans(userid, likeuserid);
    }

    @Override
    public List<User> getUserPage(Long pagenum, Long size) {
        return umapper.getAllUser((pagenum - 1) * size, size);
    }

    @Override
    public Long getUserCount() {
        return umapper.getAllUserCount();
    }

    @Override
    public Long Batchdeletion(List<Long> array) {
        if (array.size() == 0) return 0L;
        return umapper.Batchdeletion(array);
    }

    @Override
    public int updateAllColum(User user) {
        return umapper.updateByPrimaryKeySelective(user);
    }
}
