package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * 
 * @author Tech Mahindra 
 * A class for showing daily user commit details based on date
 * 
 */
public class TeamMembersCommitDetailBasedOnPR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private Timestamp commitDate = null;
	private String[] fileNameArray;
	private String[] fileStatusArray;
	private String[] linesAddedArray;
	private String[] linesRemovedArray;
	private int totalFileCount;
	private String totalFilesAdded;
	private String totalFilesModified;
	private String totalLinesAdded;
	private String totalLinesRemoved;
	private String branchName;
	private String commitMessage;
	

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
	 * @return commitDate
	 */
	public Timestamp getCommitDate() {
		return commitDate;
	}
	/**
	 * 
	 * @param commitDate
	 */
	public void setCommitDate(Timestamp commitDate) {
		this.commitDate = commitDate;
	}
	/**
	 * 
	 * @return fileNameArray
	 */
	public String[] getFileNameArray() {
		return fileNameArray;
	}
	/**
	 * 
	 * @param fileNameArray
	 */
	public void setFileNameArray(String[] fileNameArray) {
		this.fileNameArray = fileNameArray;
	}
	/**
	 * 
	 * @return linesAddedArray
	 */
	public String[] getLinesAddedArray() {
		return linesAddedArray;
	}
	/**
	 * 
	 * @param linesAddedArray
	 */
	public void setLinesAddedArray(String[] linesAddedArray) {
		this.linesAddedArray = linesAddedArray;
	}
	/**
	 * 
	 * @return fileStatusArray
	 */
	public String[] getFileStatusArray() {
		return fileStatusArray;
	}
	/**
	 * 
	 * @param fileStatusArray
	 */
	public void setFileStatusArray(String[] fileStatusArray) {
		this.fileStatusArray = fileStatusArray;
	}
	/**
	 * 
	 * @return linesRemovedArray
	 */
	public String[] getLinesRemovedArray() {
		return linesRemovedArray;
	}
	/**
	 * 
	 * @param linesRemovedArray
	 */
	/**
	 * @param linesRemovedArray
	 */
	public void setLinesRemovedArray(String[] linesRemovedArray) {
		this.linesRemovedArray = linesRemovedArray;
	}
	/**
	 * @return
	 */
	public int getTotalFileCount() {
		return totalFileCount;
	}
	/**
	 * @param totalFileCount
	 */
	public void setTotalFileCount(int totalFileCount) {
		this.totalFileCount = totalFileCount;
	}
	/**
	 * @return the totalFilesAdded
	 */
	public String getTotalFilesAdded() {
		return totalFilesAdded;
	}
	/**
	 * @param totalFilesAdded the totalFilesAdded to set
	 */
	public void setTotalFilesAdded(String totalFilesAdded) {
		this.totalFilesAdded = totalFilesAdded;
	}
	/**
	 * @return the totalFilesModified
	 */
	public String getTotalFilesModified() {
		return totalFilesModified;
	}
	/**
	 * @param totalFilesModified the totalFilesModified to set
	 */
	public void setTotalFilesModified(String totalFilesModified) {
		this.totalFilesModified = totalFilesModified;
	}
	/**
	 * @return the totalLinesAdded
	 */
	public String getTotalLinesAdded() {
		return totalLinesAdded;
	}
	/**
	 * @param totalLinesAdded the totalLinesAdded to set
	 */
	public void setTotalLinesAdded(String totalLinesAdded) {
		this.totalLinesAdded = totalLinesAdded;
	}
	/**
	 * @return the totalLinesRemoved
	 */
	public String getTotalLinesRemoved() {
		return totalLinesRemoved;
	}
	/**
	 * @param totalLinesRemoved the totalLinesRemoved to set
	 */
	public void setTotalLinesRemoved(String totalLinesRemoved) {
		this.totalLinesRemoved = totalLinesRemoved;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the commitMessage
	 */
	public String getCommitMessage() {
		return commitMessage;
	}
	/**
	 * @param commitMessage the commitMessage to set
	 */
	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + ((commitMessage == null) ? 0 : commitMessage.hashCode());
		result = prime * result + Arrays.hashCode(fileNameArray);
		result = prime * result + Arrays.hashCode(fileStatusArray);
		result = prime * result + Arrays.hashCode(linesAddedArray);
		result = prime * result + Arrays.hashCode(linesRemovedArray);
		result = prime * result + totalFileCount;
		result = prime * result + ((totalFilesAdded == null) ? 0 : totalFilesAdded.hashCode());
		result = prime * result + ((totalFilesModified == null) ? 0 : totalFilesModified.hashCode());
		result = prime * result + ((totalLinesAdded == null) ? 0 : totalLinesAdded.hashCode());
		result = prime * result + ((totalLinesRemoved == null) ? 0 : totalLinesRemoved.hashCode());
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
		TeamMembersCommitDetailBasedOnPR other = (TeamMembersCommitDetailBasedOnPR) obj;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		if (commitDate == null) {
			if (other.commitDate != null)
				return false;
		} else if (!commitDate.equals(other.commitDate))
			return false;
		if (commitMessage == null) {
			if (other.commitMessage != null)
				return false;
		} else if (!commitMessage.equals(other.commitMessage))
			return false;
		if (!Arrays.equals(fileNameArray, other.fileNameArray))
			return false;
		if (!Arrays.equals(fileStatusArray, other.fileStatusArray))
			return false;
		if (!Arrays.equals(linesAddedArray, other.linesAddedArray))
			return false;
		if (!Arrays.equals(linesRemovedArray, other.linesRemovedArray))
			return false;
		if (totalFileCount != other.totalFileCount)
			return false;
		if (totalFilesAdded == null) {
			if (other.totalFilesAdded != null)
				return false;
		} else if (!totalFilesAdded.equals(other.totalFilesAdded))
			return false;
		if (totalFilesModified == null) {
			if (other.totalFilesModified != null)
				return false;
		} else if (!totalFilesModified.equals(other.totalFilesModified))
			return false;
		if (totalLinesAdded == null) {
			if (other.totalLinesAdded != null)
				return false;
		} else if (!totalLinesAdded.equals(other.totalLinesAdded))
			return false;
		if (totalLinesRemoved == null) {
			if (other.totalLinesRemoved != null)
				return false;
		} else if (!totalLinesRemoved.equals(other.totalLinesRemoved))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TeamMembersCommitDetailBasedOnPR [userId=" + userId + ", commitDate=" + commitDate + ", fileNameArray="
				+ Arrays.toString(fileNameArray) + ", linesAddedArray=" + Arrays.toString(linesAddedArray)
				+ ", fileStatusArray=" + Arrays.toString(fileStatusArray) + ", linesRemovedArray="
				+ Arrays.toString(linesRemovedArray) + ", totalFileCount=" + totalFileCount + ", totalFilesAdded="
				+ totalFilesAdded + ", totalFilesModified=" + totalFilesModified + ", totalLinesAdded="
				+ totalLinesAdded + ", totalLinesRemoved=" + totalLinesRemoved + ", branchName=" + branchName
				+ ", commitMessage=" + commitMessage + "]";
	}

}
