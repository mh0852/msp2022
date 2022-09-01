package com.mh.web.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@TableName("tb_role_auth")
public class TbRoleAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long authId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    @Override
    public String toString() {
        return "TbRoleAuth{" +
        "roleId = " + roleId +
        ", authId = " + authId +
        "}";
    }
}
