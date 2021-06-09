package com.gitfocus.git.db.impl;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.constants.GitFocusConstants;
import com.gitfocus.git.db.model.BranchDetails;
import com.gitfocus.git.db.model.BranchDetailsCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.IBranchDetailGitService;
import com.gitfocus.repository.BranchDetailsRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra
 * Service class for BranchDetails and store values in branch_details table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we
 * to have to append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 */
@Service
public class BranchDetailGitServiceImpl implements IBranchDetailGitService {

	private static final Logger logger = LoggerFactory.getLogger(BranchDetailGitServiceImpl.class.getSimpleName());

	public BranchDetailGitServiceImpl() {
		super();
		logger.info("BranchDetailServiceImpl init");
	}

	@Autowired
	private UnitsRepository uReposRepository;
	@Autowired
	private GitFocusConstants gitConstant;
	@Autowired
	private BranchDetailsRepository branchRepo;
	@Autowired
	private UnitReposRepository uRepository;
	@Autowired
	GitFocusUtil gitUtil;

	String unitOwner = null;
	List<String> reposName = null;
	int unitId = 0;
	String branchResult = null;
	String branchDetailURI = null;
	JSONArray jsonBranchArray = null;
	JSONObject branchObj = null;
	String branchName = null;
	int repoId = 0;

	BranchDetails branchDetails = new BranchDetails();
	BranchDetailsCompositeId bCompositeId = new BranchDetailsCompositeId();

	/*
	 *  Method to get branch_details and store in branch_details table in DB 
	 */
	@Override
	public boolean save() {
		boolean result = false;
		List<Units> units = uReposRepository.findAll();
		if (units.isEmpty()) {
			return result;
		}
		units.forEach(response -> {
			unitId = response.getUnitId();
			unitOwner = response.getUnitOwner();
			reposName = uRepository.findReposName(unitId);

			reposName.forEach(repoName -> {
				for (int page = 0; page <= gitConstant.MAX_PAGE; page++) {
					branchDetailURI = gitConstant.BASE_URI + unitOwner + "/" + repoName + "/branches?" + "page="
							+ page + "&per_page=" + gitConstant.TOTAL_RECORDS_PER_PAGE + "&";
					branchResult = gitUtil.getGitAPIJsonResponse(branchDetailURI);
					jsonBranchArray = new JSONArray(branchResult);
					try {
						for (int i = 0; i < jsonBranchArray.length(); i++) {
							branchObj = jsonBranchArray.getJSONObject(i);
							branchName = branchObj.getString("name");
							repoId = uRepository.findRepoId(repoName);

							bCompositeId.setUnitId(unitId);
							bCompositeId.setRepoId(repoId);
							bCompositeId.setBranchName(branchName);

							branchDetails.setbCompositeId(bCompositeId);
							//                            branchDetails.setParentBranch(parentBranch);
							branchRepo.save(branchDetails);

							logger.info("Records saved in commit_details table in DB");

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		});
		return true;
	}
}