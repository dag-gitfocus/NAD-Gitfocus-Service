    package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Tech Mahindra 
 * Model class for teams table in DB
 */
@Entity
@Table(name = "teams", schema = "gitfocus")
public class Teams implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Teams() {
        super();
    }

    @JsonIgnore
    @JoinColumn(name = "unit_id", referencedColumnName = "unit_id")

    @EmbeddedId
    TeamsCompositeId tCompositeId;

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "team_name")
    private String teamName;

    /**
     * 
     * @return
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
     * @return teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * 
     * @return tCompositeId
     */
    public TeamsCompositeId gettCompositeId() {
        return tCompositeId;
    }

    /**
     * 
     * @param tCompositeId
     */
    public void settCompositeId(TeamsCompositeId tCompositeId) {
        this.tCompositeId = tCompositeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tCompositeId == null) ? 0 : tCompositeId.hashCode());
        result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
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
        Teams other = (Teams) obj;
        if (tCompositeId == null) {
            if (other.tCompositeId != null)
                return false;
        } else if (!tCompositeId.equals(other.tCompositeId))
            return false;
        if (teamName == null) {
            if (other.teamName != null)
                return false;
        } else if (!teamName.equals(other.teamName))
            return false;
        return true;
    }

}
