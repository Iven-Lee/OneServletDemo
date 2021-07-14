package com.Iven.Controller;
//界面层

import com.Iven.dao.DaoProvide;
import com.Iven.dao.provide;
import com.Iven.sqlName.SqlName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class User extends HttpServlet {
     //采用get的请求方式
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        //请求头参数：http://localhost:8080/myWeb/user/add?userName=？&pword=？&sex=？&email=？
        String userName,pword,sex,email;

        DaoProvide dao = new provide();
        SqlName sqlName = null;
        int result = 0;
        PrintWriter out = null;

        //1.【调用请求对象】读取【请求头】参数信息，得到用户输入的参数信息
        ///user/add?userName=  &pword=  &sex=  &email=
        userName = request.getParameter("userName");
        pword = request.getParameter("pword");
        sex = request.getParameter("sex");
        email = request.getParameter("email");


        //2.【调用UserDao】将用户信息填充到INSERT命令并借助JDBC规范发送到数据库服务器
        sqlName = new SqlName(null, userName, pword, sex, email);
        result =dao.add(sqlName);

        //3.【调用响应对象】将【处理结果】以二进制形式写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();//写入响应体
        if(result ==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
    }
    //Tocmat负责销毁【请求对象】和【响应对象】
    //Tomcat负责将Http响应协议包推送到发起请求的浏览器上
    //浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
    //浏览器将编辑后结果在窗口中展示给用户【结束】

}
