package com.wang.dao.jdbcimpl;

import com.wang.dao.dao.AdminDAO;
import com.wang.doo.AdminDO;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * UserDAO -- jdbc实现类
 *
 * @auther ten
 */
class AdminDAOImpl extends AbstractDAO implements AdminDAO {
    AdminDAOImpl() {
    }

    static AdminDAO newAdminDAO() {
        return new AdminDAOImpl();
    }

    @Override
    public void insertAdmin(AdminDO adminDO) throws SQLException {
        String sql = "insert into admin(admin_name,admin_password" +
                ",admin_levle,admin_gmt_create,admin_gmt_modified) " +
                "values(?,?,?,?,?)";
        Object[] args = {adminDO.getAdminName(), adminDO.getAdminPassword(), adminDO.getAdminLevel(),
                adminDO.getAdminGmtCreate(), adminDO.getAdminGmtModified()};
        insert(sql, args);

    }

    @Override
    public AdminDO queryAdmin(String adminName) throws SQLException {
        String sql = "select (admin_id,admin_name,admin_password,admin_level) from admin where admin_name = " + adminName;
        ResultSet resultSet = query(sql);
        AdminDO adminDO = new AdminDO();
        adminDO.setAdminId(Integer.valueOf(resultSet.getString("admin_id")));
        adminDO.setAdminName(resultSet.getString("admin_name"));
        adminDO.setAdminPassword(resultSet.getString("admin_password"));
        adminDO.setAdminLevel(resultSet.getString("admin_level"));
        return adminDO;
    }

    @Override
    public boolean isAdminExisted(String adminName) throws SQLException {
        String sql = "select count(admin_id) from admin where admin_name = " + adminName;

        ResultSet resultSet = query(sql);
        return resultSet.getInt(1) > 0;
    }
}
