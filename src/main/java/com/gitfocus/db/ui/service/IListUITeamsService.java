package com.gitfocus.db.ui.service;

import java.util.List;

import com.gitfocus.git.db.model.Teams;

/**
 * @author Tech Mahindra
 *
 */
public interface IListUITeamsService {

    /**
     * 
     * @param unitId
     * @return List<Object>
     */
    public List<Object> getTeams(int unitId);

    /**
     * 
     * @param unitId
     * @return List<Object>
     */
    public List<Teams> getAllTeams();

    /**
     * 
     * @param teamId
     * @return List<Object>
     */
    public List<Object> getReposForTeam(String teamName);

    /**
     * 
     * @param teamId
     * @return List<Object>
     */
    public List<Object> getTeamMembers(String teamName);

}
