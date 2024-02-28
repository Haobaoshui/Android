package xyz.hbs.huarongdao.service;


import xyz.hbs.huarongdao.model.Page;
import xyz.hbs.huarongdao.model.User;

import java.util.List;

public interface UserService {
    /**
     * 增加用户
     * @param user
     * @return
     */
    String add(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int delete(User user);
    int deleteByUserId(String t_user_id);

    /**
     * 更新用户，但是不更新密码，更新密码使用updateUserPassword()函数
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 更新用户密码
     * @param id
     * @param strPlainPwd
     * @return
     */
    int updateUserPassword(String id, String strPlainPwd);

    List<User> getAllUsers();

    Page<User> getPage(Integer pageNo, Integer pageSize);
    Page<User> getSearchedPage(String searchText, Integer pageNo, Integer pageSize);
}
