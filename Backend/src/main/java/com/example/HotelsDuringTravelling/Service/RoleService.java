package com.example.HotelsDuringTravelling.Service;

import com.example.HotelsDuringTravelling.Model.Role;
import com.example.HotelsDuringTravelling.Repository.RoleRepository;
import com.example.HotelsDuringTravelling.Repository.UserRepository;
import com.example.HotelsDuringTravelling.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public MongoTemplate mongoTemplate;
    public Map<Role, Optional<User>> createRole(String email, String name) {
        List<User> listUser = new ArrayList<User>();
        Role role = roleRepository.insert(new Role(name,listUser));
        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(email))
                .apply(new Update().addToSet("name").value(name))
                .first();
        Optional<User> user = userRepository.findByEmail(email);
        mongoTemplate.update(Role.class)
                .matching(Criteria.where("name").is(name))
                .apply(new Update().push("email").value(email))
                .first();
        Map<Role, Optional<User>> hm
                = new HashMap<Role, Optional<User>>();
        hm.put(role,user);
        return hm;
    }

    public Optional<Role> getRole(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
