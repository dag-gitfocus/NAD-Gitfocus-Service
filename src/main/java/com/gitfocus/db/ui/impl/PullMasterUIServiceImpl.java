package com.gitfocus.db.ui.impl;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullMasterCommitDetailOnDate;
import com.gitfocus.db.ui.model.PullRequestCount;
import com.gitfocus.db.ui.service.IPullMasterUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * 
 * @author Tech Mahindra 
 * Service class for PullMaster Service fetch the data from DB and show in GUI
 * 
 */
@Service
public class PullMasterUIServiceImpl implements IPullMasterUIService {

	private static final Logger logger = LoggerFactory.getLogger(PullMasterUIServiceImpl.class.getSimpleName());

	public PullMasterUIServiceImpl() {
		super();
		logger.info("PullMasterUIServiceImpl init");
	}

	@Autowired
	TeamMembersRepository teamMemRepos;
	@Autowired
	PullMasterRepository pMasterRepository;
	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	PullMasterRepository pullRepository;

	@Override
	public List<PullRequestCount> getCountOfPR(String teamName, String repoName, String timeperiod, String endDate) throws JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("getCountofPR " + teamName, repoName, timeperiod, endDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		List<Object> teamMembers = null;
		List<Object[]> memberPullList = new ArrayList<Object[]>();
		List<Object[]> memberPullListResults = new ArrayList<Object[]>();
		ArrayList<PullRequestCount> pullList = new ArrayList<PullRequestCount>();
		int pCount = 0;

		// get team_memebers based on team_name
		teamMembers = teamMemRepos.getTeamMembersByTeamName(teamName);
		// one week time period data
		if (timeperiod.equalsIgnoreCase("oneweek")) {
			for (Object userId : teamMembers) {
				memberPullList = pMasterRepository.getPullDetailsForMemberForOneWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberPullList) {
					pCount = ((BigInteger) obj[3]).intValue();
					// If PRcount is 0 then userId also 0
					// set userId even if PRcount is 0
					if (pCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberPullListResults.addAll(memberPullList);
			}
		}
		// two week time period data
		if (timeperiod.equalsIgnoreCase("twoweek")) {
			for (Object userId : teamMembers) {
				memberPullList = pMasterRepository.getPullDetailsForMemberForTwoWeeks(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberPullList) {
					pCount = ((BigInteger) obj[3]).intValue();
					// If PRcount is 0 then userId also 0
					// set userId even if PRcount is 0
					if (pCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberPullListResults.addAll(memberPullList);
			}
		}
		for (Object[] obj : memberPullListResults) {
			PullRequestCount model = new PullRequestCount();
			String user = (String) obj[1];
			String createdDate = sd.format(obj[2]);
			pCount = ((BigInteger) obj[3]).intValue();
			String prCount = String.valueOf(pCount);

			model.setUser(user);
			model.setPrCreatedDate(createdDate);
			model.setPrCount(prCount);

			pullList.add(model);

			if (pullList.size() == 0) {
				logger.error("There is no Records for particular request on getCountofPR " + teamName, repoName,
						timeperiod, endDate);

				throw new ResourceNotFoundException("There is no Records for particular request on PullDetailsService",
						teamName, repoName);
			}
		}

		logger.info("Data processed successfully for getCountofPR()  " + teamName, repoName, timeperiod, endDate);

		return pullList;
	}

	@Override
	public List<PullMasterCommitDetailOnDate> pullDetailOnDateForTeamMemeber(String userName, String repoName, String commitDate) throws ParseException {
		// TODO Auto-generated method stub
		logger.info("pullDetailOnDateForTeamMemeber " + userName, repoName, commitDate);
		ArrayList<String> pullNo = new ArrayList<String>();
		ArrayList<Boolean> merged = new ArrayList<Boolean>();
		ArrayList<Boolean> notMerged = new ArrayList<Boolean>();
		ArrayList<String> fromBranch = new ArrayList<String>();
		ArrayList<String> createdTime = new ArrayList<String>();
		ArrayList<Map.Entry<Long, String>> firstCommit = new ArrayList<Map.Entry<Long, String>>();
		ArrayList<String> commitCount = new ArrayList<String>();
		boolean mergedStatus = false;
		Date cDate = null;

		List<Object[]> pullMasterList = new ArrayList<Object[]>();
		String[] firstCommentList = null;
		ArrayList<PullMasterCommitDetailOnDate> pullList = new ArrayList<PullMasterCommitDetailOnDate>();
		PullMasterCommitDetailOnDate model = new PullMasterCommitDetailOnDate();

		// get startDate and endDate
		Date[] inputDates = GitFocusUtil.getStartAndEndDate(commitDate);
		int repoId = uReposRepository.findRepoId(repoName);

		// get pullMasterDetails based on userName, repoId, startDate and endDate
		pullMasterList = pullRepository.getPullDetailOnDateForMemebers(userName, repoId, inputDates[0], inputDates[1]);

		// get noOfDaysBetween or hours for first comment 
		firstCommentList = pullRepository.getTimeToFirstComment(repoId);
		for(String commitList : firstCommentList) {
			String[] commitDates = commitList.split(",");
			String createdDate = (String)Array.get(commitDates, 0);
			String reviewedDate = (String)Array.get(commitDates, 1);
			// calculate noOfDaysBetween if createdDate, reviewedDate aren't same
			// calculate hours if createdDate, reviewedDate are same
			List<Entry<Long, String>> daysDiff = GitFocusUtil.calculteDaysBetweenTwoDatesOrHours(createdDate, reviewedDate);
			firstCommit.addAll(daysDiff);
		}
		for (Object[] obj : pullMasterList) {
			pullNo.add(String.valueOf((String) obj[0].toString()));
			mergedStatus = (boolean) obj[1];
			if (mergedStatus == true) {
				merged.add(mergedStatus);
			} else {
				notMerged.add(mergedStatus);
			}

			fromBranch.add((String) obj[2]);
			cDate = (Date) obj[3];
			commitCount.add(String.valueOf((String) obj[4].toString()));
			createdTime.add(GitFocusUtil.convertDateToString(cDate));
		}

		model.setUser(userName);
		model.setRepoName(repoName);
		model.setCommitDate(GitFocusUtil.convertDateToString(inputDates[0]));
		model.setPullNo(pullNo);
		model.setMerged(merged);
		model.setNotMerged(notMerged);
		model.setBranchName(fromBranch);
		model.setCreatedTime(createdTime);
		model.setNoOfDaysBwfirstCommit(firstCommit);
		model.setCommitCount(commitCount);

		pullList.add(model);

		if (pullList.size() == 0) {
			logger.error("There is no Records for particular request on pullDetailOnDateForTeamMemeber " + userName, repoName, commitDate);
			throw new ResourceNotFoundException("There is no Records for particular request on pullDetailOnDateForTeamMemeber", userName, repoName);
		}

		logger.info("Data processed successfully for pullDetailOnDateForTeamMemeber()  " + userName, repoName, commitDate);
		return pullList;
	}

}