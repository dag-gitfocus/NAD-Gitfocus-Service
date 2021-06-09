package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.UnitRepos;

/**
 * @author Tech Mahindra 
 * Repository class for unit_repos table in DB
 */
@Repository
public interface UnitReposRepository extends JpaRepository<UnitRepos, Object> {

    /**
     * 
     * @param unitId
     * @return ReposName
     */
    @Query("select u.repoName from UnitRepos u where u.unitId=:unitId")
    List<String> findReposName(int unitId);

    /**
     * 
     * @param repoName
     * @return RepoId
     */
    @Query("select  u.uCompositeId.repoId from UnitRepos u where u.repoName=:repoName")
    int findRepoId(String repoName);

    /**
     * 
     * @param repoId
     * @return ReposNamebyRepoId
     */
    @Query("select u.repoName from UnitRepos u where u.uCompositeId.repoId=:repoId")
    String findReposNamebyRepoId(int repoId);
}
