package com.example.demo.Dao;

import com.example.demo.Entity.UserCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);


    //自定义
    int InsertNoexist(UserCollection userCollection);

    UserCollection getCollection(UserCollection userCollection);

    int AddCollection(UserCollection userCollection);

    int DeleteCollection(UserCollection userCollection);

    int UpdateCollection(UserCollection userCollection);

    List<UserCollection> getCollectionList(UserCollection userCollection);

    Long getCollectCountBysheetid(Long sheetid);

}