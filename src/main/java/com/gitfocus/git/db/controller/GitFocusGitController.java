package com.gitfocus.git.db.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitfocus.git.db.service.IBranchDetailGitService;
import com.gitfocus.git.db.service.ICommitDetailGitService;
import com.gitfocus.git.db.service.IPullCommitGitService;
import com.gitfocus.git.db.service.IPullMasterGitService;
import com.gitfocus.git.db.service.IReviewDetailsGitService;

/**
 * @author Tech Mahindra 
 * Controller class for GitFocus-Service to store data from Git to Database
 */
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GitFocusGitController {

	private static final Logger logger = LoggerFactory.getLogger(GitFocusGitController.class.getSimpleName());

	public GitFocusGitController() {
		super();
		logger.info("GitFocusController init");
	}

	@Autowired
	private ICommitDetailGitService commitDetailService;
	@Autowired
	private IBranchDetailGitService branchDetailService;
	@Autowired
	private IPullMasterGitService pullMasterService;
	@Autowired
	private IPullCommitGitService pullCommitService;
	@Autowired
	private IReviewDetailsGitService reviewDetailsService;

	boolean serviceResult = false;
	String serviceMessage = null;

	// BranchDetail Service
	@GetMapping("/gitfocus/insertbranchdetail")
	public String getBranchDetailInfo() throws ParseException {
		logger.info("BranchDetail Service");
		serviceResult = branchDetailService.save();
		if (serviceResult = true) {
			serviceMessage = "Records inserted successfully for BranchDetail Service";
			logger.info("Records inserted successfully for BranchDetail Service");
		} else {
			serviceMessage = "Records not inserted successfully for BranchDetail Service";
			logger.info("Records not inserted successfully for BranchDetail Service");

		}
		return serviceMessage;
	}

	// CommitDetail Service
	@GetMapping("/gitfocus/insertcommitdetail")
	public String getCommitDetailInfo() throws ParseException {
		logger.info("CommitDetail Service");
		serviceResult = commitDetailService.save();
		if (serviceResult = true) {
			serviceMessage = "Records inserted successfully for CommitDetail Service";
			logger.info("Records inserted successfully for CommitDetail Service");
		} else {
			serviceMessage = "Records not inserted successfully for CommitDetail Service";
			logger.info("Records inserted successfully for CommitDetail Service");
		}
		return serviceMessage;
	}

	// PullMasterDetail Service
	@GetMapping("/gitfocus/insertpullmasterdetail")
	public String getPullMasterDetailInfo() throws ParseException {
		logger.info("PullMasterDetail Service");
		serviceResult = pullMasterService.save();
		if (serviceResult = true) {
			serviceMessage = "Records inserted successfully for PullMasterDetail Service";
			logger.info("Records inserted successfully for PullMasterDetail Service");
		} else {
			serviceMessage = "Records not inserted successfully for BranchDetail Service";
			logger.info("Records not inserted successfully for PullMasterDetail Service");

		}
		return serviceMessage;
	}

	// PullCommitDetail Service
	@GetMapping("/gitfocus/insertpullcommitdetail")
	public String getPullCommitDetailInfo() throws ParseException {
		logger.info("PullCommitDetail Service");
		serviceResult = pullCommitService.save();
		if (serviceResult = true) {
			serviceMessage = "Records inserted successfully for PullCommitDetail Service";
			logger.info("Records inserted successfully for PullCommitDetail Service");
		} else {
			serviceMessage = "Records not inserted successfully for PullCommitDetail Service";
			logger.info("Records not inserted successfully for PullCommitDetail Service");

		}
		return serviceMessage;
	}

	// ReviewDetail Service
	@GetMapping("/gitfocus/insertreviewdetail")
	public String getReviewDetailInfo() throws ParseException {
		logger.info("ReviewDetail Service");
		serviceResult = reviewDetailsService.save();
		if (serviceResult = true) {
			serviceMessage = "Records inserted successfully for ReviewDetail Service";
			logger.info("Records inserted successfully for ReviewDetail Service");
		} else {
			serviceMessage = "Records not inserted successfully for ReviewDetail Service";
			logger.info("Records not inserted successfully for ReviewDetail Service");

		}
		return serviceMessage;
	}
}
