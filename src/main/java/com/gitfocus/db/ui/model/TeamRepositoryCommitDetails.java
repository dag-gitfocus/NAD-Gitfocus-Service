package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tech Mahindra 
 * Model class to list commit_details for teams and corresponding repository
 */
public class TeamRepositoryCommitDetails implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TeamRepositoryCommitDetails() {
        super();
    }

    private List<String> commitLables = new ArrayList<String>();
    private List<Integer> commitData = new ArrayList<Integer>();
    private List<String> commitLables1 = new ArrayList<String>();
    private List<Integer> commitData1 = new ArrayList<Integer>();

    private int totalCommits;
    private List<String> userId = new ArrayList<String>();
    private List<String> message = new ArrayList<String>();

    /**
     * 
     * @return userId
     */
    public List<String> getUserId() {
        return userId;
    }
    /**
     * 
     * @param userId
     */
    public void setUserId(List<String> userId) {
        this.userId = userId;
    }
    /**
     * 
     * @return message
     */
    public List<String> getMessage() {
        return message;
    }
    /**
     * 
     * @param message
     */
    public void setMessage(List<String> message) {
        this.message = message;
    }
    /**
     * 
     * @return commitLables
     */
    public List<String> getCommitLables() {
        return commitLables;
    }
    /**
     * 
     * @param commitLables
     */
    public void setCommitLables(List<String> commitLables) {
        this.commitLables = commitLables;
    }
    /**
     * 
     * @return commitData
     */
    public List<Integer> getCommitData() {
        return commitData;
    }
    /**
     * 
     * @param commitData
     */
    public void setCommitData(List<Integer> commitData) {
        this.commitData = commitData;
    }
    /**
     * 
     * @return commitLables1
     */
    public List<String> getCommitLables1() {
        return commitLables1;
    }
    /**
     * 
     * @param commitLables1
     */
    public void setCommitLables1(List<String> commitLables1) {
        this.commitLables1 = commitLables1;
    }
    /**
     * 
     * @return commitData1
     */
    public List<Integer> getCommitData1() {
        return commitData1;
    }
    /**
     * 
     * @param commitData1
     */
    public void setCommitData1(List<Integer> commitData1) {
        this.commitData1 = commitData1;
    }
    /**
     * 
     * @return totalCommits
     */
    public int getTotalCommits() {
        return totalCommits;
    }
    /**
     * 
     * @param totalCommits
     */
    public void setTotalCommits(int totalCommits) {
        this.totalCommits = totalCommits;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commitData == null) ? 0 : commitData.hashCode());
        result = prime * result + ((commitData1 == null) ? 0 : commitData1.hashCode());
        result = prime * result + ((commitLables == null) ? 0 : commitLables.hashCode());
        result = prime * result + ((commitLables1 == null) ? 0 : commitLables1.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + totalCommits;
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
        TeamRepositoryCommitDetails other = (TeamRepositoryCommitDetails) obj;
        if (commitData == null) {
            if (other.commitData != null)
                return false;
        } else if (!commitData.equals(other.commitData))
            return false;
        if (commitData1 == null) {
            if (other.commitData1 != null)
                return false;
        } else if (!commitData1.equals(other.commitData1))
            return false;
        if (commitLables == null) {
            if (other.commitLables != null)
                return false;
        } else if (!commitLables.equals(other.commitLables))
            return false;
        if (commitLables1 == null) {
            if (other.commitLables1 != null)
                return false;
        } else if (!commitLables1.equals(other.commitLables1))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (totalCommits != other.totalCommits)
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}