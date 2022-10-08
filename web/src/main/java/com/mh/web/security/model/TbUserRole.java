package com.mh.web.security.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-08-31
 */
@TableName("tb_user_role")
public class TbUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId // 复合主键
    @TableField(value = "user_id")
    private Long userId;

    @MppMultiId
    @TableField(value = "role_id")
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "TbUserRole{" +
        "userId = " + userId +
        ", roleId = " + roleId +
        "}";
    }
}
