package com.mh.web.security.utils;

import com.mh.web.security.vo.authTreeItem;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
        private List<authTreeItem> menuList = new ArrayList<authTreeItem>();
        public MenuTree(List<authTreeItem> menuList) {
            this.menuList=menuList;
        }

        //建立树形结构
        public List<authTreeItem> builTree(){
            List<authTreeItem> treeMenus =new  ArrayList<authTreeItem>();
            for(authTreeItem menuNode : getRootNode()) {
                menuNode=buildChilTree(menuNode);
                treeMenus.add(menuNode);
            }
            return treeMenus;
        }

        //递归，建立子树形结构
        public authTreeItem buildChilTree(authTreeItem pNode){
            List<authTreeItem> chilMenus =new  ArrayList<authTreeItem>();
            for(authTreeItem menuNode : menuList) {
                if(menuNode.getPid().equals(pNode.getId())) {
                    chilMenus.add(buildChilTree(menuNode));
                }
            }
            pNode.setChildren(chilMenus);
            return pNode;
        }

        //获取根节点(获取所有的父节点)
        public List<authTreeItem> getRootNode() {
            List<authTreeItem> rootMenuLists =new  ArrayList<authTreeItem>();
            for(authTreeItem menuNode : menuList) {
                if(menuNode.getPid().equals("0")) {
                    rootMenuLists.add(menuNode);
                }
            }
            return rootMenuLists;
        }
    }