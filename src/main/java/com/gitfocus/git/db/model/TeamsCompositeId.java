package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra 
 * composite id class for team
 */
@Embeddable
public class TeamsCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamsCompositeId() {
        super();
    }

    @Column(name = "team_id")
    private int teamId;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + teamId;
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
        TeamsCompositeId other = (TeamsCompositeId) obj;
        if (teamId != other.teamId)
            return false;
        return true;
    }

}
