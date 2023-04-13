package com.example.HotelsDuringTravelling.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private ObjectId id;
    private String name;
    @DocumentReference
    private List<User> users;

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
}
