package com.wang.daomanager;

import com.wang.bean.UserMsg;
import com.wang.dao.DAOFactory;
import com.wang.dao.UserMsgDAO;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * UserMsgDAOModel: usermsg表逻辑操作
 * <p>
 * 访问范围: 全局
 * 调用者: Service
 * 异常检测: 确保DAO参数无差错
 * <p>
 * 1. 查询usermsg queryUserMsg(user_id)
 * 2. 插入或更新usermsg insertUserMsg(UserMsg)
 * 3. 插入或更新iconpath updateUserIcon(iconpath)
 *
 * @auther ten
 */
public class UserMsgDAOModel implements DAOModel {
    private final int userid;
    private final UserMsgDAO dao;
    private final Logger logger;

    private UserMsgDAOModel(int userid) {
        this.userid = userid;
        this.dao = (UserMsgDAO) DAOFactory.getDAOByName("UserMsgDAO");
        this.logger = Logger.getLogger("UserMsgDAOModel@" + userid);
    }

    static DAOModel getUserMsgModel(int userid) {
        return new UserMsgDAOModel(userid);
    }

    /**
     * 查询usermsg
     *
     * @return userMsg 数据库返回UserMsg对象
     * @throws SQLException         queryUserMsg()异常
     * @throws NullPointerException 数据库查询为空
     */
    public UserMsg queryUserMsg() throws SQLException {
        logger.info("queryUserMsg()");

        return dao.queryUserMsg(userid);
    }

    /**
     * 插入usermsg except iconpath
     *
     * @throws SQLException             insertUserMsg()异常
     * @throws IllegalArgumentException usermsg属性为空
     */
    public void insertUserMsg(UserMsg userMsg) throws SQLException {
        logger.info("inserUserMsg()");

        final boolean isNull = userMsg.getNickname() == null || userMsg.getAge() == null || userMsg.getSex() == null || userMsg.getBirthday() == null || userMsg.getEmail() == null;

        if (isNull) {
            throw new IllegalArgumentException("usermsg argument is null");
        }

        dao.insertUserMsg(userMsg);
    }

    /**
     * 更新iconpath
     *
     * @throws SQLException             updateUserIcon()异常
     * @throws IllegalArgumentException iconpath属性为空
     */
    public void updateUserIcon(String iconpath) throws SQLException {
        logger.info("inserUserIcon()");

        if (iconpath == null) {
            throw new IllegalArgumentException("usermsg argument is null");
        }

        dao.updateUserIcon(userid, iconpath);
    }
}
