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
 * Model class for commit_details table in DB
 */
@Entity
@Table(name = "commit_details", schema = "gitfocus")
public class CommitDetails implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CommitDetails() {
        super();
    }

    @JsonIgnore
    @JoinColumns({ @JoinColumn(name = "unit_id", referencedColumnName = "unit_id"),
        @JoinColumn(name = "repo_id", referencedColumnName = "repo_id"),
        @JoinColumn(name = "sha_id", referencedColumnName = "sha_id"),
        @JoinColumn(name = "branch_name", referencedColumnName = "branch_name") })

    @EmbeddedId
    CommitDetailsCompositeId cCompositeId;

    @Column(name = "commit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commitDate;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "message")
    private String message;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_status")
    private String fileStatus;

    @Column(name = "lines_added")
    private String linesAdded;

    @Column(name = "lines_removed")
    private String linesRemoved;

    /**
     * 
     * @return cCompositeId
     */
    public CommitDetailsCompositeId getcCompositeId() {
        return cCompositeId;
    }

    /**
     * 
     * @param cCompositeId
     */
    public void setcCompositeId(CommitDetailsCompositeId cCompositeId) {
        this.cCompositeId = cCompositeId;
    }

    /**
     * 
     * @return commitDate
     */
    public Date getCommitDate() {
        return commitDate;
    }

    /**
     * 
     * @param commitDate
     */
    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
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
     * @return message
     */
    public String getMessage() {
        return message;
    }
    /**
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * @return fileStatus
     */
    public String getFileStatus() {
        return fileStatus;
    }

    /**
     * 
     * @param fileStatus
     */
    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    /**
     * 
     * @return linesAdded
     */
    public String getLinesAdded() {
        return linesAdded;
    }

    /**
     * 
     * @param linesAdded
     */
    public void setLinesAdded(String linesAdded) {
        this.linesAdded = linesAdded;
    }

    /**
     * 
     * @return linesRemoved
     */
    public String getLinesRemoved() {
        return linesRemoved;
    }

    /**
     * 
     * @param linesRemoved
     */
    public void setLinesRemoved(String linesRemoved) {
        this.linesRemoved = linesRemoved;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cCompositeId == null) ? 0 : cCompositeId.hashCode());
        result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((fileStatus == null) ? 0 : fileStatus.hashCode());
        result = prime * result + ((linesAdded == null) ? 0 : linesAdded.hashCode());
        result = prime * result + ((linesRemoved == null) ? 0 : linesRemoved.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
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
        CommitDetails other = (CommitDetails) obj;
        if (cCompositeId == null) {
            if (other.cCompositeId != null)
                return false;
        } else if (!cCompositeId.equals(other.cCompositeId))
            return false;
        if (commitDate == null) {
            if (other.commitDate != null)
                return false;
        } else if (!commitDate.equals(other.commitDate))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (fileStatus == null) {
            if (other.fileStatus != null)
                return false;
        } else if (!fileStatus.equals(other.fileStatus))
            return false;
        if (linesAdded == null) {
            if (other.linesAdded != null)
                return false;
        } else if (!linesAdded.equals(other.linesAdded))
            return false;
        if (linesRemoved == null) {
            if (other.linesRemoved != null)
                return false;
        } else if (!linesRemoved.equals(other.linesRemoved))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
