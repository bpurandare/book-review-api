package com.tcs.bookreview.service;

import com.tcs.bookreview.dao.UserDao;
import com.tcs.bookreview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User registerUser(User user) {
        if(userDao.findUserByUsername(user.getUsername()).size() >0 ){
            return null;
        }
        int count = Math.toIntExact(userDao.count());
        user.setUserId(++count);
        user.setPassword("{noop}"+user.getPassword());
        user.setRole(ConstantsService.ROLE_USER);
        user.setEnabled("Y");
        return userDao.save(user);
    }
}
