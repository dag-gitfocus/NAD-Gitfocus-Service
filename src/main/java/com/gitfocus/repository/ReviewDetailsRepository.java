package com.gitfocus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.ReviewDetails;

/**
 * @author Tech Mahindra Repository class for review_details table in DB
 */

@Repository
public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails, Object> {

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForOneWeek Results
	 */
	@Query(value = "SELECT ur.repo_name,rd.reviewed_by,d,count(rd.reviewed_at) from gitfocus.review_details rd join gitfocus.unit_repos ur on (ur.repo_id=rd.repo_id) RIGHT JOIN generate_series(date_trunc('day', (cast(?3 as timestamp) - interval '6 days' )),date_trunc('day', cast(?3 as timestamp)),'1 day') AS gs(d) ON d = date_trunc('day',rd.reviewed_at) and ur.repo_name=?1 and rd.reviewed_by=?2 group by ur.repo_name,rd.reviewed_by, d order by d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForOneWeek(String repoName, String userId, String endDate);

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForTwoWeek Results
	 */
	@Query(value = "SELECT ur.repo_name,rd.reviewed_by,d,count(rd.reviewed_at) from gitfocus.review_details rd join gitfocus.unit_repos ur on (ur.repo_id=rd.repo_id) RIGHT JOIN generate_series(date_trunc('day', (cast(?3 as timestamp) - interval '13 days' )),date_trunc('day', cast(?3 as timestamp)),'1 day') AS gs(d) ON d = date_trunc('day',rd.reviewed_at) and ur.repo_name=?1 and rd.reviewed_by=?2 group by ur.repo_name,rd.reviewed_by, d order by d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForTwoWeek(String repoName, String userId, String endDate);

	/**
	 * 
	 * @param userId
	 * @param repoId
	 * @param startdate
	 * @param enddate
	 * @return commitDetailOnDateForMemebers
	 */
	@Query("select rd.reviewedBy,rd.reviewedAt,rd.pullNumber,rd.reviewComment,rd.state from ReviewDetails rd \r\n"
			+ "where rd.reviewedBy=:userId \r\n" + "and rd.repoId=:repoId \r\n"
			+ "and rd.reviewedAt >=cast(:startDate as date ) \r\n"
			+ "and rd.reviewedAt <= cast(:endDate as date )  \r\n"
			+ "and rd.pullNumber is not NULL order by rd.reviewedAt")
	List<Object[]> getCommitDetailOnDateForMemebers(String userId, int repoId, Date startDate, Date endDate);

}
