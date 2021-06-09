package com.gitfocus.db.ui.service;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gitfocus.db.ui.model.ReviewDetailOnDateForMemebers;
import com.gitfocus.db.ui.model.ReviewDetailsForTeam;

/**
 * @author Tech Mahindra
 *
 */

public interface IReviewDetailUIService {

	/**
	 * 
	 * @param teamName
	 * @param repoName
	 * @param timeperiod
	 * @param endDate
	 * @return TeamMembersCommitDetails
	 */
	public List<ReviewDetailsForTeam> dateBasedReviewDetailsForTeamMembers(String teamName, String repoName,
			String timeperiod, String endDate) throws JsonProcessingException;

	/**
	 * 
	 * @param userName
	 * @param repoName
	 * @param commitDate
	 * @return reviewDetailOnDateForMemebers
	 * @throws ParseException
	 */

	public List<ReviewDetailOnDateForMemebers> reviewDetailOnDateForMemebers(String userName, String repoName,
			String commitDate) throws ParseException;

}
