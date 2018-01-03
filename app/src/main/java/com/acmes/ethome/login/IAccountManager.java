package com.acmes.ethome.login;

import com.acmes.ethome.mode.bean.DUser;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * <p>
 * Manage Account ,like reading/writing
 * <p>
 * Single-Instance is recommended pattern.
 */

public interface IAccountManager {


    /**
     * Getting current user
     *
     * @return
     */
    DUser getCurrentUser();


    /**
     * Store user to system
     *
     * @param user
     */
    void addUser(DUser user);


    /**
     * Remove user from system
     *
     * @param user
     */
    void removeUser(DUser user);


}
