package com.xmniao.entity.manor;

/**
 * @author liyuanbo
 * @create 2017-06-13 0:33
 **/
public class UserRepository {
    private long uid;//用户ID
    private int sunOverFlow;//阳光溢出数量
    private int currentRepositoryNumber;//用户当前的仓库
    private int extendedRepositoryNumber;//扩展的仓库数量
    private String createTime;//创建时间
    private String updateTime;//更新时间

    public long getUid() {
        return uid;
    }

    public int getSunOverFlow() {
        return sunOverFlow;
    }

    public int getExtendedRepositoryNumber() {
        return extendedRepositoryNumber;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setSunOverFlow(int sunOverFlow) {
        this.sunOverFlow = sunOverFlow;
    }

    public void setExtendedRepositoryNumber(int extendedRepositoryNumber) {
        this.extendedRepositoryNumber = extendedRepositoryNumber;
    }

    public int getCurrentRepositoryNumber() {
        return currentRepositoryNumber;
    }

    public void setCurrentRepositoryNumber(int currentRepositoryNumber) {
        this.currentRepositoryNumber = currentRepositoryNumber;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "uid=" + uid +
                ", sunOverFlow=" + sunOverFlow +
                ", currentRepositoryNumber=" + currentRepositoryNumber +
                ", extendedRepositoryNumber=" + extendedRepositoryNumber +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
