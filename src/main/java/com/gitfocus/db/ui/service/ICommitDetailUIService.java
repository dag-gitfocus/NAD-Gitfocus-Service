package com.gitfocus.db.ui.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.DailyUserCommitList;
import com.gitfocus.db.ui.model.TeamMembersCommitDetail;
import com.gitfocus.db.ui.model.TeamMembersCommitDetailOnDate;
import com.gitfocus.db.ui.model.TeamRepositoryCommitDetails;

/**
 * @author Tech Mahindra
 *
 */
public interface ICommitDetailUIService {
	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @return TeamRepositoryCommitDetails
	 */
	public TeamRepositoryCommitDetails getCommitDetailsTeamAndRepos(String teamName, String repoName);

	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @param timeperiod
	 * @param endDate
	 * @return TeamMembersCommitDetails
	 */
	public List<TeamMembersCommitDetail> dateBasedCommitDetailsForTeam(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException;

	/**
	 * 
	 * @param member
	 * @param date
	 * @return TeamMembersCommitDetailOnDate
	 */
	public List<TeamMembersCommitDetailOnDate> commitDetailOnDateForMemebers(String userName, String repoName,
			String commitDate) throws ParseException;

	/**
	 * 
	 * @param userName
	 * @param repoName
	 * @param commitDate
	 * @return getDailyUserCommitList
	 */
	public List<DailyUserCommitList> getDailyMemberCommitList(String userName, String repoName, String commitDate);

}
