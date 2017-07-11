package com.xmn.saas.entity.bill;

public class SellerPackageWithBLOBs extends SellerPackage {
    private String content;

    private String notice;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }
}