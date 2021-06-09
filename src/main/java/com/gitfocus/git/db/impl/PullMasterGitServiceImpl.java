package com.gitfocus.git.db.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.constants.GitFocusConstants;
import com.gitfocus.git.db.model.PullMaster;
import com.gitfocus.git.db.model.PullMasterCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.IPullMasterGitService;
import com.gitfocus.repository.BranchDetailsRepository;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra 
 * Service class for PullMaster and store values in pull_master table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we to
 * have to append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 * 
 */
@Service
public class PullMasterGitServiceImpl implements IPullMasterGitService {

	private static final Logger logger = LoggerFactory.getLogger(PullMasterGitServiceImpl.class.getSimpleName());

	public PullMasterGitServiceImpl() {
		super();
		logger.info("PullMasterServiceImpl init");
	}

	@Autowired
	private UnitsRepository unitsRepository;
	@Autowired
	private GitFocusConstants gitConstant;
	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	private BranchDetailsRepository branchRepo;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	PullMasterRepository pMasterRepository;
	@Autowired
	private GitFocusConstants gitFocusConstant;
	@Autowired
	TeamMembersRepository teamMemRepos;

	String pullResults = null;
	String pullMasterURI = null;
	String fromBranch = null;
	String toBranch = null;
	String userId = null;
	String unitOwner = null;
	List<String> reposName = null;
	String pullState = null;
	int commitCount = 0;
	boolean merged = false;
	String mergedBy = null;
	Object mergedByNull = null;
	Date closedAt = null;
	Date mergAt = null;
	JSONObject mergBy = null;
	String pullNoResults = null;
	int unitId = 0;
	int pullNo = 0;
	Date creTime = null;
	Date updTime = null;
	String pullNoUri = null;
	int pullId = 0;
	String user = null;
	int repoId = 0;
	Object cTime = null;
	Object mTime = null;
	String pullsResult = null;
	JSONObject pullNoObjJson = null;
	JSONObject pullObj = null;
	JSONObject pullNoObj = null;
	JSONObject pullObjHead = null;
	JSONObject pullObjBase = null;
	JSONObject pullObjUserId = null;
	String createdTime = null;
	String updatedTime = null;
	JSONArray jsonPullsArray = null;
	boolean result = false;
	List<String> branches = null;

	PullMasterCompositeId pullCompositeId = new PullMasterCompositeId();
	PullMaster pMaster = new PullMaster();

	/*
	 * Method to get all the pull request info and pull request based on pull number
	 */
	@Override
	public boolean save() {
		// TODO Auto-generated method stub

		List<Units> units = (List<Units>) unitsRepository.findAll();
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
				branches = branchRepo.getBranchList(repoId);

				branches.forEach(branchName -> {
					for (int page = 0; page <= gitConstant.MAX_PAGE; page++) {
						// To get Pull review based on all the pull history
						pullMasterURI = gitFocusConstant.BASE_URI + unitOwner + "/" + repoName + "/pulls?" + "state=all"
								+ "&" + "page=" + page + "&per_page=" + gitFocusConstant.TOTAL_RECORDS_PER_PAGE + "&";

						pullsResult = gitUtil.getGitAPIJsonResponse(pullMasterURI);
						jsonPullsArray = new JSONArray(pullsResult);

						for (int i = 0; i < jsonPullsArray.length(); i++) {

							pullObj = jsonPullsArray.getJSONObject(i);
							pullObjHead = pullObj.getJSONObject("head");
							pullObjBase = pullObj.getJSONObject("base");
							pullObjUserId = pullObj.getJSONObject("user");

							pullNo = pullObj.getInt("number");

							pullCompositeId.setRepoId(repoId);
							pullCompositeId.setPullNumber(pullNo);

							pMaster.setPullMasterCompositeId(pullCompositeId);

							pMaster.setUnitId(unitId);
							pullId = pullObj.getInt("id");
							fromBranch = pullObjHead.getString("ref");
							toBranch = pullObjBase.getString("ref");
							createdTime = pullObj.getString("created_at");
							updatedTime = pullObj.getString("updated_at");
							creTime = GitFocusUtil.stringToDate(createdTime);
							pullState = pullObj.getString("state");
							updTime = GitFocusUtil.stringToDate(updatedTime);
							userId = pullObjUserId.getString("login");

							// To get Pull review based on pull number -- START

							pullNoUri = gitFocusConstant.BASE_URI + unitOwner + "/" + repoName + "/pulls/" + pullNo
									+ "?" + "state=all" + "&" + "page=" + page + "&per_page="
									+ gitFocusConstant.TOTAL_RECORDS_PER_PAGE + "&";

							pullNoResults = gitUtil.getGitAPIJsonResponse(pullNoUri);
							pullNoObjJson = new JSONObject(pullNoResults);

							commitCount = pullNoObjJson.getInt("commits");
							merged = pullNoObjJson.getBoolean("merged");
							cTime = pullNoObjJson.get("closed_at");
							mTime = pullNoObjJson.get("merged_at");

							// merged_by
							Object mergValue = pullNoObjJson.get("merged_by");
							if (mergValue instanceof JSONObject) {
								mergBy = pullNoObjJson.getJSONObject("merged_by");
								mergedBy = mergBy.getString("login");
								pMaster.setMergedBy(mergedBy);
							} else {
								mergedByNull = pullNoObjJson.get("merged_by");
								pMaster.setMergedBy(String.valueOf(mergedByNull));
							}
							// closed_at
							if (!cTime.equals(null)) {
								closedAt = GitFocusUtil.stringToDate(String.valueOf(cTime));
								pMaster.setClosedAt(closedAt);
							} else {
								pMaster.setClosedAt(null);
							}
							// merged_at
							if (!mTime.equals(null)) {
								mergAt = GitFocusUtil.stringToDate(String.valueOf(mTime));
								pMaster.setMergedAt(mergAt);
							} else {
								pMaster.setMergedAt(null);
							}

							// To get Pull review based on pull number -- END

							pMaster.setPullId(pullId);
							pMaster.setFromBranch(fromBranch);
							pMaster.setToBranch(toBranch); 
							pMaster.setCreatedTime(creTime);
							pMaster.setPullStatus(pullState);
							pMaster.setUserId(userId);
							pMaster.setUpdatedTime(updTime);
							pMaster.setCommitCount(commitCount);
							pMaster.setMerged(merged);
							pMaster.setClosedAt(closedAt);

							pMasterRepository.save(pMaster);

							logger.info("Records saved in PullMaster table in DB ");
						}
					}
				});
			});
		});
		return true;
	}
}
