package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra 
 * composite id class for commit_details
 */
@Embeddable
public class CommitDetailsCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CommitDetailsCompositeId() {
        super();
    }

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "sha_id")
    private String shaId;

    @Column(name = "repo_id")
    private int repoId;

    @Column(name = "branch_name")
    private String branchName;

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
     * @return shaId
     */
    public String getShaId() {
        return shaId;
    }

    /**
     * 
     * @param shaId
     */
    public void setShaId(String shaId) {
        this.shaId = shaId;
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

    /**
     * 
     * @return branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 
     * @param branchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
        result = prime * result + repoId;
        result = prime * result + ((shaId == null) ? 0 : shaId.hashCode());
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
        CommitDetailsCompositeId other = (CommitDetailsCompositeId) obj;
        if (branchName == null) {
            if (other.branchName != null)
                return false;
        } else if (!branchName.equals(other.branchName))
            return false;
        if (repoId != other.repoId)
            return false;
        if (shaId == null) {
            if (other.shaId != null)
                return false;
        } else if (!shaId.equals(other.shaId))
            return false;
        if (unitId != other.unitId)
            return false;
        return true;
    }

}
