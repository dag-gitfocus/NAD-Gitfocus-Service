package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tech Mahindra composite id class for commit_details
 */
@Embeddable
public class ReviewDetailsCompositeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ReviewDetailsCompositeId() {
        super();
    }

    @Column(name = "review_id")
    private int reviewId;

    /**
     * 
     * @return reviewId
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * 
     * @param reviewId
     */

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + reviewId;
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
        ReviewDetailsCompositeId other = (ReviewDetailsCompositeId) obj;
        if (reviewId != other.reviewId)
            return false;
        return true;
    }

}
