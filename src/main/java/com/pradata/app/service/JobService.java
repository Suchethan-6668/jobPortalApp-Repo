package com.pradata.app.service;

import com.pradata.app.model.JobPost;
import com.pradata.app.model.User;
import com.pradata.app.repo.JobRepo;
import com.pradata.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class JobService {
    @Autowired public JobRepo repo;
    @Autowired private UserRepo userRepo;

    public ResponseEntity<JobPost> addJob(JobPost jobPost, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        jobPost.setEmployer(user);
        repo.save(jobPost);
        return new ResponseEntity<>(jobPost, HttpStatus.CREATED);
    }

    public ResponseEntity<List<JobPost>> getAllJobs() { return new ResponseEntity<>(repo.findAll(), HttpStatus.OK); }
    public ResponseEntity<JobPost> getJob(int postId) {
        JobPost job = repo.findById(postId).orElse(null);
        if (job == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    public ResponseEntity<String> updateJob(int id, JobPost updated, Principal principal) {
        JobPost existing = repo.findById(id).orElse(null);
        if (existing == null) return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        User user = userRepo.findByUsername(principal.getName());
        boolean isAdmin = user.getRole().getRoleName().equals("ADMIN");
        boolean isOwner = existing.getEmployer().getUserId() == user.getUserId();
        if (!isOwner && !isAdmin) return new ResponseEntity<>("Not authorized", HttpStatus.FORBIDDEN);
        existing.setPostTechStack(updated.getPostTechStack());
        existing.setPostDesc(updated.getPostDesc());
        existing.setPostProfile(updated.getPostProfile());
        existing.setReqExperience(updated.getReqExperience());
        repo.save(existing);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }
    public ResponseEntity<String> deleteJob(int postId, Principal p) {
        JobPost job = repo.findById(postId).orElse(null);
        if (job == null) return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        User user = userRepo.findByUsername(p.getName());
        boolean isAdmin = user.getRole().getRoleName().equals("ADMIN");
        boolean isOwner = job.getEmployer().getUserId() == user.getUserId();
        if (!isOwner && !isAdmin) return new ResponseEntity<>("Not authorized", HttpStatus.FORBIDDEN);
        repo.deleteById(postId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    public List<JobPost> search(String keyword) {
        return repo.findByPostDescContainingOrPostProfileContaining(keyword, keyword);
    }
    public ResponseEntity<String> deleteAllJobs(Principal p) {
        User u = userRepo.findByUsername(p.getName());
        if (!u.getRole().getRoleName().equals("ADMIN"))
            return new ResponseEntity<>("Not authorized", HttpStatus.FORBIDDEN);
        repo.deleteAll();
        return new ResponseEntity<>("All jobs deleted", HttpStatus.OK);
    }
}

