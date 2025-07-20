package com.pradata.app.controller;

import com.pradata.app.model.JobPost;
import com.pradata.app.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class JobRestController {
    @Autowired
    private JobService service;

    @GetMapping("employee/jobPosts")
    public ResponseEntity<List<JobPost>> getAllJobs() { return service.getAllJobs(); }

    @GetMapping("employee/jobPosts/{postId}")
    public ResponseEntity<JobPost> getJob(@PathVariable int postId) { return service.getJob(postId); }

    @PostMapping("employer/jobPosts")
    public ResponseEntity<JobPost> addJob(@RequestBody JobPost jobPost, Principal principal) {
        return service.addJob(jobPost, principal);
    }

    @PutMapping("employer/jobPosts/{postId}")
    public ResponseEntity<String> updateJob(@PathVariable int postId, @RequestBody JobPost updated, Principal p) {
        return service.updateJob(postId, updated, p);
    }

    @DeleteMapping("employer/jobPosts/{postId}")
    public ResponseEntity<String> deleteJob(@PathVariable int postId, Principal p) {
        return service.deleteJob(postId, p);
    }

    @DeleteMapping("employer/jobPosts/delete")
    public ResponseEntity<String> deleteAllJobs(Principal p) { return service.deleteAllJobs(p); }

    @GetMapping("employee/jobPosts/keyword/{keyword}")
    public ResponseEntity<List<JobPost>> searchByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(service.search(keyword));
    }
}

