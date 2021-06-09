package com.gitfocus.db.ui.model;

import java.io.Serializable;

/**
 * @author Tech Mahindra 
 * A model class for showing the number of Pull Requests created by a team member
 */
public class PullRequestCount implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PullRequestCount() {
        super();
    }

    private String user = null;
    private String prCreatedDate = null;
    private String prCount = null;

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
     * @return prCreatedDate
     */
    public String getPrCreatedDate() {
        return prCreatedDate;
    }

    /**
     * @param prCreatedDate
     */
    public void setPrCreatedDate(String prCreatedDate) {
        this.prCreatedDate = prCreatedDate;
    }

    /**
     * @return prCount
     */
    public String getPrCount() {
        return prCount;
    }

    /**
     * @param prCount
     */
    public void setPrCount(String prCount) {
        this.prCount = prCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((prCount == null) ? 0 : prCount.hashCode());
        result = prime * result + ((prCreatedDate == null) ? 0 : prCreatedDate.hashCode());
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
        PullRequestCount other = (PullRequestCount) obj;
        if (prCount == null) {
            if (other.prCount != null)
                return false;
        } else if (!prCount.equals(other.prCount))
            return false;
        if (prCreatedDate == null) {
            if (other.prCreatedDate != null)
                return false;
        } else if (!prCreatedDate.equals(other.prCreatedDate))
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
        return "PullRequestCount [user=" + user + ", commitDate=" + prCreatedDate + ", commitCount=" + prCount + "]";
    }

}
