package com.Iven.dao;

import com.Iven.jdbcutil.JdbcUtil;
import com.Iven.sqlName.SqlName;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class provide implements DaoProvide{
    //JDBC封装库
    private JdbcUtil util = new JdbcUtil();

    @Override
    //用户注册
    public int add(SqlName sqlName) {
        String sql = "insert into users(userName,pword,sex,email)" +
                " values(?,?,?,?)";
        //预编译
        PreparedStatement ps = util.createStatement(sql);
        int result = 0;
        try {
            //获取参数
            ps.setString(1, sqlName.getUserName());
            ps.setString(2, sqlName.getPword());
            ps.setString(3, sqlName.getSex());
            ps.setString(4, sqlName.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }
}
