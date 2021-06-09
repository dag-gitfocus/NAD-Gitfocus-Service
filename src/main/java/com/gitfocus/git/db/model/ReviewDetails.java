package com.gitfocus.git.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Tech Mahindra Model class for review_details table in DB
 */
@Entity
@Table(name = "review_details", schema = "gitfocus")
public class ReviewDetails implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ReviewDetails() {
        super();
    }

    @JsonIgnore
    @JoinColumns({ @JoinColumn(name = "review_id", referencedColumnName = "review_id")})

    @Id
    ReviewDetailsCompositeId rDetailCompositeId;

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "repo_id")
    private int repoId;

    @Column(name = "pull_number")
    private int pullNumber;

    @Column(name = "reviewed_by")
    private String reviewedBy;

    @Column(name = "review_comment")
    private String reviewComment;

    @Column(name = "state")
    private String state;

    @Column(name = "reviewed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedAt;

    @Column(name = "commit_id")
    private String commitId;

    /**
     * 
     * @return rDetailCompositeId
     */
    public ReviewDetailsCompositeId getrDetailCompositeId() {
        return rDetailCompositeId;
    }

    /**
     * 
     * @param rDetailCompositeId
     */
    public void setrDetailCompositeId(ReviewDetailsCompositeId rDetailCompositeId) {
        this.rDetailCompositeId = rDetailCompositeId;
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
     * @return reviewedBy
     */
    public String getReviewedBy() {
        return reviewedBy;
    }

    /**
     * 
     * @param reviewedBy
     */
    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    /**
     * 
     * @return reviewComment
     */
    public String getReviewComment() {
        return reviewComment;
    }

    /**
     * 
     * @param reviewComment
     */
    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    /**
     * 
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return reviewedAt
     */
    public Date getReviewedAt() {
        return reviewedAt;
    }

    /**
     * 
     * @param reviewedAt
     */
    public void setReviewedAt(Date reviewedAt) {
        this.reviewedAt = reviewedAt;
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
        result = prime * result + ((commitId == null) ? 0 : commitId.hashCode());
        result = prime * result + pullNumber;
        result = prime * result + ((rDetailCompositeId == null) ? 0 : rDetailCompositeId.hashCode());
        result = prime * result + repoId;
        result = prime * result + ((reviewComment == null) ? 0 : reviewComment.hashCode());
        result = prime * result + ((reviewedAt == null) ? 0 : reviewedAt.hashCode());
        result = prime * result + ((reviewedBy == null) ? 0 : reviewedBy.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        ReviewDetails other = (ReviewDetails) obj;
        if (commitId == null) {
            if (other.commitId != null)
                return false;
        } else if (!commitId.equals(other.commitId))
            return false;
        if (pullNumber != other.pullNumber)
            return false;
        if (rDetailCompositeId == null) {
            if (other.rDetailCompositeId != null)
                return false;
        } else if (!rDetailCompositeId.equals(other.rDetailCompositeId))
            return false;
        if (repoId != other.repoId)
            return false;
        if (reviewComment == null) {
            if (other.reviewComment != null)
                return false;
        } else if (!reviewComment.equals(other.reviewComment))
            return false;
        if (reviewedAt == null) {
            if (other.reviewedAt != null)
                return false;
        } else if (!reviewedAt.equals(other.reviewedAt))
            return false;
        if (reviewedBy == null) {
            if (other.reviewedBy != null)
                return false;
        } else if (!reviewedBy.equals(other.reviewedBy))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (unitId != other.unitId)
            return false;
        return true;
    }

}