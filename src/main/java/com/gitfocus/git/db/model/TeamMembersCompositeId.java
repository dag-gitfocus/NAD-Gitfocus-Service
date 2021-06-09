package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Team Mahindra 
 * composite id class for team_members
 */
@Embeddable
public class TeamMembersCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamMembersCompositeId() {
        super();
    }

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "members")
    private String members;

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
     * @return members
     */
    public String getMembers() {
        return members;
    }

    /**
     * 
     * @param members
     */
    public void setMembers(String members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((members == null) ? 0 : members.hashCode());
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
        TeamMembersCompositeId other = (TeamMembersCompositeId) obj;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        if (teamId != other.teamId)
            return false;
        return true;
    }

}
