package com.gitfocus.db.ui.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.PullMasterCommitDetailOnDate;
import com.gitfocus.db.ui.model.PullRequestCount;

/**
 * @author Tech Mahindra
 *
 */
public interface IPullMasterUIService {

	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @param timeperiod
	 * @param endDate
	 * @return TeamMembersPullDetails
	 */
	List<PullRequestCount> getCountOfPR(String teamName, String repoName, String timeperiod, String endDate)
			throws JsonProcessingException;

	/**
	 * 
	 * @param member
	 * @param date
	 * @return TeamMembersCommitDetailOnDate
	 */
	public List<PullMasterCommitDetailOnDate> pullDetailOnDateForTeamMemeber(String userName, String repoName,
			String commitDate) throws ParseException;

}
