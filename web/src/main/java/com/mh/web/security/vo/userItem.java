package com.mh.web.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class userItem implements Serializable {
    private String id;
    private String gmtCreate;
    private String gmtModified;
    private Boolean deleted;
    private String username;
    private String password;
    private String nickName;
    private String salt;
    private String token;
    private String roleName;
}
