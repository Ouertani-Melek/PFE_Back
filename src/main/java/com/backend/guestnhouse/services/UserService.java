package com.backend.guestnhouse.services;


import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseRepository houseRepository;


    public String acrchiveUser(String id){
       User user= userRepository.findById(id).orElse(null);
        if(user!=null){
           List<House> lh= houseRepository.findHouseByUser(id);
           if(!lh.isEmpty()){
               for (House h:lh) {
                   h.setArchived(1);
                   houseRepository.save(h);
               }
           }
            user.setArchived(1);
            userRepository.save(user);
            return "User Archived";
        }else return "User not found";
    }
    public String deacrchiveUser(String id){
        User user= userRepository.findById(id).orElse(null);
        if(user!=null){
            user.setArchived(0);
            userRepository.save(user);
            return "User Dearchived";
        }else return "User not found";
    }
    public String updateUser(String id,User user){
        User user1= userRepository.findById(id).orElse(null);
        if(user1!=null){
            user.setId(id);
            user.setUsername(user.getEmail());
            userRepository.save(user);
            return "user updated";
        }else return "user not found";
    }
   /* public List<User> getAdmins() {
       return userRepository.findAllUsers(0,"ROLE_ADMIN");
    }
    public List<User> getHosts(){
        return userRepository.findAllUsers(0,"ROLE_HOST");
    }

    public List<User> getUsers(){
        return userRepository.findAllUsers(0,"ROLE_USER");
    }

    public List<User> getArchivedUsers(){
        return userRepository.findAllUsers(1,"ROLE_USER");

   public List<User> getUsers(){
       return userRepository.findAllUsers();
   }  }*/
}
