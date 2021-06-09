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
import com.gitfocus.git.db.model.ReviewDetails;
import com.gitfocus.git.db.model.ReviewDetailsCompositeId;
import com.gitfocus.git.db.model.Units;
import com.gitfocus.git.db.service.IReviewDetailsGitService;
import com.gitfocus.repository.PullMasterRepository;
import com.gitfocus.repository.ReviewDetailsRepository;
import com.gitfocus.repository.UnitReposRepository;
import com.gitfocus.repository.UnitsRepository;
import com.gitfocus.util.GitFocusUtil;

/**
 * @author Tech Mahindra 
 * Service class for ReviewDetails and store values in review_details table in DB
 * 
 * NOTE : By default GitAPIJson gives max 30 records only for each RestAPI call but for some API have more than 30 records, hence we to
 * have to append page number and totalNoOfRecords/perPage for each URL's to fetch rest of the records
 * 
 */
@Service
public class ReviewDetailsGitServiceImpl implements IReviewDetailsGitService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewDetailsGitServiceImpl.class.getSimpleName());

	public ReviewDetailsGitServiceImpl() {
		super();
		logger.info("ReviewDetailsGitServiceImpl init");
	}

	@Autowired
	private GitFocusConstants gitFocusConstant;
	@Autowired
	private ReviewDetailsRepository reviewRepo;
	@Autowired
	private UnitsRepository unitsRepository;
	@Autowired
	private UnitReposRepository uReposRepository;
	@Autowired
	GitFocusUtil gitUtil;
	@Autowired
	private PullMasterRepository pullMasterRepo;

	int reviewId = 0;
	String reviewResults = null;
	String reviewedBy = null;
	String reviewComment = null;
	String state = null;
	String reviewedAt = null;
	String commitId = null;
	int unitId = 0;
	JSONObject reviewObjUser = null;
	JSONArray reviewJson = null;
	String reviewURI = null;
	boolean result = false;
	String user = null;
	String accessToken = null;
	int repoId = 0;
	JSONObject reviewObj = null;
	List<String> pullNos = null;
	String unitOwner = null;
	List<String> reposName = null;
	List<String> branches = null;
	ReviewDetails rDetails = new ReviewDetails();
	ReviewDetailsCompositeId rDetailsCompositeId = new ReviewDetailsCompositeId();

	@Override
	public boolean save() throws ParseException {
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
				pullNos = pullMasterRepo.findPullNo(repoId);

				pullNos.forEach(reviewPullNo -> {
					for (int page = 0; page <= gitFocusConstant.MAX_PAGE; page++) {
						
						// To get review details based on all the pull history
						reviewURI =  gitFocusConstant.BASE_URI + unitOwner + "/" + repoName
								+ "/pulls/"+reviewPullNo+"/reviews?"+"state=all"+"&" + "page=" + page  + "&per_page=" + gitFocusConstant.TOTAL_RECORDS_PER_PAGE+ "&";
						reviewResults = gitUtil.getGitAPIJsonResponse(reviewURI);

						try {
							reviewJson = new JSONArray(reviewResults);
							for (int i = 0; i < reviewJson.length(); i++) {
								reviewObj = reviewJson.getJSONObject(i);
								reviewObjUser = reviewObj.getJSONObject("user");
								reviewId = reviewObj.getInt("id");
								reviewedBy = reviewObjUser.getString("login");
								reviewComment = reviewObj.getString("body");
								state = reviewObj.getString("state");
								reviewedAt = reviewObj.getString("submitted_at");
								commitId = reviewObj.getString("commit_id");

								rDetailsCompositeId.setReviewId(reviewId);
								rDetails.setrDetailCompositeId(rDetailsCompositeId);

								rDetails.setUnitId(unitId);
								rDetails.setRepoId(repoId);
								rDetails.setPullNumber(Integer.parseInt(reviewPullNo));
								rDetails.setReviewedBy(reviewedBy);
								rDetails.setReviewComment(reviewComment);
								rDetails.setState(state);
								rDetails.setReviewedAt(GitFocusUtil.stringToDate(reviewedAt));
								rDetails.setCommitId(commitId);

								reviewRepo.save(rDetails);

								logger.info("Records saved in review_details table in DB");
							}

						} catch (JSONException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				});
			});
		});
		return true;
	}
}

