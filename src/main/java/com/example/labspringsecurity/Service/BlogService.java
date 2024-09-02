package com.example.labspringsecurity.Service;

import com.example.labspringsecurity.API.APIException;
import com.example.labspringsecurity.Model.Blog;
import com.example.labspringsecurity.Model.User;
import com.example.labspringsecurity.Repository.AuthRepository;
import com.example.labspringsecurity.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();}



    public List<Blog> getMyBlog(Integer id) {
        User user = authRepository.findUserById(id);
        return blogRepository.findAllByUser(user);}



    public void addBlog(Integer authorId, Blog blog) {
        User user = authRepository.findUserById(authorId);
        if (user==null){
            throw new APIException("User not found");}
        blog.setUser(user);
        blogRepository.save(blog);}


    public void updateBlog(Integer blog_id, Integer auth_id, Blog blog) {
        User user = authRepository.findUserById(auth_id);
        Blog b = blogRepository.findBlogById(blog_id);
        if (user==null){
            throw new APIException("User not found");}
        if (b==null){
            throw new APIException("Blog not found");}
        else if (b.getUser().getId()!= auth_id) {
            throw new APIException("Sorry, but you are not allowed to update this blog");}
        b.setTitle(blog.getTitle());
        b.setBody(blog.getBody());
        blogRepository.save(blog);}


    public void deleteBlog(Integer blog_id, Integer auth_id) {
        User user = authRepository.findUserById(auth_id);
        Blog b = blogRepository.findBlogById(blog_id);
        if (user==null){
            throw new APIException("User not found");}
        if (b==null){
            throw new APIException("Blog not found");}
        else if (b.getUser().getId()!=auth_id) {
            throw new APIException("Sorry, but you are not allowed to delete this blog");}
        blogRepository.delete(b);}


    public Blog getBlogById(Integer blog_id, Integer auth_id) {
        User user = authRepository.findUserById(auth_id);
        Blog b = blogRepository.findBlogById(blog_id);
        if (user==null){
            throw new APIException("User not found");}
        if (b==null){
            throw new APIException("Blog not found");}
        else if (b.getUser().getId()!=auth_id) {
            throw new APIException("Sorry but you are not allowed");}
        return b;}

    public Blog getBlogByTitle(String title) {
        Blog b = blogRepository.findBlogByTitle(title);
        if (b==null){
            throw new APIException("Blog not found");}
        return b;}

}
