package kr.co.jaejinuproject001.myrestfulservice.dao;

import kr.co.jaejinuproject001.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> users = new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1,"Kenneth", new Date()));
        users.add(new User(2,"jaejinu", new Date()));
        users.add(new User(3,"kyoroho", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }

        if(user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }

    public User findOne(Integer id) {
        for(User user : users) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


}
