package com.gitfocus.db.ui.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gitfocus.db.ui.model.TeamMembersCommitDetailBasedOnPR;
import com.gitfocus.db.ui.service.IPullCommitUIService;

/**
 * 
 * @author Tech Mahindra 
 * Controller class for pull_commit service from database to GUI
 * 
 */
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PullCommitDetailsUIController {

	private static final Logger logger = LoggerFactory.getLogger(PullCommitDetailsUIController.class);

	public PullCommitDetailsUIController() {
		super();
		logger.info("PullCommitDetailsUIController init");
	}

	@Autowired
	IPullCommitUIService pullCommitService;

	/**
	 * 
	 * @param pullNo
	 * @param branchName
	 * @param repoName
	 * @return
	 * @throws ParseException
	 */

	@GetMapping("/gitfocus/getPullCommitDetailBasedOnPR/{pullNo}/{branchName}/{repoName}/{rownum}")
	public List<TeamMembersCommitDetailBasedOnPR> getPullCommitDetailBasedOnPR(@PathVariable("pullNo") int pullNo,
			@PathVariable("branchName") String branchName, @PathVariable("repoName") String repoName,@PathVariable("rownum") int rownum) throws ParseException {
		logger.info("getPullCommitDetailBasedOnPR", pullNo, repoName);
		List<TeamMembersCommitDetailBasedOnPR> commitDetailsBasedonPRJson = null;
		commitDetailsBasedonPRJson = pullCommitService.getCommitDetailsBasedOnPR(pullNo, branchName, repoName, rownum);
		logger.info("getPullCommitDetailBasedOnPR Records", commitDetailsBasedonPRJson);
		return commitDetailsBasedonPRJson;
	}
}
