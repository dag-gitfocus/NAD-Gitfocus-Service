package com.gitfocus.db.ui.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitfocus.db.ui.service.IListUITeamsService;
import com.gitfocus.exception.ResourceNotFoundException;
import com.gitfocus.git.db.model.Teams;
import com.gitfocus.repository.TeamMembersRepository;
import com.gitfocus.repository.TeamRepoRepository;
import com.gitfocus.repository.TeamsRepository;

/**
 * @author Tech Mahindra 
 * Service class for TeamsService fetch the data from DB and show in GUI
 */

@Service
public class ListTeamsUIServiceImpl implements IListUITeamsService {

	private static final Logger logger = LoggerFactory.getLogger(ListTeamsUIServiceImpl.class.getSimpleName());

	public ListTeamsUIServiceImpl() {
		super();
		logger.info("ListTeamsServiceImpl init");
	}

	@Autowired
	private TeamsRepository teamsRepo;
	@Autowired
	TeamMembersRepository teamMembersRepo;
	@Autowired
	private TeamRepoRepository teamsRepoRepository;

	@Override
	public List<Teams> getAllTeams() {
		// TODO Auto-generated method stub
		logger.info("ListTeamsServiceImpl - getAllTeams");
		List<Teams> teams = teamsRepo.findAll();
		if (teams.size() == 0) { 
			logger.error("There is no Records getAllTeams");
			throw new ResourceNotFoundException("There is no Records for particular request on Teams Service", "team",
					teams);
		}
		return teams;
	}

	@Override
	public List<Object> getTeamMembers(String teamName) {
		// TODO Auto-generated method stub
		logger.info("ListTeamsServiceImpl - getTeamMembers");
		List<Object> teamMembers = teamMembersRepo.getTeamMembersByTeamName(teamName);
		if (teamMembers.size() == 0) {
			logger.error("There is no Records getTeamMembers");
			throw new ResourceNotFoundException("There is no Records for particular request on Teams Service", teamName,
					teamName);
		}
		return teamMembers;
	}

	@Override
	public List<Object> getReposForTeam(String teamName) {
		logger.info("ListTeamsServiceImpl - getReposForTeam");
		List<Object> reposForTeam = teamsRepoRepository.getReposForTeam(teamName);
		if (reposForTeam.size() == 0) {
			logger.error("There is no Records getReposForTeam");
			throw new ResourceNotFoundException("There is no Records for particular request on Teams Service", teamName,
					teamName);
		}
		return reposForTeam;
	}

	@Override
	public List<Object> getTeams(int unitId) {
		logger.info("ListTeamsServiceImpl - getTeams");
		List<Object> teams = teamsRepo.getTeams(unitId);
		if (teams.size() == 0) {
			logger.error("There is no Records getTeams");
			throw new ResourceNotFoundException("There is no Records for particular request on Teams Service", "unitId",
					unitId);
		}
		return teams;
	}

}
