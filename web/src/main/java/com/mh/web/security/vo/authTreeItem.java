package com.mh.web.security.vo;

import com.mh.web.security.model.TbAuth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class authTreeItem {
    private String id;

    private String gmtCreate;

    private String gmtModified;

    private boolean deleted;

    private String pid;

    private String name;

    private String code;

    private String toCode;

    private int type;

    private String status;

    private int level;

    private List<authTreeItem> children;

    private boolean select;
}
