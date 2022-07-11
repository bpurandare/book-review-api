package com.tcs.bookreview.security;

import com.tcs.bookreview.dao.UserDao;
import com.tcs.bookreview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = userDao.findUserByUsername(username);

        if(users.isEmpty()){
            return null;
        }

        User user = users.get(0);

        return new UserDetailImpl(user);
    }
}
