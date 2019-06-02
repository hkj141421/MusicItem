package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.User;
import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Created by forget on 2019/4/22.
 */
@ServerEndpoint("/test/{data}")
public class websocketTest {

    public static Long onlinecount = 0L;

    public static ConcurrentHashMap<Session, websocketTest> clientsMap = new ConcurrentHashMap<>();//存储客户端的Map集合，用于给指定用户发送消息

    public static CopyOnWriteArraySet<websocketTest> clentsSet = new CopyOnWriteArraySet<>();//存储客户端的Set集合，用于群发消息

    public static Logger logger = Logger.getLogger(websocketTest.class);//记录日志

    private Session session;

    public websocketTest() {
        System.out.println("调用构造方法");
    }

    public synchronized void addonlinecount() {
        onlinecount++;
    }

    public synchronized void subonlinecount() {
        onlinecount--;
    }

    public synchronized Long getOnlinecount() {
        return onlinecount;
    }

    @OnOpen
    public void open(@PathParam("data") String data, Session session) {

        System.out.println("客户端" + session.getId() + "连接到服务器。服务器当前在线人数为" + onlinecount);
        this.session = session;
        clentsSet.add(this);
        clientsMap.put(session, this);
        addonlinecount();

    }

    @OnMessage
    public void testWebSocket(String message, Session session) throws IOException, EncodeException {
        //添加粉丝
        logger.info("收到客户端" + session.getId() + "的消息");

        AjaxResponseBody resp = new AjaxResponseBody();

        User user = new User();
        user.setEmail("670378784@qq.com");
        //    user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        user.setUseraccount(session.getId());
        user.setUserheadimg("E;/UPLOAD");
        user.setUserid(1L);
        user.setUsername("hello");
        user.setUserpassword("admin");

        resp.setData(user);
        resp.setMsg("success");
        resp.setStatus("200");
        //发送给所有客户端
        for (websocketTest client : clentsSet) {
            client.session.getBasicRemote().sendObject(resp);
        }

    }

    @OnClose
    public void close(Session session) {
        this.session = session;
        clentsSet.remove(this);
        clientsMap.remove(session);
        subonlinecount();
        System.out.println("客户端" + session.getId() + "断开服务器连接。服务器当前在线人数为" + onlinecount);
    }

}
