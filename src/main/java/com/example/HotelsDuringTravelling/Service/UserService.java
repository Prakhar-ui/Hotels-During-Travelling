package com.example.HotelsDuringTravelling.Service;

import com.example.HotelsDuringTravelling.Model.Role;
import com.example.HotelsDuringTravelling.Repository.RoleRepository;
import com.example.HotelsDuringTravelling.Repository.UserRepository;
import com.example.HotelsDuringTravelling.Model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public MongoTemplate mongoTemplate;


    public Optional<User> getUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



    public Map<User, Optional<Role>> createUser(String name, List<String> userList) {
        Set<Role> roles = new HashSet<>();
        User user = userRepository.insert(new User(userList.get(1),userList.get(2),userList.get(3),roles));
        mongoTemplate.update(Role.class)
                .matching(Criteria.where("name").is(name))
                .apply(new Update().push("email").value(userList.get(2)))
                .first();
        Optional<Role> role = roleRepository.findByName(name);
        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(userList.get(2)))
                .apply(new Update().push("role").value(name))
                .first();
        Map<User, Optional<Role>> hm
                = new HashMap<User, Optional<Role>>();
        hm.put(user,role);
        return hm;
    }
}
