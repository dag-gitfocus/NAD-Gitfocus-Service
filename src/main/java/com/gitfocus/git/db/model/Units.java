package com.gitfocus.git.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tech Mahindra 
 * Model class for units table in DB
 */
@Entity
@Table(name = "units", schema = "gitfocus")
public class Units implements Serializable {
 
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Units() {
        super();
    }

    @Id
    @Column(name = "unit_id")
    private int unitId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "no_of_repos")
    private int noOfRepos;

    @Column(name = "unit_owner")
    private String unitOwner;

    @Column(name = "access_token")
    private String accessToken;
    
    /**
     * 
     * @return
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
     * @return unitName
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 
     * @return noOfRepos
     */
    public int getNoOfRepos() {
        return noOfRepos;
    }

    /**
     * 
     * @param noOfRepos
     */
    public void setNoOfRepos(int noOfRepos) {
        this.noOfRepos = noOfRepos;
    }

    /**
     * 
     * @return unitOwner
     */
    public String getUnitOwner() {
        return unitOwner;
    }

    /**
     * 
     * @param unitOwner
     */
    public void setUnitOwner(String unitOwner) {
        this.unitOwner = unitOwner;
    }

    /**
     * 
     * @return accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + noOfRepos;
        result = prime * result + unitId;
        result = prime * result + ((unitName == null) ? 0 : unitName.hashCode());
        result = prime * result + ((unitOwner == null) ? 0 : unitOwner.hashCode());
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
        Units other = (Units) obj;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (noOfRepos != other.noOfRepos)
            return false;
        if (unitId != other.unitId)
            return false;
        if (unitName == null) {
            if (other.unitName != null)
                return false;
        } else if (!unitName.equals(other.unitName))
            return false;
        if (unitOwner == null) {
            if (other.unitOwner != null)
                return false;
        } else if (!unitOwner.equals(other.unitOwner))
            return false;
        return true;
    }

}
