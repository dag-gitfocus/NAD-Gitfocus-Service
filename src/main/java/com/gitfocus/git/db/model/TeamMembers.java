package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Tech Mahindra 
 * Model class for team_members table in DB
 */

@Entity
@Table(name = "team_members", schema = "gitfocus")
public class TeamMembers implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamMembers() {
        super();
    }

    @JsonIgnore
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")

    @EmbeddedId
    TeamMembersCompositeId tcompositeId;

    /**
     * 
     * @return tcompositeId
     */
    public TeamMembersCompositeId getTcompositeId() {
        return tcompositeId;
    }

    /**
     * 
     * @param tcompositeId
     */
    public void setTcompositeId(TeamMembersCompositeId tcompositeId) {
        this.tcompositeId = tcompositeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tcompositeId == null) ? 0 : tcompositeId.hashCode());
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
        TeamMembers other = (TeamMembers) obj;
        if (tcompositeId == null) {
            if (other.tcompositeId != null)
                return false;
        } else if (!tcompositeId.equals(other.tcompositeId))
            return false;
        return true;
    }

}
