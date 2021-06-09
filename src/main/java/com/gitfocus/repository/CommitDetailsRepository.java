package com.gitfocus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.CommitDetails;

/**
 * @author Tech Mahindra 
 * Repository class for commit_details table in DB
 */
@Repository
public interface CommitDetailsRepository extends JpaRepository<CommitDetails, Object> {
	/**
	 * 
	 * @param repoId
	 * @param teamName
	 * @param repoName
	 * @return CommitDetailTeamAndRepos
	 */

	@Query("select cd.userId,cd.message,cd.fileStatus,cd.linesAdded,cd.linesRemoved from CommitDetails cd "
			+ "inner join UnitRepos ur on cd.cCompositeId.repoId=ur.uCompositeId.repoId inner join Teams ts on ur.unitId=ts.unitId where ts.teamName=:teamName and ur.repoName=:repoName "
			+ "and cd.cCompositeId.repoId=:repoId")
	List<Object[]> getCommitDetailTeamAndRepos(int repoId, String teamName, String repoName);

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForTwoWeek Results
	 */
	@Query(value = "select res.reponame,res.userid,res.d,count(res.cdate) from \r\n" 
	        +"(SELECT ur.repo_name as reponame ,cd.user_id as userid,d,cd.commit_date as cdate from gitfocus.commit_details\r\n"
			+ "cd join gitfocus.branch_details bd ON (cd.repo_id=bd.repo_id and cd.branch_name=bd.branch_name)\r\n"
			+ "join gitfocus.unit_repos ur on (ur.repo_id=bd.repo_id) RIGHT JOIN generate_series( \r\n"
			+ "date_trunc('day', (cast(?3 as timestamp) - interval '13 days' )), \r\n"
			+ "date_trunc('day', cast(?3 as timestamp)),'1 day' \r\n"
			+ ") AS gs(d) ON d = date_trunc('day',cd.commit_date) and \r\n"
			+ "ur.repo_name=?1 and cd.user_id=?2 group by ur.repo_name,cd.user_id, d,cd.commit_date ) as res\r\n" 
			+ "group by res.reponame,res.userid,res.d order by res.d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForTwoWeek(String repoName, String userId, String endDate);

	/**
	 * 
	 * @param member
	 * @param repoName
	 * @param endDate
	 * @return getCommitDetailsForMemberForOneWeek Results
	 */
	@Query(value = "select res.reponame,res.userid,res.d,count(res.cdate) from \r\n" 
	        +"(SELECT distinct ur.repo_name as reponame ,cd.user_id as userid,d,cd.commit_date as cdate from gitfocus.commit_details\r\n"
			+ "cd join gitfocus.branch_details bd ON (cd.repo_id=bd.repo_id and cd.branch_name=bd.branch_name)\r\n"
			+ "join gitfocus.unit_repos ur on (ur.repo_id=bd.repo_id) RIGHT JOIN generate_series( \r\n"
			+ "date_trunc('day', (cast(?3 as timestamp) - interval '6 days' )), \r\n"
			+ "date_trunc('day', cast(?3 as timestamp)),'1 day' \r\n"
			+ ") AS gs(d) ON d = date_trunc('day',cd.commit_date) and \r\n"
			+ "ur.repo_name=?1 and cd.user_id=?2 group by ur.repo_name,cd.user_id, d,cd.commit_date) as res\r\n" 
			+ "group by res.reponame,res.userid,res.d order by res.d", nativeQuery = true)
	List<Object[]> getCommitDetailsForMemberForOneWeek(String repoName, String userId, String endDate);

	/**
	 * 
	 * @param userId
	 * @param repoId
	 * @param startdate
	 * @param enddate
	 * @return commitDetailOnDateForMemebers
	 */
	@Query("select distinct cm.userId,cm.commitDate,cm.message,cm.fileStatus,cm.linesAdded,cm.linesRemoved,cm.fileName from CommitDetails cm \r\n"
			+ "where cm.userId=:userId \r\n" + "and cm.cCompositeId.repoId=:repoId \r\n"
			+ "and cm.commitDate >=cast(:startDate as date ) \r\n"
			+ "and cm.commitDate <= cast(:endDate as date )  \r\n"
			+ "and cm.fileName is not NULL  order by cm.commitDate")
	List<Object[]> getCommitDetailOnDateForMemebers(String userId, int repoId, Date startDate, Date endDate);

	/**
	 * 
	 * @param repoId
	 * @param commitId
	 * @return getBranchNameByShaIdAndRepoId
	 */
	@Query("select cd.cCompositeId.branchName from CommitDetails cd where cd.cCompositeId.repoId=:repoId "
			+ "and cd.cCompositeId.shaId=:commitId")
	List<String> getBranchNameByShaIdAndRepoId(int repoId, String commitId);

	/**
	 * 
	 * @param userName
	 * @param repoId
	 * @param date
	 * @param date2
	 * @return getDailyMemberCommitListOnDate
	 */
	@Query("select distinct cd.commitDate, cd.fileName, cd.fileStatus, cd.linesAdded, cd.linesRemoved, cd.message from CommitDetails cd where cd.userId=:userName "
			+ "and cd.cCompositeId.repoId=:repoId and cd.commitDate >=cast(:startDate as date ) and cd.commitDate <= cast(:endDate as date)"
			+ "order by cd.commitDate")
	List<Object[]> getDailyMemberCommitListOnDate(String userName, int repoId, Date startDate, Date endDate);

}
