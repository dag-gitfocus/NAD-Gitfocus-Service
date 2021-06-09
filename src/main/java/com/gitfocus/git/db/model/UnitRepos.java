package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author SP00553980 
 * Model class for unit_repos table in DB
 */
@Entity
@Table(name = "unit_repos", schema = "gitfocus")
public class UnitRepos implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnitRepos() {
        super();
    }

    @JsonIgnore
    @JoinColumn(name = "unit_id", referencedColumnName = "unit_id")

    @EmbeddedId
    UnitReposCompositeId uCompositeId;

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "repo_name")
    private String repoName;

    /**
     * 
     * @return
     */
    public UnitReposCompositeId getuCompositeId() {
        return uCompositeId;
    }

    /**
     * 
     * @param uCompositeId
     */
    public void setuCompositeId(UnitReposCompositeId uCompositeId) {
        this.uCompositeId = uCompositeId;
    }

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
     * @return repoName
     */

    public String getRepoName() {
        return repoName;
    }
    
    /**
     * 
     * @param repoName
     */

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((repoName == null) ? 0 : repoName.hashCode());
        result = prime * result + ((uCompositeId == null) ? 0 : uCompositeId.hashCode());
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
        UnitRepos other = (UnitRepos) obj;
        if (repoName == null) {
            if (other.repoName != null)
                return false;
        } else if (!repoName.equals(other.repoName))
            return false;
        if (uCompositeId == null) {
            if (other.uCompositeId != null)
                return false;
        } else if (!uCompositeId.equals(other.uCompositeId))
            return false;
        return true;
    }

}
