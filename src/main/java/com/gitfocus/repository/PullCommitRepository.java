package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.PullCommit;

/**
 * @author Tech Mahindra
 *
 */
@Repository
public interface PullCommitRepository extends JpaRepository<PullCommit, Object> {

	/**
	 * 
	 * @param pullNo
	 * @param branchName
	 * @param repoId
	 * @return getCommitDetailsBasedOnPR
	 */
	@Query(value = "select * from (select cm.user_id,cm.commit_date,cm.message,cm.file_status,cm.lines_added,cm.lines_removed,cm.file_name,cm.branch_name,\r\n" + 
			"			ROW_NUMBER () OVER (ORDER BY commit_date)\r\n" + 
			"			from gitfocus.commit_details cm\r\n" + 
			"			JOIN gitfocus.pull_commit pc ON cm.sha_id = pc.commit_id\r\n" + 
			"			AND cm.branch_name = pc.branch_name\r\n" + 
			"			AND cm.repo_id = pc.repo_id\r\n" + 
			"			where pc.pull_number= ?1\r\n" + 
			"			and pc.branch_name= ?2\r\n" + 
			"			and pc.repo_id = ?3\r\n" + 
			"			) x where row_number = ?4", nativeQuery = true)
	List<Object[]> getCommitDetailsBasedOnPR(int pullNo, String branchName, int repoId, int rownum);

}
