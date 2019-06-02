package com.example.demo.ServiceImp;

import com.example.demo.Dao.UserCollectionMapper;
import com.example.demo.Entity.UserCollection;
import com.example.demo.Service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by forget on 2018/11/26.
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    UserCollectionMapper cmapper;

    @Override
    public List<UserCollection> getUserAllCollectionList(Long userid) {
        UserCollection C = new UserCollection();
        C.setUserid(userid);
        return cmapper.getCollectionList(C);
    }

    @Override
    public List<UserCollection> getUserSingleCollection(Long userid, Long sheetid) {
        UserCollection C = new UserCollection();
        C.setUserid(userid);
        C.setSheetid(sheetid);
        return cmapper.getCollectionList(C);
    }

    @Override
    public int InsertCollectionRecord(UserCollection userCollection) {
        return cmapper.InsertNoexist(userCollection);
    }

    @Override
    public int DeleteCollectionRecord(Long userid, String TableName, Long musicid) {
        UserCollection C = new UserCollection();
        C.setUserid(userid);
        return cmapper.DeleteCollection(C);
    }

    @Override
    public int DeleteCollectionTable(Long userid, Long sheetid) {
        UserCollection C = new UserCollection();
        C.setUserid(userid);
        C.setSheetid(sheetid);
        return cmapper.DeleteCollection(C);
    }

    @Override
    public int UpdateCollectionRecord(UserCollection userCollection) {
        return cmapper.UpdateCollection(userCollection);
    }
}
