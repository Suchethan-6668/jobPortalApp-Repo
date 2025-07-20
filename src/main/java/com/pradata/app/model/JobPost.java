package com.pradata.app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "jobs")
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@ManyToOne @JoinColumn(name = "employer_id", nullable = false)
	private User employer;

	@Column(nullable=false)
	private String postProfile;

	@Column(nullable=false, columnDefinition="TEXT")
	private String postDesc;

	private Integer reqExperience;

	@ElementCollection
	private List<String> postTechStack;
	// getters/setters
}

