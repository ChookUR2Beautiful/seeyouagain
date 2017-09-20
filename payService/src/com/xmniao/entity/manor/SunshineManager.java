package com.xmniao.entity.manor;

/**
 * @author liyuanbo
 * @create 2017-06-17 12:31
 **/
public class SunshineManager {
    private int id ;
    private int recommendNumber; //推荐获取奖励
    private int spendEnergy;     //推荐奖励是否与下级消耗能能力挂钩
    private int spendEnergyNumber; //推荐奖励需下级消费的能量值
    private int handsleNumber; // 园又赠送奖励

    public int getId() {
        return id;
    }

    public int getRecommendNumber() {
        return recommendNumber;
    }

    public int getSpendEnergy() {
        return spendEnergy;
    }

    public int getSpendEnergyNumber() {
        return spendEnergyNumber;
    }

    public int getHandsleNumber() {
        return handsleNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecommendNumber(int recommendNumber) {
        this.recommendNumber = recommendNumber;
    }

    public void setSpendEnergy(int spendEnergy) {
        this.spendEnergy = spendEnergy;
    }

    public void setSpendEnergyNumber(int spendEnergyNumber) {
        this.spendEnergyNumber = spendEnergyNumber;
    }

    public void setHandsleNumber(int handsleNumber) {
        this.handsleNumber = handsleNumber;
    }

    @Override
    public String toString() {
        return "SunshineManager{" +
                "id=" + id +
                ", recommendNumber=" + recommendNumber +
                ", spendEnergy=" + spendEnergy +
                ", spendEnergyNumber=" + spendEnergyNumber +
                ", handsleNumber=" + handsleNumber +
                '}';
    }
}
