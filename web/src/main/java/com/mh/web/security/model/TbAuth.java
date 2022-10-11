package com.mh.web.security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tb_auth")
@Getter
@Setter
@ToString
public class TbAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String gmtCreate;

    private String gmtModified;

    private String deleted;

    private Long pid;

    private String name;

    private String code;

    private String toCode;

    private String type;

    private String status;

    private String level;

    private String isValid;

    @Override
    public boolean equals(Object obj) {
        if(this==obj){ return true;}
        if(obj==null){ return false;}
        if(obj instanceof TbAuth){
            TbAuth s = (TbAuth)obj;
            if(this.id.equals(s.getId())&&this.pid == s.getPid()){
                return true;
            }
        }
        return false;
    }

}
