package com.pradata.app.controller;

import com.pradata.app.model.JobPost;
import com.pradata.app.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class JobRestController {
    @Autowired
    private JobService service;

    @GetMapping("employee/jobPosts")
    public ResponseEntity<List<JobPost>> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("employee/jobPosts/{postId}")
    public ResponseEntity<JobPost> getJob(@PathVariable("postId") int postId){
        return service.getJob(postId);
    }

    @PostMapping("employer/jobPosts")
    public ResponseEntity<JobPost> addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @PutMapping("employer/jobPosts/{postId}")
    public ResponseEntity<String> updateJob(@PathVariable int postId,@RequestBody JobPost updatedjobPost){
        return service.updateJob(postId,updatedjobPost);

    }

    @DeleteMapping("employer/jobPosts/{postId}")
    public ResponseEntity<String> deleteJob(@PathVariable("postId") int postId){
        return service.deleteJob(postId);

    }

    @DeleteMapping("employer/jobPosts/delete")
    public ResponseEntity<String> deleteAllJobs(){
        return service.deleteAllJobs();
    }

    @GetMapping("load")
    public String load(){
        service.load();
        return "Success";
    }

    @GetMapping("employee/jobPosts/keyword/{keyword}")
    public ResponseEntity<List<JobPost>> searchByKeyword(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(service.search(keyword), HttpStatus.OK);
    }



}
