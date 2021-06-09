package com.gitfocus.db.ui.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.db.ui.model.TeamMembersCommitDetailBasedOnPR;
import com.gitfocus.db.ui.service.IPullCommitUIService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.PullCommitRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra
 * Service class for PullCommit Service fetch the data from DB and show in GUI
 */
@Service
public class PullCommitUIServiceImpl implements IPullCommitUIService {

	private static Logger logger = LoggerFactory.getLogger(PullCommitUIServiceImpl.class);

	public PullCommitUIServiceImpl() {
		super();
		logger.info("PullCommitUIServiceImpl init");
	}

	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	CommitDetailsRepository commitRepository;
	@Autowired
	TeamMembersRepository teamMemRepos;
	@Autowired
	PullCommitRepository pullCommitRepository;

	@Override
	public List<TeamMembersCommitDetailBasedOnPR> getCommitDetailsBasedOnPR(int pullNo, String branchName, String repoName,int rownum) throws ParseException {
		logger.info("commitDetailBasedOnPR " + pullNo, branchName, repoName);
		
		int totalLinesAdded = 0;
		int totalLinesRemoved = 0;
		int totalFilesAdded = 0;
		int totalFilesModified = 0;
		String userName = null;
		Timestamp commDate = null;
		String[] linesAddedArr = null;
		String[] linesRemovedArr = null;
		String[] fileStatusArr = null;
		String[] fileNameArr = null;
		String message = null;
		List<Object[]> commitdetails = new ArrayList<Object[]>();
		
		List<TeamMembersCommitDetailBasedOnPR> commitList = new ArrayList<TeamMembersCommitDetailBasedOnPR>();
		TeamMembersCommitDetailBasedOnPR model = new TeamMembersCommitDetailBasedOnPR();
		
		int repoId = uReposRepository.findRepoId(repoName);
		commitdetails = pullCommitRepository.getCommitDetailsBasedOnPR(pullNo, branchName, repoId, rownum);
		for (Object[] obj : commitdetails) {
			userName = (String) obj[0];
			commDate = (Timestamp) obj[1];
			message = ((String) obj[2]);
			String fileStatus = (String) obj[3];
			String linesAdded = (String) obj[4];
			String linedRemoved = (String) obj[5];
			String fileName = (String) obj[6];
			fileNameArr = fileName.split(",");
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
		model.setUserId(userName);
		model.setCommitMessage(message);
		model.setCommitDate(commDate);
		model.setFileStatusArray(fileStatusArr);
		model.setFileNameArray(fileNameArr);
		model.setTotalFileCount(fileNameArr.length);
		model.setLinesAddedArray(linesAddedArr);
		model.setLinesRemovedArray(linesRemovedArr);
		model.setBranchName(branchName);
		model.setTotalFilesAdded(String.valueOf(totalFilesAdded));
		model.setTotalFilesModified(String.valueOf(totalFilesModified));
		model.setTotalLinesAdded(String.valueOf(totalLinesAdded));
		model.setTotalLinesRemoved(String.valueOf(totalLinesRemoved));

		commitList.add(model);

		if (commitList.size() == 0) {
			logger.error("There is no Records for particular request on pullCommitdetailsBasedOnPR" + pullNo, branchName, repoName);
			
			throw new ResourceNotFoundException("There is no Records for particular request on CommitDetailsService",
					branchName, repoName);
		}

		logger.info("Data processed successfully for  pullCommitdetailsBasedOnPR" + pullNo, branchName, repoName);

		return commitList;
	}
}
