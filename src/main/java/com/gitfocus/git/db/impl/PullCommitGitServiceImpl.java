package com.gitfocus.git.db.impl;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.constants.GitFocusConstants;
import com.gitfocus.git.db.model.PullCommit;
import com.gitfocus.git.db.model.PullCommitCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.IPullCommitGitService;
import com.gitfocus.repository.CommitDetailsRepository;
import com.gitfocus.repository.PullCommitRepository;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra Service class for PullCommit and store values in pull_commit table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we to
 * append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 * 
 */
@Service
public class PullCommitGitServiceImpl implements IPullCommitGitService {

	private static final Logger logger = LoggerFactory.getLogger(PullCommitGitServiceImpl.class.getSimpleName());

	public PullCommitGitServiceImpl() {
		super();
		logger.info("PullCommitServiceImpl init");
	}

	@Autowired
	private UnitReposRepository uRepository;
	@Autowired
	private UnitsRepository uReposRepository;
	@Autowired
	private GitFocusConstants gitConstant;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	private CommitDetailsRepository commitRepo;
	@Autowired
	private PullCommitRepository pullCommitRepo;
	@Autowired
	private PullMasterRepository pullMasterRepo;

	int repoId = 0;
	int unitId = 0;
	String unitOwner = null;
	List<String> reposName = null;
	String pullCommitResult = null;
	String pullCommitURI = null;
	JSONArray jsonArr = null;
	JSONObject jsonObj = null;
	String branchName = null;
	String sha_id = null;
	List<String> branches = null;
	List<String> pullNos = null;
	List<Units> units = null;
	int pNUmber = 0;
	PullCommit pCommit = new PullCommit();
	PullCommitCompositeId pullCompositeId = new PullCommitCompositeId();

	@Override
	public boolean save() throws ParseException {
		// TODO Auto-generated method stub
		boolean result = false;
		units = uReposRepository.findAll();
		if (units.isEmpty()) {
			return result;
		} else if (!units.isEmpty()) {

			units.forEach(response -> {
				unitId = response.getUnitId();
				unitOwner = response.getUnitOwner();
				reposName = uRepository.findReposName(unitId);

				reposName.forEach(repoName -> {
					repoId = uRepository.findRepoId(repoName);

					// get pull numbers for each pull request
					pullNos = pullMasterRepo.findPullNo(repoId);
					pullNos.forEach(pullNo -> {
						for (int page = 0; page <= gitConstant.MAX_PAGE; page++) {
							pullCommitURI = gitConstant.BASE_URI + unitOwner + "/" + repoName
									+ "/pulls/"+pullNo+"/commits?"+"state=all"+"&" + "page=" + page  + "&per_page=" + gitConstant.TOTAL_RECORDS_PER_PAGE+ "&";
							pNUmber = Integer.parseInt(pullNo);

							// sometimes pull number might be 0
							// in that case just ignore the pull request which has pull number 0
							if(pNUmber == 0) {
								continue;
							} else {
								pullCommitResult = gitUtil.getGitAPIJsonResponse(pullCommitURI);
								jsonArr = new JSONArray(pullCommitResult);

								try {
									jsonArr = new JSONArray(pullCommitResult);
									for (int i = 0; i < jsonArr.length(); i++) {
										jsonObj = jsonArr.getJSONObject(i);
										sha_id = jsonObj.getString("sha");
										// get branches based on repoId and sha_id
										// sometimes one branch has multiple repoId and sha_id
										branches = commitRepo.getBranchNameByShaIdAndRepoId(repoId, sha_id);
										branches.forEach(branchName -> {
											repoId = uRepository.findRepoId(repoName);
											pullCompositeId.setRepoId(repoId);
											pullCompositeId.setPullNumber(Integer.parseInt(pullNo));
											pullCompositeId.setCommitId(sha_id);
											pullCompositeId.setBranchName(branchName);

											pCommit.setpCompositeId(pullCompositeId);
											pCommit.setUnitId(unitId);

											pullCommitRepo.save(pCommit);

											logger.info("Records saved in pull_commit table in DB");
										});
									}
								}
								catch (JSONException e) {
									// TODO: handle exception
									e.printStackTrace();
								}
							}
						}
					});
				});
			});
		}
		return true;
	}
}
