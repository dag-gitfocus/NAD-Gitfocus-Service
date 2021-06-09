package com.gitfocus.git.db.service;

import java.text.ParseException;

/**
 * @author Tech Mahindra
 *
 */
public interface IPullCommitGitService {
    /**
     * 
     * @return boolean
     * @throws ParseException
     */
    public boolean save() throws ParseException;

}
