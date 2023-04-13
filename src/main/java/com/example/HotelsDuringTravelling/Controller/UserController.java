package com.example.HotelsDuringTravelling.Controller;

import com.example.HotelsDuringTravelling.Exception.UserNotFoundException;
import com.example.HotelsDuringTravelling.Model.Role;
import com.example.HotelsDuringTravelling.Model.User;
import com.example.HotelsDuringTravelling.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/newuser")
    public ResponseEntity<Map<User, Optional<Role>>> createReview(@RequestBody List<String> payload) {

        return new ResponseEntity<Map<User, Optional<Role>>>(userService.createUser(payload.get(0),payload), HttpStatus.CREATED);
    }
    @GetMapping("/getuser")
    public ResponseEntity<Optional<User>> getUser(@RequestParam ObjectId id){
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        } else {
            return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllusers")
    public ResponseEntity<List<User>> getUser(){
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        } else {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
    }
}
