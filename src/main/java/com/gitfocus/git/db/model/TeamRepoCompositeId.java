package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra 
 * composite id class for team_repo
 */
@Embeddable
public class TeamRepoCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamRepoCompositeId() {
        super();
    }

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "repo_id")
    private int repoId;

    /**
     * 
     * @return unitId
     */
    public int getUnitId() {
        return unitId;
    }

    /**
     * 
     * @param unitId
     */
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    /**
     * 
     * @return teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * 
     * @param teamId
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    /**
     * 
     * @return repoId
     */
    public int getRepoId() {
        return repoId;
    }

    /**
     * 
     * @param repoId
     */
    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + repoId;
        result = prime * result + teamId;
        result = prime * result + unitId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeamRepoCompositeId other = (TeamRepoCompositeId) obj;
        if (repoId != other.repoId)
            return false;
        if (teamId != other.teamId)
            return false;
        if (unitId != other.unitId)
            return false;
        return true;
    }

}
