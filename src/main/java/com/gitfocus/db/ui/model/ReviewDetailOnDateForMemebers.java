package com.gitfocus.db.ui.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Tech Mahindra 
 * A model class for showing the review details on particular date
 */
public class ReviewDetailOnDateForMemebers implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReviewDetailOnDateForMemebers() {
		super();
	}

	private String userName;
	private String repoName;
	private String reviewDate;
	private List<String> reviewComment;
	private List<String> pullNUmber;
	private List<String> State;
	private List<Timestamp> reviewedAt;

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
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return reviewDate
	 */
	public String getReviewDate() {
		return reviewDate;
	}

	/**
	 * 
	 * @param reviewDate
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * 
	 * @return reviewComment
	 */
	public List<String> getReviewComment() {
		return reviewComment;
	}

	/**
	 * 
	 * @param reviewComment
	 */
	public void setReviewComment(List<String> reviewComment) {
		this.reviewComment = reviewComment;
	}

	/**
	 * 
	 * @return pullNUmber
	 */
	public List<String> getPullNUmber() {
		return pullNUmber;
	}

	/**
	 * 
	 * @param pullNUmber
	 */
	public void setPullNUmber(List<String> pullNUmber) {
		this.pullNUmber = pullNUmber;
	}

	/**
	 * 
	 * @return State
	 */
	public List<String> getState() {
		return State;
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(List<String> state) {
		State = state;
	}

	/**
	 * @return the reviewedAt
	 */
	public List<Timestamp> getReviewedAt() {
		return reviewedAt;
	}

	/**
	 * @param reviewedAt the reviewedAt to set
	 */
	public void setReviewedAt(List<Timestamp> reviewedAt) {
		this.reviewedAt = reviewedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((State == null) ? 0 : State.hashCode());
		result = prime * result + ((pullNUmber == null) ? 0 : pullNUmber.hashCode());
		result = prime * result + ((repoName == null) ? 0 : repoName.hashCode());
		result = prime * result + ((reviewComment == null) ? 0 : reviewComment.hashCode());
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + ((reviewedAt == null) ? 0 : reviewedAt.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		ReviewDetailOnDateForMemebers other = (ReviewDetailOnDateForMemebers) obj;
		if (State == null) {
			if (other.State != null)
				return false;
		} else if (!State.equals(other.State))
			return false;
		if (pullNUmber == null) {
			if (other.pullNUmber != null)
				return false;
		} else if (!pullNUmber.equals(other.pullNUmber))
			return false;
		if (repoName == null) {
			if (other.repoName != null)
				return false;
		} else if (!repoName.equals(other.repoName))
			return false;
		if (reviewComment == null) {
			if (other.reviewComment != null)
				return false;
		} else if (!reviewComment.equals(other.reviewComment))
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewedAt == null) {
			if (other.reviewedAt != null)
				return false;
		} else if (!reviewedAt.equals(other.reviewedAt))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReviewDetailOnDateForMemebers [userName=" + userName + ", repoName=" + repoName + ", reviewDate="
				+ reviewDate + ", reviewComment=" + reviewComment + ", pullNUmber=" + pullNUmber + ", State=" + State
				+ ", reviewedAt=" + reviewedAt + "]";
	}

}
