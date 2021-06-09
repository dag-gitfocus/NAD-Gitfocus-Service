package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra 
 * composite id class for unit_repos table
 */
@Embeddable
public class UnitReposCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UnitReposCompositeId() {
        super();
    }

    @Column(name = "repo_id")
    private int repoId;

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
        UnitReposCompositeId other = (UnitReposCompositeId) obj;
        if (repoId != other.repoId)
            return false;
        return true;
    }

}
