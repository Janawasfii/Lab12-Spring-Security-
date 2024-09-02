package com.example.labspringsecurity.Controller;

import com.example.labspringsecurity.Model.User;
import com.example.labspringsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getAllUser());}


    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody User user){
        authService.register(user);
        return ResponseEntity.status(200).body("User registered successfully");}


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user){
        authService.updateUser(user, id);
        return ResponseEntity.status(200).body("User updated successfully");}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");}


}
