package com.gitfocus.db.ui.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.ReviewDetailOnDateForMemebers;
import com.gitfocus.db.ui.model.ReviewDetailsForTeam;
import com.gitfocus.db.ui.service.IReviewDetailUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.ReviewDetailsRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * 
 * @author Tech Mahindra 
 * Service class for ReviewDetails to fetch the data from DB and show in GUI
 * 
 */
@Service
public class ReviewDetailsUIServiceImpl implements IReviewDetailUIService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewDetailsUIServiceImpl.class.getSimpleName());

	public ReviewDetailsUIServiceImpl() {
		super();
		logger.info("ReviewDetailsUIServiceImpl init");
	}

	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	ReviewDetailsRepository reviewDetailsRepo;
	@Autowired
	TeamMembersRepository teamMemRepos;
	@Autowired
	private UnitReposRepository uReposRepository;

	@Override
	public List<ReviewDetailsForTeam> dateBasedReviewDetailsForTeamMembers(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("dateBasedCommitDetailsForTeamMembers " + teamName, repoName, timeperiod, endDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		List<Object> teamMembers = null;
		List<Object[]> memberReviewList = new ArrayList<Object[]>();
		List<Object[]> memberReviewListResults = new ArrayList<Object[]>();
		ArrayList<ReviewDetailsForTeam> commitList = new ArrayList<ReviewDetailsForTeam>();
		int cCount = 0;

		// get team_memebers based on team_name
		teamMembers = teamMemRepos.getTeamMembersByTeamName(teamName);
		// one week time period of data
		if (timeperiod.equalsIgnoreCase("oneweek")) {
			for (Object userId : teamMembers) {
				memberReviewList = reviewDetailsRepo.getCommitDetailsForMemberForOneWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberReviewList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberReviewListResults.addAll(memberReviewList);
			}
		}
		// two week time period of data
		if (timeperiod.equalsIgnoreCase("twoweek")) {
			for (Object userId : teamMembers) {
				memberReviewList = reviewDetailsRepo.getCommitDetailsForMemberForTwoWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberReviewList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberReviewListResults.addAll(memberReviewList);
			}
		}
		for (Object[] obj : memberReviewListResults) {
			ReviewDetailsForTeam model = new ReviewDetailsForTeam();
			String user = (String) obj[1];
			String commitDate = sd.format(obj[2]);
			cCount = ((BigInteger) obj[3]).intValue();
			String commitCount = String.valueOf(cCount);

			model.setUser(user);
			model.setCommitDate(commitDate);
			model.setCommitCount(commitCount);

			commitList.add(model);

			if (commitList.size() == 0) {
				logger.error("There is no Records for particular request on dateBasedCommitDetailsForTeamMembers"
						+ teamName, repoName, timeperiod, endDate);
				throw new ResourceNotFoundException(
						"There is no Records for particular request on dateBasedCommitDetailsForTeamMembers", teamName,
						repoName);
			}
		}

		logger.info("Data processed successfully for dateBasedCommitDetailsForTeamMembers()  " + teamName, repoName,
				timeperiod, endDate);

		return commitList;
	}

	@Override
	public List<ReviewDetailOnDateForMemebers> reviewDetailOnDateForMemebers(String userName, String repoName,
			String commitDate) throws ParseException {
		// TODO Auto-generated method stub
		logger.info("reviewDetailOnDateForMemebers " + userName, repoName, commitDate);
		List<Object[]> reviewDetailsList = new ArrayList<Object[]>();
		ArrayList<ReviewDetailOnDateForMemebers> commitList = new ArrayList<ReviewDetailOnDateForMemebers>();
		List<String> pullNoList = new ArrayList<String>();
		List<String> reviewCommentList = new ArrayList<String>();
		List<String> stateList = new ArrayList<String>();
		List<Timestamp> reviewDateList = new ArrayList<Timestamp>();
		ReviewDetailOnDateForMemebers model = new ReviewDetailOnDateForMemebers();
		// get startDate and endDate
		Date[] inputDates = GitFocusUtil.getStartAndEndDate(commitDate);
		int repoId = uReposRepository.findRepoId(repoName);
		// get commitDetails based on userName, repoId, startDate and endDate
		reviewDetailsList = reviewDetailsRepo.getCommitDetailOnDateForMemebers(userName, repoId, inputDates[0],
				inputDates[1]);
		for (Object[] obj : reviewDetailsList) {
			pullNoList.add((String) String.valueOf(obj[2]));
			reviewCommentList.add((String) obj[3]);
			stateList.add((String) obj[4]);
			reviewDateList.add((Timestamp) obj[1]);
		}

		model.setUserName(userName);
		model.setRepoName(repoName);
		model.setReviewDate(commitDate);
		model.setPullNUmber(pullNoList);
		model.setReviewComment(reviewCommentList);
		model.setState(stateList);
		model.setReviewedAt(reviewDateList);
		commitList.add(model);

		if (commitList.size() == 0) {
			logger.error("There is no Records for particular request on reviewDetailOnDateForMemebers " + userName, repoName, commitDate);

			throw new ResourceNotFoundException("There is no Records for particular request on reviewDetailOnDateForMemebers", userName, repoName);
		}

		logger.info("Data processed successfully for reviewDetailOnDateForMemebers()  " + userName, repoName, commitDate);

		return commitList;
	}
}
