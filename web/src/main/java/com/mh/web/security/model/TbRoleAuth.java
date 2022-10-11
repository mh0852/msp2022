package com.mh.web.security.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@Getter
@ToString
public class TbRoleAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "role_id")
    private Long roleId;

    @MppMultiId
    @TableField(value = "auth_id")
    private Long authId;

}
