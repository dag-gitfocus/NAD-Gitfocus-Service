package com.gitfocus.git.db.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.constants.GitFocusConstants;
import com.gitfocus.git.db.model.CommitDetails;
import com.gitfocus.git.db.model.CommitDetailsCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.ICommitDetailGitService;
import com.gitfocus.repository.BranchDetailsRepository;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra 
 * Service class for CommitDetails and store values in commit_details table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we to
 * append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 * 
 */
@Service
public class CommitDetailGitServiceImpl implements ICommitDetailGitService {

	private static final Logger logger = LoggerFactory.getLogger(CommitDetailGitServiceImpl.class.getSimpleName());

	public CommitDetailGitServiceImpl() {
		super();
		logger.info("CommitDetailServiceImpl init");
	}

	@Autowired
	private GitFocusConstants gitConstant;
	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	private UnitsRepository uRepository;
	@Autowired
	private CommitDetailsRepository cDetailsRepository;
	@Autowired
	private BranchDetailsRepository bDetailsRepository;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	CommitDetailsRepository commitRepository;
	@Autowired
	TeamMembersRepository teamMemRepos;

	List<Units> unitRecords = null;
	List<Units> units = null;
	String jsonResult = null;
	String unitOwner = null;
	int unitId = 0;
	int repoId = 0;
	String commitsResult = null;
	String shaId = null;
	String commitsUri = null;
	JSONArray jsonResponse = null;
	JSONObject commitDetailObj = null;
	JSONObject commitObj1 = null;
	JSONObject commitObj2 = null;
	JSONObject commitObj3 = null;
	String userId = null;
	String commitDetailURI = null;
	String commitDetailShaURI = null;
	String commitDetailShaResult = null;
	JSONObject commitDetailShaObj = null;
	JSONArray commitShaArr = null;
	JSONObject jsonObj = null;
	JSONObject jsonShaObj = null;
	Date cDate = null;
	String commitDate = null;
	String messgae = null;
	List<String> reposName = null;
	List<String> branches = null;
	CommitDetails cDetails = new CommitDetails();
	CommitDetailsCompositeId commitCompositeId = new CommitDetailsCompositeId();

	/**
	 * Method to get Json from Git and store values in DataBase
	 * @return boolean
	 * @throws ParseException
	 */
	@Override
	public boolean save() {
		logger.info("CommitDetailServiceImpl save()");
		boolean result = false;
		units = (List<Units>) uRepository.findAll();
		if (units.isEmpty()) {
			return result;
		}
		units.forEach(response -> {
			unitId = response.getUnitId();
			unitOwner = response.getUnitOwner();
			reposName = uReposRepository.findReposName(unitId);

			reposName.forEach(repoName -> {
				repoId = uReposRepository.findRepoId(repoName);
				
				// get branches for repository
				branches = bDetailsRepository.getBranchList(repoId);

				branches.forEach(branchName -> {
					for (int page = 0; page <= gitConstant.MAX_PAGE; page++) {
						commitDetailURI = gitConstant.BASE_URI + unitOwner + "/" + repoName + "/commits?" + "sha="
								+ branchName + "&page=" + page + "&" + "per_page=" + gitConstant.TOTAL_RECORDS_PER_PAGE + "&";
						
						commitsResult = gitUtil.getGitAPIJsonResponse(commitDetailURI);
						jsonResponse = new JSONArray(commitsResult);

						for (int i = 0; i < jsonResponse.length(); i++) {
							commitDetailObj = jsonResponse.getJSONObject(i);
							
							commitObj1 = commitDetailObj.getJSONObject("commit");
							commitObj2 = commitObj1.getJSONObject("author");
							
							if (commitDetailObj.has("author") && !commitDetailObj.isNull("author")) {
								commitObj3 = commitDetailObj.getJSONObject("author");
								userId = commitObj3.getString("login");
							}
							
							shaId = commitDetailObj.getString("sha");
							commitDate = commitObj2.getString("date");
							cDate = GitFocusUtil.stringToDate(commitDate);
							messgae = commitObj1.getString("message");

							// commit_detail based on sha_id -- START
							if (shaId != null) {

								commitDetailShaURI = gitConstant.BASE_URI + unitOwner + "/" + repoName + "/commits/" + shaId + "?";

								commitDetailShaResult = gitUtil.getGitAPIJsonResponse(commitDetailShaURI);
								commitDetailShaObj = new JSONObject(commitDetailShaResult);
								commitShaArr = commitDetailShaObj.getJSONArray("files");

								String fileName = null;
								String fileStatus = null;
								String linesAdded = null;
								String linesRemoved = null;

								for (int j = 0; j < commitShaArr.length(); j++) {
									jsonShaObj = commitShaArr.getJSONObject(j);
									fileName = fileName + jsonShaObj.getString("filename").concat(",");
									fileStatus = fileStatus + jsonShaObj.getString("status").concat(",");
									linesAdded = linesAdded	+ String.valueOf(jsonShaObj.getInt("additions")).concat(",");
									linesRemoved = linesRemoved	+ String.valueOf(jsonShaObj.getInt("deletions")).concat(",");

									// commit_detail based on sha_id -- END
									
									// store values in commit_details table in database
									commitCompositeId.setUnitId(unitId);
									commitCompositeId.setShaId(shaId);
									commitCompositeId.setRepoId(repoId);
									commitCompositeId.setBranchName(branchName);

									cDetails.setcCompositeId(commitCompositeId);

									cDetails.setCommitDate(cDate);
									cDetails.setUserId(userId);
									cDetails.setMessage(messgae);
									cDetails.setFileName(fileName.replace("null", ""));
									cDetails.setFileStatus(fileStatus.replace("null", ""));
									cDetails.setLinesAdded(linesAdded.replace("null", ""));
									cDetails.setLinesRemoved(linesRemoved.replace("null", ""));

									cDetailsRepository.save(cDetails);

									logger.info("Records saved in commit_details table in DB");
								}
							}
						}
					}
				});
			});
		});
		return true;
	}
}
