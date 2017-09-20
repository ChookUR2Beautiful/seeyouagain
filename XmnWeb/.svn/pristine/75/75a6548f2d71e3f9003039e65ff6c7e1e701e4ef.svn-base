package com.xmniao.xmn.core.manor.entity.treant;

import java.util.List;

/**
 * Created by yang.qiang on 2017/8/2.
 */
public class Node {
    List<Node> children;
    String image;
    Text text;
    String htmlClass;
    String htmlId;
    boolean collapsable;
    private String parentId;

    public Node(String htmlId, Text text,boolean collapsable) {
        this.text = text;
        this.htmlId = htmlId;
        this.collapsable = collapsable;
    }

    public Node(Text text) {
        this.text = text;
    }

    public String getHtmlClass() {
        return htmlClass;
    }

    public void setHtmlClass(String htmlClass) {
        this.htmlClass = htmlClass;
    }

    public String getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(String htmlId) {
        this.htmlId = htmlId;
    }

    public boolean isCollapsable() {
        return collapsable;
    }

    public void setCollapsable(boolean collapsable) {
        this.collapsable = collapsable;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Text getText() {
        return text;
    }

    public Node setText(Text text) {
        this.text = text;
        return this;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }
}
