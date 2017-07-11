package com.xmn.saas.service.redpacket;

import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.entity.redpacket.RedpacketRecord;

import java.util.List;
import java.util.Map;

public interface RedpacketService {
	
	/**
	 * 结束红包活动并退款
	 * @Title: endRedpacket 
	 * @Description: TODO 
	 * @param @param redpacketId
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	public Map<String,String> endRedpacket(Long redpacketId)throws Exception;
	
	public ResponseData getUserMsg(Map<String, String> paramMap) throws Exception;

    /**
     * 根据商家id查询红包信息
     *
     * @param @param  sellerid
     * @param @return
     * @param @throws Exception    设定文件
     * @return Redpacket    返回类型
     * @throws
     * @Title: findRedpacketBySellerid
     * @Description: TODO
     */
    Redpacket findRedpacketBySellerid(Long sellerid) throws Exception;

    /**
     * 根据主键获取红包信息
     *
     * @param @param  redpacketId
     * @param @return
     * @param @throws Exception    设定文件
     * @return Redpacket    返回类型
     * @throws
     * @Title: getRedpacketByPrimaryKey
     * @Description: TODO
     */
    Redpacket findRedpacketByPrimaryKey(Long redpacketId) throws Exception;

    /**
     * 新增红包信息
     *
     * @param @param  redpacket
     * @param @throws Exception    设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: addRedpacket
     * @Description: TODO
     */
    Integer addRedpacket(Redpacket redpacket) throws Exception;

    /**
     * 新增红包领取记录信息
     *
     * @param @param  redpacketRecord
     * @param @throws Exception    设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: addRedpacketRecord
     * @Description: TODO
     */
    Integer addRedpacketRecord(RedpacketRecord redpacketRecord) throws Exception;

    /**
     * 编辑红包信息
     *
     * @param @param  redpacket
     * @param @throws Exception    设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: updateRedpacket
     * @Description: TODO
     */
    Integer updateRedpacket(Redpacket redpacket) throws Exception;

    /**
     * @param @param  redpacket
     * @param @return
     * @param @throws Exception    设定文件
     * @return Integer    返回类型
     * @throws
     * @Title: updateByPrimaryKeyOrVersionLock
     * @Description: TODO
     */
    Integer updateByPrimaryKeyAndVersionLock(Redpacket redpacket) throws Exception;

    /**
     * 红包信息列表
     *
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<Redpacket>    返回类型
     * @throws
     * @Title: list
     * @Description: TODO
     */
    List<Redpacket> findRedpacketByParams(Map<String, Object> paramMap) throws Exception;

    /**
     * 根据订单号获取红包信息
     *
     * @param @param  orderNo
     * @param @return
     * @param @throws Exception    设定文件
     * @return Redpacket    返回类型
     * @throws
     * @Title: findRedpacketByOrderNo
     * @Description: TODO
     */
    Redpacket findRedpacketByOrderNo(String orderNo) throws Exception;

    /**
     * 红包领取列表信息
     *
     * @param @param  paramMap
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<RedpacketRecord>    返回类型
     * @throws
     * @Title: findRedpacketRecordByParams
     * @Description: TODO
     */
    List<RedpacketRecord> findRedpacketRecordByParams(Map<String, Object> paramMap) throws Exception;

    /**
     * 根据主键获取红包领取信息
     *
     * @param @param  redpacketRecordId
     * @param @return
     * @param @throws Exception    设定文件
     * @return RedpacketRecord    返回类型
     * @throws
     * @Title: findRedpacketRecordByPrimaryKey
     * @Description: TODO
     */
    RedpacketRecord findRedpacketRecordByPrimaryKey(Long redpacketRecordId) throws Exception;


    /**
     * 方法描述：获取大转盘可选奖品
     * 创建人：jianming
     * 创建时间：2016年10月10日 上午10:16:09
     *
     * @param sellerId
     * @return
     */
    List<Redpacket> listRoulleteAward(Integer sellerId);

    /**
     * 方法描述：锁定红包
     * 创建人：jianming
     * 创建时间：2016年10月10日 下午5:50:13
     *
     * @param awardId
     */
    void setAward(Integer awardId);

    /**
     * 方法描述：查询营销活动的奖品信息
     * 创建人：jianming
     * 创建时间：2016年10月12日 上午10:35:28
     *
     * @param id
     * @param activityType
     * @return
     */
    List<Redpacket> getActivityAward(Integer id, Integer activityType);

    Redpacket findRedpacket(Redpacket param);

    /**
     * 判断红包是否过期
     * @param redpacket
     * @return
     * @throws Exception
     */
    boolean isExpired(Redpacket redpacket) throws Exception;

    boolean redpacketStoppable(Redpacket redpacket) throws Exception;

    int disableRedpacket(Redpacket updateRedp);
}
