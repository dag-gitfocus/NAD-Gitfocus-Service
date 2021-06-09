package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.Teams;

/**
 * @author Tech Mahindra
 * Repository class for teams table in DB
 *
 */
@Repository
public interface TeamsRepository extends JpaRepository<Teams, Object> {

    /**
     * 
     * @param unitId
     * @return AllTeams
     */
    @Query("select t.tCompositeId.teamId,t.teamName from Teams t where t.unitId=:unitId")
    List<Object> getTeams(int unitId);

}
