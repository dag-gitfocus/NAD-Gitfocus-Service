package com.gitfocus.db.ui.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gitfocus.db.ui.model.TeamMembersCommitDetailBasedOnPR;

/**
 * @author Tech Mahindra
 *
 */

@Repository
public interface IPullCommitUIService {

	public List<TeamMembersCommitDetailBasedOnPR> getCommitDetailsBasedOnPR(int pullNo, String branchName, String repoName,int rownum) throws ParseException;

}
