package com.example.labspringsecurity.Controller;

import com.example.labspringsecurity.Model.Blog;
import com.example.labspringsecurity.Model.User;
import com.example.labspringsecurity.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor

public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlog() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());}


    @GetMapping("/get-my")
    public ResponseEntity getMyBlog(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(blogService.getMyBlog(id));}


    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body("Blog added to "+ user.getUsername());}

    @PutMapping("/update/{blog_id}/{auth_id}")
    public ResponseEntity updateBlog(@PathVariable Integer blog_id, @PathVariable Integer auth_id, @Valid @RequestBody Blog blog){
        blogService.updateBlog(blog_id,auth_id,blog);
        return ResponseEntity.status(200).body("Blog updated");}


    @DeleteMapping("/delete/{blog_id}/{auth_id}")
    public ResponseEntity deleteBlog(@PathVariable Integer blog_id, @PathVariable Integer auth_id){
        blogService.deleteBlog(blog_id,auth_id);
        return ResponseEntity.status(200).body("Blog deleted");}


    @GetMapping("/getBlogById/{blog_id}/{auth_id}")
    public ResponseEntity getBlogById(@PathVariable Integer blog_id, @PathVariable Integer auth_id){
        return ResponseEntity.status(200).body(blogService.getBlogById(blog_id,auth_id));
    }

    @GetMapping("/getBlogByTitle/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }
}
