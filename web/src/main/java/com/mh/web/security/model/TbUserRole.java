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
@TableName("tb_user_role")
@Getter
@Setter
@ToString
public class TbUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId // 复合主键
    @TableField(value = "user_id")
    private Long userId;

    @MppMultiId
    @TableField(value = "role_id")
    private Long roleId;

}
