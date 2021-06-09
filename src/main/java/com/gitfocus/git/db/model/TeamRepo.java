package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Tech Mahindra 
 * Model class for team_repo table in DB
 */
@Entity
@Table(name = "team_repo", schema = "gitfocus")
public class TeamRepo implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamRepo() {
        super();
    }

    @JsonIgnore
    @JoinColumns({@JoinColumn(name = "team_id", referencedColumnName = "team_id"),
            @JoinColumn(name = "unit_id", referencedColumnName = "unit_id") })

    @EmbeddedId
    TeamRepoCompositeId tCompositeId;
    
    @Column(name="role")
    private String role;

    /**
     * 
     * @return tCompositeId
     */
    public TeamRepoCompositeId gettCompositeId() {
        return tCompositeId;
    }

    /**
     * 
     * @param tCompositeId
     */
    public void settCompositeId(TeamRepoCompositeId tCompositeId) {
        this.tCompositeId = tCompositeId;
    }

    /**
     * 
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((tCompositeId == null) ? 0 : tCompositeId.hashCode());
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
        TeamRepo other = (TeamRepo) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (tCompositeId == null) {
            if (other.tCompositeId != null)
                return false;
        } else if (!tCompositeId.equals(other.tCompositeId))
            return false;
        return true;
    }
}
