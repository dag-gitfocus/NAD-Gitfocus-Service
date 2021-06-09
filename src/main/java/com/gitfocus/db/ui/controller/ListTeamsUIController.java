package com.gitfocus.db.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gitfocus.db.ui.service.IListUITeamsService;
import com.gitfocus.git.db.model.Teams;

/**
 * @author Tech Mahindra 
 * To list no of Teams and corresponding repository for each Teams from database to GUI
 */
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class ListTeamsUIController {
	//private final HttpServletRequest request;
	private static final Logger logger = LoggerFactory.getLogger(ListTeamsUIController.class);

	public ListTeamsUIController() {
		super();
		//this.request = null;
		logger.info("ListTeamsController init");
	}

	@Autowired
	private IListUITeamsService teamRepo;

	/**
	 * 
	 * @param unitId
	 * @return team
	 */
	@GetMapping("/gitfocus/getTeams/{unitId}")
	public List<Object> getTeams(@PathVariable("unitId") int unitId) {
		List<Object> team = teamRepo.getTeams(unitId);
		logger.info("getTeams Records " + team);
		return team;
	}

	/**
	 * 
	 * @param unitId
	 * @return team
	 */
	@GetMapping("/gitfocus/getAllTeams")
	public List<Teams> getAllTeams() {
		List<Teams> team = teamRepo.getAllTeams();
		logger.info("getAllTeams Records " + team);
		return team;
	}

	/**
	 * 
	 * @param teamId
	 * @return listOfTeamMembers
	 */
	@GetMapping("/gitfocus/getTeamMembers/{teamName}")
	public List<Object> getTeamMembers(@PathVariable("teamName") String teamName) {
		List<Object> listOfTeamMembers = teamRepo.getTeamMembers(teamName);
		logger.info("getTeamMembers Records " + listOfTeamMembers);
		return listOfTeamMembers;
	}

	/**
	 * 
	 * @param teamId
	 * @return repo
	 */
	@GetMapping("/gitfocus/getRepoNames/{teamName}")
	public List<Object> getRepoByTeam(@PathVariable("teamName") String teamId) {
		List<Object> repoByTeam = teamRepo.getReposForTeam(teamId);
		logger.info("getRepoByTeam Records " + repoByTeam);
		return repoByTeam;
	}

//	private KeycloakSecurityContext getKeycloakSecurityContext() {
//		return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
//	}
}
