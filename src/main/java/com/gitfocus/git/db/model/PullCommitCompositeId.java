package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra 
 * composite id class for pull_commit
 *
 */
@Embeddable
public class PullCommitCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PullCommitCompositeId() {
        super();
    }

    @Column(name = "repo_id")
    private int repoId;

    @Column(name = "pull_number")
    private int pullNumber;

    @Column(name = "commit_id")
    private String commitId;

    @Column(name = "branch_name")
    private String branchName;

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

    /**
     * 
     * @return commitId
     */
    public String getCommitId() {
        return commitId;
    }

    /**
     * 
     * @param commitId
     */
    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
        result = prime * result + ((commitId == null) ? 0 : commitId.hashCode());
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
        PullCommitCompositeId other = (PullCommitCompositeId) obj;
        if (branchName == null) {
            if (other.branchName != null)
                return false;
        } else if (!branchName.equals(other.branchName))
            return false;
        if (commitId == null) {
            if (other.commitId != null)
                return false;
        } else if (!commitId.equals(other.commitId))
            return false;
        if (pullNumber != other.pullNumber)
            return false;
        if (repoId != other.repoId)
            return false;
        return true;
    }
}