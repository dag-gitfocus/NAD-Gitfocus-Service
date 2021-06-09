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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullMasterCommitDetailOnDate;
import com.gitfocus.db.ui.model.PullRequestCount;
import com.gitfocus.db.ui.service.IPullMasterUIService;

/**
 * @author Tech Mahindra
 * Controller class for pull_details service from database to GUI
 */
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PullDetailsUIController {

	private static final Logger logger = LoggerFactory.getLogger(PullDetailsUIController.class.getSimpleName());

	public PullDetailsUIController() {
		super();
		logger.info("PullDetailsController init");
	}

	@Autowired
	IPullMasterUIService pullMasterService;

	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @param timeperiod
	 * @return dateBasedPullDetailsForTeamMembers
	 */
	@GetMapping("/gitfocus/getpullrequestcount/{teamName}/{repoName}/{timeperiod}/{endDate}")
	public List<PullRequestCount> getCountofPR(@PathVariable("teamName") String teamName,
			@PathVariable("repoName") String repoName, @PathVariable("timeperiod") String timeperiod,
			@PathVariable("endDate") String endDate) throws JsonProcessingException {

		logger.info("getCountofPR", teamName, repoName, timeperiod, endDate);
		List<PullRequestCount> pullDetailsJson = null;
		pullDetailsJson = pullMasterService.getCountOfPR(teamName, repoName, timeperiod, endDate);
		logger.info("getRepoPullCountWeek Records", pullDetailsJson);
		return pullDetailsJson;
	}
	
    /**
     * 
     * @param repoName
     * @param userId
     * @param commitDate
     * @return
     * @throws ParseException
     */
    @GetMapping("/gitfocus/getPullDetailsOnDateForTeamMemeber/{repoName}/{userId}/{commitDate}")
    public List<PullMasterCommitDetailOnDate> getPullDetailsOnDateForTeamMemeber(
            @PathVariable("repoName") String repoName, @PathVariable("userId") String userId,
            @PathVariable("commitDate") String commitDate) throws ParseException {
        logger.info("getPullDetailsOnDateForTeamMemeber", userId, commitDate);
        List<PullMasterCommitDetailOnDate> pullMasterDetailsOnDateJson = null;
        pullMasterDetailsOnDateJson = pullMasterService.pullDetailOnDateForTeamMemeber(userId, repoName, commitDate);
        logger.info("getCommitDetailOnDateForMemebers Records", pullMasterDetailsOnDateJson);
        return pullMasterDetailsOnDateJson;
    }
}
