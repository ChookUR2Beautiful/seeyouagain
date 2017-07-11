package com.xmn.saas.service.redpacket.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.dao.redpacket.RedpacketDao;
import com.xmn.saas.dao.redpacket.RedpacketRecordDao;
import com.xmn.saas.entity.redpacket.RedpacketRecord;
import com.xmn.saas.service.redpacket.RedpacketRecordService;
import com.xmn.saas.service.redpacket.RedpacketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * create 2016/11/10
 *
 * @author yangQiang
 */

@Service
public class RedpacketRecordServiceImpl implements RedpacketRecordService {

    @Autowired
    private RedpacketRecordDao redpacketRecordDao;

    @Autowired
    private RedpacketDao redpacketDao;

    @Autowired
    private RedpacketService redpacketService;

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public Map<String, Object> pagedRecordList(Long redpacketId, Integer pageNum, Integer pageSize) throws Exception {
        List<RedpacketRecord> redpacketRecordList = redpacketRecordDao.selectByRedpacketIdAndLimit(
                redpacketId, pageNum * pageSize, pageSize);

        // 遍历红包领取列表, 封装领取参数
        ArrayList<Record> recordList = new ArrayList<>();

        for (RedpacketRecord redpacketRecord : redpacketRecordList) {
            //获取用户ID
            Long userId = redpacketRecord.getUserId();

            // 复制属性到Record对象中
            Record record = new Record();
            BeanUtils.copyProperties(redpacketRecord, record);

            // 查询用户信息
            Map<String, String> userParamMap = new HashMap<String, String>();
            userParamMap.put("uid", String.valueOf(redpacketRecord.getUserId()));

            // 封装用户信息
            ResponseData userMsg = redpacketService.getUserMsg(userParamMap);
            Map<String, String> data = userMsg.resultMap;
            String nname = data.get("nname");
            String phone = data.get("phone");
            if (StringUtils.isNotBlank(nname)) {
                record.setNname(nname);
            } else if (StringUtils.isNotBlank(phone)) {
                record.setNname(phone);
            } else {
                record.setNname("匿名");
            }

            String avatar = data.get("avatar");
            if (StringUtils.isNotBlank(avatar)) {
                record.setAvatar(globalConfig.getImageHost() + avatar);
            } else {
                record.setAvatar("");
            }
            record.setRedpType(redpacketDao.selectByPrimaryKey(redpacketId).getRedpacketType());


            // 添加Record
            recordList.add(record);
        }

        // 封装响应参数
        Map<String, Object> result = new HashMap<>();
        // 查询领取总数
        result.put("recordCount", redpacketRecordDao.countByRedpacketId(redpacketId));
        result.put("recordList", recordList);

        return result;
    }

    public class Record{
        private Long id;
        private String nname;           // 用户名
        private String avatar;          // 头像
        private Date recordTime;        // 领取时间
        private Integer redpType;       // 红包类型
        private BigDecimal denomination;// 领取金额
        private Long userId;            // 用户id

        public String getNname() {
            return nname;
        }

        public void setNname(String nname) {
            this.nname = nname;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Integer getRedpType() {
            return redpType;
        }

        public void setRedpType(Integer redpType) {
            this.redpType = redpType;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Date getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(Date recordTime) {
            this.recordTime = recordTime;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public BigDecimal getDenomination() {
            return denomination;
        }

        public void setDenomination(BigDecimal denomination) {
            this.denomination = denomination;
        }
    }

}
