package com.gitfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gitfocus.git.db.model.TeamMembers;

/**
 * @author Tech Mahindra
 * Repository class for team_members table in DB
 *
 */
@Repository
public interface TeamMembersRepository extends JpaRepository<TeamMembers, String> {

    /**
     * 
     * @param teamName
     * @return TeamMembersByTeamName
     */
    @Query("select t.tcompositeId.members from TeamMembers t inner join Teams s on t.tcompositeId.teamId=s.tCompositeId.teamId where s.teamName=:teamName")
    List<Object> getTeamMembersByTeamName(String teamName);

}
