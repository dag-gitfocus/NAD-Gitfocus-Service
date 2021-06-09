package com.gitfocus.git.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Tech Mahindra 
 * Model class for pull_master table in DB
 */
@Entity
@Table(name = "pull_master", schema = "gitfocus")
public class PullMaster implements Serializable {
 
    private static final long serialVersionUID = 1L;

    public PullMaster() {
    }

    @JsonIgnore
    @JoinColumns({
            @JoinColumn(name = "pull_number", referencedColumnName = "pull_number", insertable = false, updatable = false),
            @JoinColumn(name = "repo_id", referencedColumnName = "repo_id", insertable = false, updatable = false) })
    
    @EmbeddedId
    private PullMasterCompositeId pCompositeId;

    public PullMasterCompositeId getCommitCompositeId() {
        return pCompositeId;
    }

    public void setPullMasterCompositeId(PullMasterCompositeId pullMasterCompositeId) {
        this.pCompositeId = pullMasterCompositeId;
    }

    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "pull_id")
    private int pullId;

    @Column(name = "from_branch")
    private String fromBranch;

    @Column(name = "to_branch")
    private String toBranch;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "pull_status")
    private String pullStatus;

    @Column(name = "commit_count")
    private int commitCount;

    @Column(name = "user_id")
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "merged")
    private boolean merged;

    @Column(name = "merged_by", length = 1024)
    private String mergedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "closed_at")
    private Date closedAt;

    @Column(name = "merged_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mergedAt;

    /**
     * 
     * @return pCompositeId
     */
    public PullMasterCompositeId getpCompositeId() {
        return pCompositeId;
    }

    /**
     * 
     * @param pCompositeId
     */
    public void setpCompositeId(PullMasterCompositeId pCompositeId) {
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

    /**
     * 
     * @return pullId
     */
    public int getPullId() {
        return pullId;
    }

    /**
     * 
     * @param pullId
     */
    public void setPullId(int pullId) {
        this.pullId = pullId;
    }

    /**
     * 
     * @return fromBranch
     */
    public String getFromBranch() {
        return fromBranch;
    }

    /**
     * 
     * @param fromBranch
     */
    public void setFromBranch(String fromBranch) {
        this.fromBranch = fromBranch;
    }

    /**
     * 
     * @return toBranch
     */
    public String getToBranch() {
        return toBranch;
    }

    /**
     * 
     * @param toBranch
     */
    public void setToBranch(String toBranch) {
        this.toBranch = toBranch;
    }

    /**
     * 
     * @return createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 
     * @return pullStatus
     */
    public String getPullStatus() {
        return pullStatus;
    }

    /**
     * 
     * @param pullStatus
     */
    public void setPullStatus(String pullStatus) {
        this.pullStatus = pullStatus;
    }

    /**
     * 
     * @return commitCount
     */
    public int getCommitCount() {
        return commitCount;
    }

    /**
     * 
     * @param commitCount
     */
    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    /**
     * 
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return updatedTime
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 
     * @param updatedTime
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 
     * @return merged
     */
    public boolean isMerged() {
        return merged;
    }

    /**
     * 
     * @param merged
     */
    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    /**
     * 
     * @return mergedBy
     */
    public String getMergedBy() {
        return mergedBy;
    }

    /**
     * 
     * @param mergedBy
     */
    public void setMergedBy(String mergedBy) {
        this.mergedBy = mergedBy;
    }

    /**
     * 
     * @return closedAt
     */
    public Date getClosedAt() {
        return closedAt;
    }

    /**
     * 
     * @param closedAt
     */
    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    /**
     * 
     * @return mergedAt
     */
    public Date getMergedAt() {
        return mergedAt;
    }

    /**
     * 
     * @param mergedAt
     */
    public void setMergedAt(Date mergedAt) {
        this.mergedAt = mergedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((closedAt == null) ? 0 : closedAt.hashCode());
        result = prime * result + commitCount;
        result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
        result = prime * result + ((fromBranch == null) ? 0 : fromBranch.hashCode());
        result = prime * result + (merged ? 1231 : 1237);
        result = prime * result + ((mergedAt == null) ? 0 : mergedAt.hashCode());
        result = prime * result + ((mergedBy == null) ? 0 : mergedBy.hashCode());
        result = prime * result + ((pCompositeId == null) ? 0 : pCompositeId.hashCode());
        result = prime * result + pullId;
        result = prime * result + ((pullStatus == null) ? 0 : pullStatus.hashCode());
        result = prime * result + ((toBranch == null) ? 0 : toBranch.hashCode());
        result = prime * result + unitId;
        result = prime * result + ((updatedTime == null) ? 0 : updatedTime.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        PullMaster other = (PullMaster) obj;
        if (closedAt == null) {
            if (other.closedAt != null)
                return false;
        } else if (!closedAt.equals(other.closedAt))
            return false;
        if (commitCount != other.commitCount)
            return false;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals(other.createdTime))
            return false;
        if (fromBranch == null) {
            if (other.fromBranch != null)
                return false;
        } else if (!fromBranch.equals(other.fromBranch))
            return false;
        if (merged != other.merged)
            return false;
        if (mergedAt == null) {
            if (other.mergedAt != null)
                return false;
        } else if (!mergedAt.equals(other.mergedAt))
            return false;
        if (mergedBy == null) {
            if (other.mergedBy != null)
                return false;
        } else if (!mergedBy.equals(other.mergedBy))
            return false;
        if (pCompositeId == null) {
            if (other.pCompositeId != null)
                return false;
        } else if (!pCompositeId.equals(other.pCompositeId))
            return false;
        if (pullId != other.pullId)
            return false;
        if (pullStatus == null) {
            if (other.pullStatus != null)
                return false;
        } else if (!pullStatus.equals(other.pullStatus))
            return false;
        if (toBranch == null) {
            if (other.toBranch != null)
                return false;
        } else if (!toBranch.equals(other.toBranch))
            return false;
        if (unitId != other.unitId)
            return false;
        if (updatedTime == null) {
            if (other.updatedTime != null)
                return false;
        } else if (!updatedTime.equals(other.updatedTime))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}
