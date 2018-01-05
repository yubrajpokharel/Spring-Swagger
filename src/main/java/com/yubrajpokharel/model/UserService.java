package com.yubrajpokharel.model;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yubrajpokharel on 1/4/18.
 */

@Component
public class UserService {

    private Map<Integer, User>  map;

    public UserService(){
        map = new HashMap<>();
        setAllUser();
    }

    public void setAllUser(){

        User user = new User(1, "christain", "bale", 40);
        User user2 = new User(2, "christain", "bale", 40);
        User user3 = new User(3, "christain", "bale", 40);

        this.map.put(1, user);
        this.map.put(2, user2);
        this.map.put(3, user3);
    }

    public Map<Integer, User> getAllUser(){
        return this.map;
    }


    public User getUserById(int id){
        return this.map.get(id);
    }

    public void add(User user){
        this.map.put(map.size() + 1, user);
    }

}
