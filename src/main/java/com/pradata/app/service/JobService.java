package com.pradata.app.service;

import com.pradata.app.model.JobPost;
import com.pradata.app.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;


    // method to add a jobPost
    public ResponseEntity<String> addJob(JobPost jobPost) {
        repo.save(jobPost);
        return new ResponseEntity<>("Job added",HttpStatus.CREATED);

    }


    //method to return all JobPosts
    public ResponseEntity<List<JobPost>> getAllJobs() {
        List<JobPost> jobs =  repo.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }


    public ResponseEntity<JobPost> getJob(int postId) {
        //int k = 10/0;
        JobPost job = repo.findById(postId).orElse(null);
        if(job == null){
            return new ResponseEntity<>(new JobPost(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job,HttpStatus.OK);
    }

    public ResponseEntity<String> updateJob(int id,JobPost updatedJobPost) {
        JobPost existingJobPost = repo.findById(id).orElse(null);
        if(existingJobPost == null){
            return new ResponseEntity<>("id not there to update in jobPosts",HttpStatus.NOT_FOUND);
        }
        existingJobPost.setPostTechStack(updatedJobPost.getPostTechStack());
        existingJobPost.setPostDesc(updatedJobPost.getPostDesc());
        existingJobPost.setPostProfile(updatedJobPost.getPostProfile());
        existingJobPost.setReqExperience(updatedJobPost.getReqExperience());
        repo.save(existingJobPost);
        return new ResponseEntity<>("Successfully Replaced",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteJob(int postId) {
        JobPost existingJobPost = repo.findById(postId).orElse(null);
        if(existingJobPost == null){
            return new ResponseEntity<>("id not there to delete in jobPosts",HttpStatus.NOT_FOUND);
        }
        repo.deleteById(postId);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }

    public void load() {

        List<JobPost> jobs =
                new ArrayList<>(List.of(
                        new JobPost(1, "Software Engineer", "Exciting opportunity for a skilled software engineer.", 3, List.of("Java", "Spring", "SQL")),
                        new JobPost(2, "Data Scientist", "Join our data science team and work on cutting-edge projects.", 5, List.of("Python", "Machine Learning", "TensorFlow")),
                        new JobPost(3, "Frontend Developer", "Create amazing user interfaces with our talented frontend team.", 2, List.of("JavaScript", "React", "CSS")),
                        new JobPost(4, "Network Engineer", "Design and maintain our robust network infrastructure.", 4, List.of("Cisco", "Routing", "Firewalls")),
                        new JobPost(5, "UX Designer", "Shape the user experience with your creative design skills.", 3, List.of("UI/UX Design", "Adobe XD", "Prototyping"))

                ));

        repo.saveAll(jobs);

    }

    public List<JobPost> search(String keyword) {
        return repo.findByPostDescContainingOrPostProfileContaining(keyword,keyword);
    }

    public ResponseEntity<String> deleteAllJobs() {
        repo.deleteAll();
        return new ResponseEntity<>("Deleted All jobs",HttpStatus.OK);
    }
}
