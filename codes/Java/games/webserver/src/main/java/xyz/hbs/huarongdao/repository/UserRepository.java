package xyz.hbs.huarongdao.repository;


import xyz.hbs.huarongdao.model.Page;
import xyz.hbs.huarongdao.model.User;

import java.util.List;

public interface UserRepository {



    /**
     * 增加用户
     * @param user
     * @return
     */
    String add(User user);



    /**
     * 删除用户
     * @param t_user_id
     * @return
     */
    int deleteById(String t_user_id);
    int delete(User user);

    /**
     * 更新用户密码
     *
     * @param id
     * @param strEncodedPwd
     * @return
     */
    int updateUserPassword(String id, String strEncodedPwd);
    int update(User user);



    /**
     * 根据用户名与密码判断用户是否存在
     * @param userName
     * @param userPwd
     * @return
     */
    boolean isUserExists(String userName, String userPwd);


    /**
     * 得到用户总个数
     * @return
     */
    int getCount();

    /**
     * 得到相似名用户个数
     * @param text
     * @return
     */
    int getCountLike(String text);



    /**
     * 根据用户名与密码得到用户
     * @param userName
     * @param userPwd
     * @return
     */
    User getUser(String userName, String userPwd);



    /**
     * 根据用户名得到用户
     * @param userName
     * @return
     */
    User getUserByName(String userName);


    /**
     * 根据用户ID得到用户
     * @param t_user_id
     * @return
     */
    User getUserByID(String t_user_id);


    List<User> getAllUsers();
    Page<User> getPage(int pageNo, int pageSize);
    Page<User> getSearchedPage(String searchText, Integer pageNo, Integer pageSize);


}
