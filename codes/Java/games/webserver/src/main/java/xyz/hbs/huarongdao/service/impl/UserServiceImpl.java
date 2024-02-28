package xyz.hbs.huarongdao.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hbs.huarongdao.model.Page;
import xyz.hbs.huarongdao.model.User;
import xyz.hbs.huarongdao.repository.UserRepository;
import xyz.hbs.huarongdao.service.UserService;
import xyz.hbs.huarongdao.utility.EncoderHandler;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String add(User user) {
        if(user==null
                || user.getUserName()==null
                || user.getUserPassword()==null
                || user.getUserName().length()==0
                || user.getUserPassword().length()==0)
            return null;

        //数据库不能存储明文，这里保存密码的散列值
        String userPassword=user.getUserPassword();
        user.setUserPassword(EncoderHandler.encode(userPassword));

        return userRepository.add(user);
    }

    @Override
    public int delete(User user) {
        if(user==null)return 0;
        return userRepository.deleteById(user.getId());
    }

    @Override
    public int update(User user) {
        if(user==null)return 0;
        return userRepository.update(user);
    }

    @Override
    public int updateUserPassword(String id, String strPlainPwd) {
        if(id==null || strPlainPwd==null || strPlainPwd.length()==0)return 0;
        strPlainPwd= EncoderHandler.encode(strPlainPwd);
        return userRepository.updateUserPassword(id,strPlainPwd);
    }

    @Override
    public List<User> getAllUsers() {
         return userRepository.getAllUsers();
    }

    @Override
    public int deleteByUserId(String t_user_id) {
        return userRepository.deleteById(t_user_id);
    }

    @Override
    public Page<User> getPage(Integer pageNo, Integer pageSize) {
        return userRepository.getPage(pageNo,pageSize);
    }

    @Override
    public Page<User> getSearchedPage(String searchText, Integer pageNo, Integer pageSize) {
        return userRepository.getSearchedPage(searchText,pageNo,pageSize);
    }
}
