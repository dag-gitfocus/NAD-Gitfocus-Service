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
import com.gitfocus.db.ui.model.DailyUserCommitList;
import com.gitfocus.db.ui.model.TeamMembersCommitDetail;
import com.gitfocus.db.ui.model.TeamMembersCommitDetailOnDate;
import com.gitfocus.db.ui.model.TeamRepositoryCommitDetails;
import com.gitfocus.db.ui.service.ICommitDetailUIService;

/**
 * @author Tech Mahindra 
 * Controller class for commit_details service from database to GUI
 */
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class CommitDetailsUIController {

    private static final Logger logger = LoggerFactory.getLogger(CommitDetailsUIController.class);

    public CommitDetailsUIController() {
        super();
        logger.info("CommitDetailsController init");
    }

    @Autowired
    ICommitDetailUIService commitService;

    /**
     * 
     * @param teamName
     * @param repoName
     * @param timeperiod
     * @return dateBasedCommitDetailsForTeamMembers
     */
    @GetMapping("/gitfocus/getDateBasedCommitDetailsForTeamMembers/{teamName}/{repoName}/{timeperiod}/{endDate}")
    public List<TeamMembersCommitDetail> getDateBasedCommitDetailsForTeamMembers(
            @PathVariable("teamName") String teamName, @PathVariable("repoName") String repoName,
            @PathVariable("timeperiod") String timeperiod, @PathVariable("endDate") String endDate)
            throws JsonProcessingException {

        logger.info("getDateBasedCommitDetailsForTeamMembers", teamName, repoName, timeperiod, endDate);
        List<TeamMembersCommitDetail> commitDetailsJson = null;
        commitDetailsJson = commitService.dateBasedCommitDetailsForTeam(teamName, repoName, timeperiod, endDate);
        logger.info("getRepoCommitCountWeek Records", commitDetailsJson);
        return commitDetailsJson;
    }

    /**
     * 
     * @param repoName
     * @param userId
     * @param commitDate
     * @return commitDetailsOnDateJson
     * @throws ParseException
     */
    @GetMapping("/gitfocus/getCommitDetailOnDateForMemebers/{repoName}/{userId}/{commitDate}")
    public List<TeamMembersCommitDetailOnDate> getCommitDetailOnDateForMemebers(
            @PathVariable("repoName") String repoName, @PathVariable("userId") String userId,
            @PathVariable("commitDate") String commitDate) throws ParseException {
        logger.info("getCommitDetailOnDateForMemebers", repoName, userId, commitDate);
        List<TeamMembersCommitDetailOnDate> commitDetailsOnDateJson = null;
        commitDetailsOnDateJson = commitService.commitDetailOnDateForMemebers(userId, repoName, commitDate);
        logger.info("getCommitDetailOnDateForMemebers Records", commitDetailsOnDateJson);
        return commitDetailsOnDateJson;
    }
    
    /**
     * 
     * @param repoName
     * @param userId
     * @param commitDate
     * @return commitDetailsOnDateJson
     * @throws ParseException
     */
    @GetMapping("/gitfocus/getDailyMemberCommitList/{repoName}/{userId}/{commitDate}")
    public List<DailyUserCommitList> getDailyMemberCommitList(@PathVariable("repoName") String repoName, @PathVariable("userId") String userId,  
            @PathVariable("commitDate") String commitDate) throws ParseException {
        logger.info("getDailyMemberCommitList", repoName, userId, commitDate);
        List<DailyUserCommitList> commitDetailsOnDateJson = null;
        commitDetailsOnDateJson = commitService.getDailyMemberCommitList(repoName, userId, commitDate);
        logger.info("getDailyMemberCommitList Records", commitDetailsOnDateJson);
        return commitDetailsOnDateJson;
    }

    /**
     * 
     * @param teamName, Repository
     * @return commitDetailsByTeamAndRepos
     */
    @GetMapping("/gitfocus/getRepoNames/{teamName}/{repoName}")
    public TeamRepositoryCommitDetails commitDetailsByTeamAndRepos(@PathVariable("teamName") String teamName,
            @PathVariable("repoName") String repoName) {
        logger.info("commitDetailsByTeamAndRepos", teamName, repoName);
        TeamRepositoryCommitDetails commitDetailsByTeamAndRepos = commitService.getCommitDetailsTeamAndRepos(teamName,
                repoName);
        logger.info("commitDetailsByTeamAndRepos Records", commitDetailsByTeamAndRepos);
        return commitDetailsByTeamAndRepos;
    }
}
