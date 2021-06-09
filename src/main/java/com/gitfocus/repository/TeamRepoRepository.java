package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.TeamRepo;

/**
 * @author Tech Mahindra 
 * Repository class for team_repo table in DB
 */
@Repository
public interface TeamRepoRepository extends JpaRepository<TeamRepo, Object> {

    /**
     * 
     * @param teamName
     * @return ReposForTeam
     */
    @Query("select u.repoName from UnitRepos u inner join TeamRepo t on u.uCompositeId.repoId=t.tCompositeId.repoId inner join \r\n"
            + "Teams s on s.tCompositeId.teamId=t.tCompositeId.teamId where s.teamName=:teamName")
    List<Object> getReposForTeam(String teamName);

}
