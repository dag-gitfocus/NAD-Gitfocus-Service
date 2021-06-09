package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * 
 * @author Tech Mahindra 
 * A class for showing pull master commit details and count based on date
 * 
 */
public class PullMasterCommitDetailOnDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PullMasterCommitDetailOnDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String user = null;
	private String repoName = null;
	private String commitDate = null;
	private List<String> pullNo = new ArrayList<String>();
	private List<Boolean> merged = new ArrayList<Boolean>();
	private List<Boolean> notMerged = new ArrayList<Boolean>();
	private List<String> branchName = new ArrayList<String>();
	private List<String> createdTime = new ArrayList<String>();
	private List<String> commitCount = new ArrayList<String>();
	private List<Entry<Long, String>> noOfDaysBwfirstCommit = new ArrayList<Entry<Long, String>>();

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
	 * @return repoName
	 */
	public String getRepoName() {
		return repoName;
	}

	/**
	 * 
	 * @param repoName
	 */
	public void setRepoName(String repoName) {
		this.repoName = repoName;
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
	 * @return pullNo
	 */
	public List<String> getPullNo() {
		return pullNo;
	}

	/**
	 * 
	 * @param pullNo
	 */
	public void setPullNo(List<String> pullNo) {
		this.pullNo = pullNo;
	}

	/**
	 * 
	 * @return merged
	 */
	public List<Boolean> getMerged() {
		return merged;
	}

	/**
	 * 
	 * @param merged
	 */
	public void setMerged(List<Boolean> merged) {
		this.merged = merged;
	}

	/**
	 * 
	 * @return notMerged
	 */
	public List<Boolean> getNotMerged() {
		return notMerged;
	}

	/**
	 * 
	 * @param notMerged
	 */
	public void setNotMerged(List<Boolean> notMerged) {
		this.notMerged = notMerged;
	}

	/**
	 * 
	 * @return branchName
	 */
	public List<String> getBranchName() {
		return branchName;
	}

	/**
	 * 
	 * @param branchName
	 */
	public void setBranchName(List<String> branchName) {
		this.branchName = branchName;
	}

	/**
	 * 
	 * @return createdTime
	 */
	public List<String> getCreatedTime() {
		return createdTime;
	}

	/**
	 * 
	 * @param createdTime
	 */
	public void setCreatedTime(List<String> createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return
	 */
	public List<String> getCommitCount() {
		return commitCount;
	}

	/**
	 * @param commitCount
	 */
	public void setCommitCount(List<String> commitCount) {
		this.commitCount = commitCount;
	}

	/**
	 * 
	 * @return noOfDaysBwfirstCommit
	 */
	public List<Entry<Long, String>> getNoOfDaysBwfirstCommit() {
		return noOfDaysBwfirstCommit;
	}
	/**
	 * 
	 * @param noOfDaysBwfirstCommit
	 */
	public void setNoOfDaysBwfirstCommit(List<Entry<Long, String>> noOfDaysBwfirstCommit) {
		this.noOfDaysBwfirstCommit = noOfDaysBwfirstCommit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((commitCount == null) ? 0 : commitCount.hashCode());
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((merged == null) ? 0 : merged.hashCode());
		result = prime * result + ((noOfDaysBwfirstCommit == null) ? 0 : noOfDaysBwfirstCommit.hashCode());
		result = prime * result + ((notMerged == null) ? 0 : notMerged.hashCode());
		result = prime * result + ((pullNo == null) ? 0 : pullNo.hashCode());
		result = prime * result + ((repoName == null) ? 0 : repoName.hashCode());
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
		PullMasterCommitDetailOnDate other = (PullMasterCommitDetailOnDate) obj;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
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
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (merged == null) {
			if (other.merged != null)
				return false;
		} else if (!merged.equals(other.merged))
			return false;
		if (noOfDaysBwfirstCommit == null) {
			if (other.noOfDaysBwfirstCommit != null)
				return false;
		} else if (!noOfDaysBwfirstCommit.equals(other.noOfDaysBwfirstCommit))
			return false;
		if (notMerged == null) {
			if (other.notMerged != null)
				return false;
		} else if (!notMerged.equals(other.notMerged))
			return false;
		if (pullNo == null) {
			if (other.pullNo != null)
				return false;
		} else if (!pullNo.equals(other.pullNo))
			return false;
		if (repoName == null) {
			if (other.repoName != null)
				return false;
		} else if (!repoName.equals(other.repoName))
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
		return "PullMasterCommitDetailOnDate [user=" + user + ", repoName=" + repoName + ", commitDate=" + commitDate
				+ ", pullNo=" + pullNo + ", merged=" + merged + ", notMerged=" + notMerged + ", branchName="
				+ branchName + ", createdTime=" + createdTime + ", commitCount=" + commitCount
				+ ", noOfDaysBwfirstCommit=" + noOfDaysBwfirstCommit + "]";
	}

}
