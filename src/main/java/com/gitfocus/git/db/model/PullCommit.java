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
 * Model class for pull_commit table in DB
 */
@Entity
@Table(name = "pull_commit", schema = "gitfocus")
public class PullCommit implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PullCommit() {
        super();
    }

    @JsonIgnore
    @JoinColumns({

            @JoinColumn(name = "repo_id", referencedColumnName = "repo_id"),
            @JoinColumn(name = "pull_number", referencedColumnName = "pull_number"),
            @JoinColumn(name = "commit_id", referencedColumnName = "commit_id"),
            @JoinColumn(name = "branch_name", referencedColumnName = "branch_name") })

    @EmbeddedId
    PullCommitCompositeId pCompositeId;

    @Column(name = "unit_id")
    private int unitId;

    /**
     * 
     * @return pCompositeId
     */
    public PullCommitCompositeId getpCompositeId() {
        return pCompositeId;
    }

    /**
     * 
     * @param pCompositeId
     */
    public void setpCompositeId(PullCommitCompositeId pCompositeId) {
        this.pCompositeId = pCompositeId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pCompositeId == null) ? 0 : pCompositeId.hashCode());
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
        PullCommit other = (PullCommit) obj;
        if (pCompositeId == null) {
            if (other.pCompositeId != null)
                return false;
        } else if (!pCompositeId.equals(other.pCompositeId))
            return false;
        if (unitId != other.unitId)
            return false;
        return true;
    }
}