package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra
 * composite id class for PullMaster
 */
@Embeddable 
public class PullMasterCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    

    public PullMasterCompositeId() {
        super();
    }

    @Column(name = "repo_id")
    private int repoId;

    @Column(name = "pull_number")
    private int pullNumber;

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

    /**
     * 
     * @return pullNumber
     */
    public int getPullNumber() {
        return pullNumber;
    }

    /**
     * 
     * @param pullNumber
     */
    public void setPullNumber(int pullNumber) {
        this.pullNumber = pullNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pullNumber;
        result = prime * result + repoId;
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
        PullMasterCompositeId other = (PullMasterCompositeId) obj;
        if (pullNumber != other.pullNumber)
            return false;
        if (repoId != other.repoId)
            return false;
        return true;
    }

}
