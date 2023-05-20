package com.example.HotelsDuringTravelling.Controller;

import com.example.HotelsDuringTravelling.Model.Role;
import com.example.HotelsDuringTravelling.Model.User;
import com.example.HotelsDuringTravelling.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping()
    public ResponseEntity<Map<Role, Optional<User>>> createRole(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Map<Role, Optional<User>>>(roleService.createRole(payload.get("email"),payload.get("name")), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getAllRole(){
        return new ResponseEntity<List<Role>>(roleService.getAllRole(),HttpStatus.OK);
    }


    @GetMapping("/getRole")
    public ResponseEntity<Optional<Role>> getRole(@RequestBody String name){
        return new ResponseEntity<Optional<Role>>(roleService.getRole(name),HttpStatus.OK);
    }

}
