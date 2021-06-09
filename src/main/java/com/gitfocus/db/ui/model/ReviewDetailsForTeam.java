package com.gitfocus.db.ui.model;

import java.io.Serializable;

/**
 * @author Tech Mahindra 
 * A class for showing each team members commit details based on date
 */
public class ReviewDetailsForTeam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ReviewDetailsForTeam() {
        super();
    }

    private String user = null;
    private String commitDate = null;
    private String commitCount = null;
    /**
     * 
     * @return user
     */
    public String getUser() {
        return user;
    }
    /**
     * 
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * 
     * @return commitDate
     */
    public String getCommitDate() {
        return commitDate;
    }
    /**
     * 
     * @param commitDate
     */
    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }
    /**
     * 
     * @return commitCount
     */
    public String getCommitCount() {
        return commitCount;
    }
    /**
     * 
     * @param commitCount
     */
    public void setCommitCount(String commitCount) {
        this.commitCount = commitCount;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commitCount == null) ? 0 : commitCount.hashCode());
        result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        ReviewDetailsForTeam other = (ReviewDetailsForTeam) obj;
        if (commitCount == null) {
            if (other.commitCount != null)
                return false;
        } else if (!commitCount.equals(other.commitCount))
            return false;
        if (commitDate == null) {
            if (other.commitDate != null)
                return false;
        } else if (!commitDate.equals(other.commitDate))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "TeamMembersCommitDetail [user=" + user + ", commitDate=" + commitDate + ", commitCount=" + commitCount
                + "]";
    }

}
