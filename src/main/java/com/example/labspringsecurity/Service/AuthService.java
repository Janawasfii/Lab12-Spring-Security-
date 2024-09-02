package com.example.labspringsecurity.Service;

import com.example.labspringsecurity.API.APIException;
import com.example.labspringsecurity.Model.User;
import com.example.labspringsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    //Get all users
    public List<User> getAllUser(){
        return authRepository.findAll();
    }

    //Add user
        public void register(User user) {
            // user.setRole("USER");
            String hash= new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(hash);
            authRepository.save(user);
        }

    //update user
    public void updateUser(User user,Integer id){
        User u = authRepository.findUserById(id);
        if (u==null){
            throw new APIException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        authRepository.save(u);}

    //Delete user
    public void deleteUser(Integer id){
        User u = authRepository.findUserById(id);
        if (u==null){
            throw new APIException("User not found");
        }
        authRepository.delete(u);}
}
