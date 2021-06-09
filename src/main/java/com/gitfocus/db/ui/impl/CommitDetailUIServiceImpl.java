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
import com.gitfocus.db.ui.model.DailyUserCommitList;
import com.gitfocus.db.ui.model.TeamMembersCommitDetail;
import com.gitfocus.db.ui.model.TeamMembersCommitDetailOnDate;
import com.gitfocus.db.ui.model.TeamRepositoryCommitDetails;
import com.gitfocus.db.ui.service.ICommitDetailUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * 
 * @author Tech Mahindra 
 * Service class for CommitDetails fetch the data from DB and show in GUI
 * 
 */
@Service
public class CommitDetailUIServiceImpl implements ICommitDetailUIService {

	private static final Logger logger = LoggerFactory.getLogger(CommitDetailUIServiceImpl.class.getSimpleName());

	public CommitDetailUIServiceImpl() {
		super();
		logger.info("CommitDetailUIServiceImpl init");
	}

	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	CommitDetailsRepository commitRepository;
	@Autowired
	TeamMembersRepository teamMemRepos;

	@Override
	public List<TeamMembersCommitDetail> dateBasedCommitDetailsForTeam(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException {
		// TODO Auto-generated method stub
		logger.info("dateBasedCommitDetailsForTeamMembers " + teamName, repoName, timeperiod, endDate);
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
		List<Object> teamMembers = null;
		List<Object[]> memberCommitList = new ArrayList<Object[]>();
		List<Object[]> memberCommitListResults = new ArrayList<Object[]>();
		ArrayList<TeamMembersCommitDetail> commitList = new ArrayList<TeamMembersCommitDetail>();
		int cCount = 0;

		// get team_memebers based on team_name
		teamMembers = teamMemRepos.getTeamMembersByTeamName(teamName);
		// one week time period of data
		if (timeperiod.equalsIgnoreCase("oneweek")) {
			for (Object userId : teamMembers) {
				memberCommitList = commitRepository.getCommitDetailsForMemberForOneWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberCommitList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberCommitListResults.addAll(memberCommitList);
			}
		}
		// two week time period of data
		if (timeperiod.equalsIgnoreCase("twoweek")) {
			for (Object userId : teamMembers) {
				memberCommitList = commitRepository.getCommitDetailsForMemberForTwoWeek(repoName, userId.toString(),
						endDate);
				for (Object[] obj : memberCommitList) {
					cCount = ((BigInteger) obj[3]).intValue();
					// If commitcount is 0 then userId also 0
					// set userId even if commitcount is 0
					if (cCount == 0) {
						obj[1] = userId.toString();
					}
				}
				memberCommitListResults.addAll(memberCommitList);
			}
		}
		for (Object[] obj : memberCommitListResults) {
			TeamMembersCommitDetail model = new TeamMembersCommitDetail();
			String user = (String) obj[1];
			String commitDate = sd.format(obj[2]);
			cCount = ((BigInteger) obj[3]).intValue();
			String commitCount = String.valueOf(cCount);

			model.setUser(user);
			model.setCommitDate(commitDate);
			model.setCommitCount(commitCount);

			commitList.add(model);

			if (commitList.size() == 0) {
				logger.error("There is no Records for particular request on dateBasedCommitDetailsForTeamMembers "
						+ teamName, repoName, timeperiod, endDate);
				throw new ResourceNotFoundException(
						"There is no Records for particular request on CommitDetailsService", teamName, repoName);
			}
		}

		logger.info("Data processed successfully for dateBasedCommitDetailsForTeamMembers()  " + teamName, repoName,
				timeperiod, endDate);

		return commitList;
	}

	@Override
	public List<TeamMembersCommitDetailOnDate> commitDetailOnDateForMemebers(String userName, String repoName,
			String commitDate) throws ParseException {
		// TODO Auto-generated method stub
		logger.info("commitDetailOnDateForMemebers " + userName, repoName, commitDate);
		int totalLinesAdded = 0;
		int totalLinesRemoved = 0;
		int totalFilesAdded = 0;
		int totalFilesModified = 0;
		String[] linesAddedArr = null;
		String[] linesRemovedArr = null;
		String[] fileStatusArr = null;
		String[] messageArr = null;
		ArrayList<String> msgList = new ArrayList<String>();
		List<Object[]> memberCommitList = new ArrayList<Object[]>();
		ArrayList<TeamMembersCommitDetailOnDate> commitList = new ArrayList<TeamMembersCommitDetailOnDate>();
		TeamMembersCommitDetailOnDate model = new TeamMembersCommitDetailOnDate();
		// get startDate and endDate
		Date[] inputDates = GitFocusUtil.getStartAndEndDate(commitDate);
		int repoId = uReposRepository.findRepoId(repoName);
		// get commitDetails based on userName, repoId, startDate and endDate
		memberCommitList = commitRepository.getCommitDetailOnDateForMemebers(userName, repoId, inputDates[0],
				inputDates[1]);
		for (Object[] obj : memberCommitList) {
			msgList.add((String) obj[2]);
			String fileStatus = (String) obj[3];
			String linesAdded = (String) obj[4];
			String linedRemoved = (String) obj[5];
			fileStatusArr = fileStatus.split(",");
			linesAddedArr = linesAdded.split(",");
			linesRemovedArr = linedRemoved.split(",");

			for (String fileStatusObj : fileStatusArr) {
				if (fileStatusObj.equalsIgnoreCase("modified")) {
					totalFilesModified = totalFilesModified + 1;
				}
				if (fileStatusObj.equalsIgnoreCase("added")) {
					totalFilesAdded = totalFilesAdded + 1;
				}
			}
			for (String linesAddedObj : linesAddedArr) {
				totalLinesAdded = totalLinesAdded + Integer.parseInt(linesAddedObj);
			}
			for (String linesRemovedObj : linesRemovedArr) {
				totalLinesRemoved = totalLinesRemoved + Integer.parseInt(linesRemovedObj);
			}
		}
		model.setUser(userName);
		model.setCommitDate(GitFocusUtil.convertDateToString(inputDates[0]));
		messageArr = msgList.toArray(new String[msgList.size()]);
		model.setCommitMessageArray(messageArr);
		model.setTotalFilesAdded(String.valueOf(totalFilesAdded));
		model.setTotalFilesModified(String.valueOf(totalFilesModified));
		model.setLinesAdded(String.valueOf(totalLinesAdded));
		model.setLinesRemoved(String.valueOf(totalLinesRemoved));

		commitList.add(model);

		if (commitList.size() == 0) {
			logger.error("There is no Records for particular request on commitDetailOnDateForMemebers " + userName,
					repoName, commitDate);
			throw new ResourceNotFoundException("There is no Records for particular request on CommitDetailsService",
					userName, repoName);
		}

		logger.info("Data processed successfully for commitDetailOnDateForMemebers()  " + userName, repoName,
				commitDate);
		return commitList;
	}

	@Override
	public List<DailyUserCommitList> getDailyMemberCommitList(String repoName, String userName, String commitDate) {
		// TODO Auto-generated method stub
		logger.info("getDailyMemberCommitList " + userName, repoName, commitDate);

		List<Object[]> memberCommitList = new ArrayList<Object[]>();
		ArrayList<DailyUserCommitList> commitList = new ArrayList<DailyUserCommitList>();

		// get startDate and endDate
		Date[] inputDates = GitFocusUtil.getStartAndEndDate(commitDate);
		int repoId = uReposRepository.findRepoId(repoName);

		// get commitDetails based on userName, repoId, startDate and endDate
		memberCommitList = commitRepository.getDailyMemberCommitListOnDate(userName, repoId, inputDates[0], inputDates[1]);
		for (Object[] obj : memberCommitList) {
			
			DailyUserCommitList model = new DailyUserCommitList();
			int totalLinesAdded = 0;
			int totalLinesRemoved = 0;
			int totalFilesAdded = 0;
			int totalFilesModified = 0;
			Timestamp cDate = (Timestamp) obj[0];
			String fileName = (String) obj[1];
			String fileStatus = (String) obj[2];
			String linesAdded = (String) obj[3];
			String linesRemoved = (String) obj[4];
			String commitMessage = (String) obj[5];

			String[] fileNameArray = fileName.split(",");
			String[] fileStatusArray = fileStatus.split(",");
			String[] linesAddedArray = linesAdded.split(",");
			String[] linesRemovedArray = linesRemoved.split(",");

			String commDate = cDate.toString().substring(11, 16).replace(':', '.');
			float x = Float.parseFloat(commDate);
			
			for (String fileStatusObj : fileStatusArray) {
				if (fileStatusObj.equalsIgnoreCase("modified")) {
					totalFilesModified = totalFilesModified + 1;
				}
				if (fileStatusObj.equalsIgnoreCase("added")) {
					totalFilesAdded = totalFilesAdded + 1;
				}
			}

			for (String linesAddedObj : linesAddedArray) {
				totalLinesAdded = totalLinesAdded + Integer.parseInt(linesAddedObj);
			}
			for (String linesRemovedObj : linesRemovedArray) {
				totalLinesRemoved = totalLinesRemoved + Integer.parseInt(linesRemovedObj);
			}

			model.setUserId(userName);
			model.setCommitDate(cDate);
			model.setFileNameArray(fileNameArray);
			model.setFileStatusArray(fileStatusArray);
			model.setLinesAddedArray(linesAddedArray);
			model.setLinesRemovedArray(linesRemovedArray);
			model.setTotalFileCount(fileNameArray.length);
			model.setCommitMessage(commitMessage);
			model.setTotalLinesAdded(totalLinesAdded);
			model.setTotalLinesRemoved(totalLinesRemoved);
			model.setTotalFilesAdded(String.valueOf(totalFilesAdded));
			model.setTotalFilesModified(String.valueOf(totalFilesModified));
			model.setX(x);

			commitList.add(model);
		}

		if (commitList.size() == 0) {
			logger.error("There is no Records for particular request on commitDetailOnDateForMemebers " + userName, repoName, commitDate);
			throw new ResourceNotFoundException("There is no Records for particular request on CommitDetailsService", userName, repoName);
		}

		logger.info("Data processed successfully for commitDetailOnDateForMemebers()  " + userName, repoName, commitDate);

		return commitList;
	}

	/**
	 * As of now this getCommitDetailsTeamAndRepos() not used, keeping here for
	 * future requirement
	 */
	@Override
	public TeamRepositoryCommitDetails getCommitDetailsTeamAndRepos(String teamName, String repoName) {
		// TODO Auto-generated method stub
		logger.info("getCommitDetailsTeamAndRepos " + teamName, repoName);
		TeamRepositoryCommitDetails repoCommitDetails = new TeamRepositoryCommitDetails();
		List<Object[]> teamRepoCommitList = null;
		int repoId = 0;
		int codeCommits = 0;
		String[] linesAddedArr = null;
		String[] linesRemovedArr = null;
		String[] fileStatusArr = null;
		int totalLinesDeleted = 0;
		int totalLinesAdded = 0;
		int totalFilesAdded = 0;
		int totalFilesModified = 0;
		int totalFilesDeleted = 0;
		int totalFilesRenamed = 0;
		String fileStatus = null;
		String totalNoOfLinesAdded = null;
		String totalNoOfLinesRemoved = null;

		repoId = uReposRepository.findRepoId(repoName);
		teamRepoCommitList = commitRepository.getCommitDetailTeamAndRepos(repoId, teamName, repoName);
		codeCommits = teamRepoCommitList.size();

		for (Object[] obj : teamRepoCommitList) {

			repoCommitDetails.getUserId().add((String) obj[0]);
			repoCommitDetails.getMessage().add((String) obj[1]);

			fileStatus = (String) obj[2];
			totalNoOfLinesAdded = (String) obj[3];
			totalNoOfLinesRemoved = (String) obj[4];

			linesAddedArr = totalNoOfLinesAdded.split(",");
			linesRemovedArr = totalNoOfLinesRemoved.split(",");
			fileStatusArr = fileStatus.split(",");

			for (String fileStatusObj : fileStatusArr) {

				if (fileStatusObj.equalsIgnoreCase("modified")) {
					totalFilesModified = totalFilesModified + 1;
				} else if (fileStatusObj.equalsIgnoreCase("added")) {
					totalFilesAdded = totalFilesAdded + 1;
				} else if (fileStatusObj.equalsIgnoreCase("removed")) {
					totalFilesDeleted = totalFilesDeleted + 1;
				} else if (fileStatusObj.equalsIgnoreCase("renamed")) {
					totalFilesRenamed = totalFilesRenamed + 1;
				}
			}
			for (String linesAddedObj : linesAddedArr) {
				totalLinesAdded = totalLinesAdded + Integer.parseInt(linesAddedObj);
			}
			for (String linesRemovedObj : linesRemovedArr) {
				totalLinesDeleted = totalLinesDeleted + Integer.parseInt(linesRemovedObj);
			}

		}

		repoCommitDetails.setTotalCommits(codeCommits);

		repoCommitDetails.getCommitLables().add("Total Files Added");
		repoCommitDetails.getCommitData().add(totalFilesAdded);
		repoCommitDetails.getCommitLables().add("Total Files Deleted");
		repoCommitDetails.getCommitData().add(totalFilesDeleted);
		repoCommitDetails.getCommitLables().add("Total Files Modified");
		repoCommitDetails.getCommitData().add(totalFilesModified);
		repoCommitDetails.getCommitLables().add("Total Files Renamed");
		repoCommitDetails.getCommitData().add(totalFilesRenamed);

		repoCommitDetails.getCommitLables1().add("Total Lines Added");
		repoCommitDetails.getCommitData1().add(totalLinesAdded);
		repoCommitDetails.getCommitLables1().add("Total Lines Deleted");
		repoCommitDetails.getCommitData1().add(totalLinesDeleted);

		if (teamRepoCommitList.size() == 0) {
			logger.error("There is no Records for particular request on CommitDetailsService " + teamName, repoName);
			throw new ResourceNotFoundException("There is no Records for particular request on CommitDetailsService",
					teamName, repoName);
		}
		logger.info("Data processed successfully for getCommitDetailsTeamAndRepos()  " + teamName, repoName);
		return repoCommitDetails;
	}

}
