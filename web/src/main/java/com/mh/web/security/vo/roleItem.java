package com.mh.web.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class roleItem {
    private String id;
    private String gmtCreate;
    private String gmtModified;
    private Boolean deleted;
    private String roleName;
    private String remark;
}
