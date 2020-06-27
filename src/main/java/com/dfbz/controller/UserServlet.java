package com.dfbz.controller;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.entity.Dept;
import com.dfbz.entity.Page;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
import com.dfbz.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();
    private DeptService deptService = new DeptService();

    //    查询全部和按条件查询
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user list123");
        //有条件查询
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("gender"));
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");

//        获取页面大小值
        String pageSize = request.getParameter("pageSize");
//        当前页面
        String pageStr = request.getParameter("page");
//        username回显
        request.setAttribute("username", username);
//        Page对象数据
        Page page = new Page();
//        设置pageCurrent
        if (!StringUtils.isEmpty(pageStr)) {
            page.setPageCurrent(Integer.parseInt(pageStr));
        }
//        设置页面大小值，默认值为5
        if (!StringUtils.isEmpty(pageSize)) {
            page.setSize(Integer.parseInt(pageSize));
        }

//        分页查询
        if ((username != null && !("".equals(username))) || (gender != null && !("-1".equals(gender)))) {
//            模糊查询回显到page的数据
            List<User> users = userService.findByName(username, Integer.parseInt(gender), page, false);
//            符合条件的实际数据
            List<User> trueUsers = userService.findByName(username, Integer.parseInt(gender), page, true);
            page.setCount(trueUsers.size());
            for (User user : users) {
                System.out.println(user.getUsername());
                String dept = deptService.findDeptByDeptId(user.getDeptId());
                user.setDeptName(dept);
            }
            //        设置pageCount
            Integer count = page.getCount();
            Integer size = page.getSize();
            Integer pgCount = count % size == 0 ? count / size : count / size + 1;
            page.setPageCount(pgCount);
            request.setAttribute("page", page);

            request.setAttribute("userList", users);
            request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
        } else {
//            无条件查询回显到page的数据
            System.out.println("无条件查询");
            List<User> users = userService.listAll(page,false);
//            符合条件的实际数据s
            List<User> trueUsers = userService.listAll(page,true);
            page.setCount(trueUsers.size());
            for (User user : users) {
                System.out.println(user.getUsername());
                String dept = deptService.findDeptByDeptId(user.getDeptId());
                System.out.println(dept);
                user.setDeptName(dept);
            }
            //        设置pageCount
            Integer pgCount = page.getCount() % page.getSize() == 0 ? page.getCount() / page.getSize() : page.getCount() / page.getSize() + 1;
            page.setPageCount(pgCount);
            request.setAttribute("page", page);

            request.setAttribute("userList", users);
            request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user add");
        String uid = request.getParameter("uid");
        //            查询所有部门
        List<Dept> depts = deptService.findAll();
        request.setAttribute("DeptNam", depts);

//      如果有uid就是进入修改页面
        if (!("".equals(uid)) && uid != null) {
            User user = userService.findByUid(Integer.parseInt(uid));
            System.out.println(user.getUsername());
            request.setAttribute("userInfo", user);
            request.getRequestDispatcher("/jsp/user/addUser.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsp/user/addUser.jsp").forward(request, response);
        }

    }

    //    添加和修改
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user addUser");

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setRealName(request.getParameter("realName"));
        String age = request.getParameter("age");
        user.setAge(Integer.parseInt(age));
        user.setPhone(request.getParameter("phone"));
        user.setGender(request.getParameter("gender"));
        user.setDescription(request.getParameter("desc"));
        user.setRegisterTime(request.getParameter("registerTime"));
        String deptId = request.getParameter("deptId");

        if (deptId != "" && deptId != null) {
            user.setDeptId(Integer.parseInt(deptId));
        }
//        如果传入uid就是修改user信息
        String uid = request.getParameter("uid");
        if (uid != null && !("".equals(uid))) {
            user.setId(Integer.parseInt(uid));
            int i = userService.updateUser(user);
            if (i > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
            response.sendRedirect("/user/list");
        } else {
            int i = userService.addUser(user);
            if (i > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
            response.sendRedirect("/user/list");
        }

    }

    //    逻辑删除
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user delete");

        String uid = request.getParameter("uid");
        int i = userService.delete(uid);
        if (i > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        response.sendRedirect("/user/list");
    }

    //    判断用户是否存在
    public void exitUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("user exitUser");
        String name = request.getParameter("name");
        System.out.println(name);
        String uid = request.getParameter("uid");
//      精准查询
        User user = userService.findByNameAccurate(name);
        System.out.println(user);
        if (user != null && !("".equals(user))) {
            if (uid != null && !("".equals(uid))) {
                System.out.println(uid);
                User user1 = userService.findByNameAndUid(name, Integer.parseInt(uid));
                System.out.println("查询是否为自己的用户名");
                if (user1 != null) {
                    System.out.println("可以修改的用户名");
                    response.getWriter().write("!isExit");
                } else {
                    System.out.println("修改的用户名已被其他用户占用");
//                    修改回原来自己的名字
                    User user2 = userService.findByUid(Integer.parseInt(uid));
                    JSONObject jo = new JSONObject();
                    jo.put("flag", "isExit");
                    jo.put("name", user2.getUsername());
                    response.getWriter().write(jo.toString());
                }
                response.getWriter().close();
            } else {
                System.out.println("新增用户名已存在");
                response.getWriter().write("isExit");
            }
        } else {
            System.out.println("新增或修改用户名不存在");
            response.getWriter().write("!isExit");
        }
        response.getWriter().close();


    }

}
