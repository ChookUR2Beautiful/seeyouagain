package com.xmn.saas.service.redpacket.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.ThriftBuilder;
import com.xmn.saas.base.thrift.common.ResponseData;
import com.xmn.saas.constants.RedpacketConsts;
import com.xmn.saas.dao.redpacket.RedpacketDao;
import com.xmn.saas.dao.redpacket.RedpacketRecordDao;
import com.xmn.saas.entity.redpacket.Redpacket;
import com.xmn.saas.entity.redpacket.RedpacketRecord;
import com.xmn.saas.service.base.UserService;
import com.xmn.saas.service.redpacket.RedpacketService;
import com.xmn.saas.service.wallet.WalletService;
import com.xmn.saas.utils.CalendarUtil;

@SuppressWarnings("all")
@Service
public class RedpacketServiceImpl implements RedpacketService {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(RedpacketServiceImpl.class);
	
	@Autowired
	private RedpacketDao redpacketDao;
	
	@Autowired
	private RedpacketRecordDao redpacketRecordDao;
	
	@Autowired
	private GlobalConfig globalConfig;
	
	@Autowired
	private WalletService walletService;
	
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
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String,String> endRedpacket(Long redpacketId) throws Exception{
		Map<String,String> result=new HashMap<String,String>();
		Redpacket redpacket=redpacketDao.selectByPrimaryKey(redpacketId);
		if(redpacket==null){
			result.put("state", "500");
			result.put("info", "没有此红包信息");
		}else{
			  if(redpacket.getBeginDate().compareTo(CalendarUtil.dateFormat(new Date())) == 1) {
	            result.put("state", "500");
				result.put("info", "该红包活动还未开始");
	          }else if (redpacket.getEndDate().compareTo(CalendarUtil.dateFormat(new Date())) == 1) {
				result.put("state", "500");
				result.put("info", "该红包活动结束日期未到期");
              }else if (redpacket.getPayStatus().intValue() == RedpacketConsts.PAYYES) {
            	
				BigDecimal refundAmount=new BigDecimal("0.00");//应退商家金额
				BigDecimal total=redpacket.getTotalAmount(); //红包总金额
				Map<String,Object> paramMap=new HashMap<String,Object>();
				paramMap.put("REDPACKET_ID", redpacket.getId());
				List<RedpacketRecord> redpacketRecordList=redpacketRecordDao.findRedpacketRecordByParams(paramMap);//获取红包领取记录信息
				BigDecimal realSpending = new BigDecimal("0.00"); //领取合计
				if (!redpacketRecordList.isEmpty()) {
					for (RedpacketRecord redpacketRecord : redpacketRecordList) {
						if(redpacketRecord.getStatus()==1){//状态 1： 已到账
							realSpending = realSpending.add(redpacketRecord.getDenomination());
						}
					}
				}
				
				if(redpacket.getGetRedpacket().compareTo(realSpending)==0){//判断红包表“已领取红包金额”与“领取合计”是否匹配
					refundAmount=total.subtract(realSpending).setScale(2, BigDecimal.ROUND_HALF_UP);
					//红包终止时剩余未领取的金额退还给商家钱包commision字段
					Map<String,String> walletMap=new HashMap<String,String>();
					walletMap.put("uId", redpacket.getSellerid().toString());
					walletMap.put("userType", RedpacketConsts.USER_TYPE);
					walletMap.put("option", "0");
					walletMap.put("commision", refundAmount.toString());
					walletMap.put("rType", "39");
					walletMap.put("orderId", redpacket.getOrderNo());
					walletMap.put("remark",redpacket.getRedpacketName());
					Map<String,String> resultMap=walletService.updateWalletAmount(walletMap);
					logger.info("退还给商家钱包结果："+resultMap);
					if(!resultMap.isEmpty()){
						String status=resultMap.get("state");
						String msg=resultMap.get("msg");
						if("0".equals(status)){
							//退款成功
							redpacket.setEndDate(new Date());
							redpacket.setPayStatus(RedpacketConsts.REFUND);
							redpacket.setRefundAmount(refundAmount);
							redpacket.setStatus(RedpacketConsts.DISABLE);
							Long currentVersion =redpacket.getVersionLock(); //乐观锁当前版本号
							redpacket.setVersionLock(currentVersion);
							int count=redpacketDao.updateByPrimaryKeyAndVersionLock(redpacket);
							if(count>0){
								result.put("state", "200");
								result.put("info", "退款成功,退款商家id:"+redpacket.getSellerid());
							}
						}else{
							result.put("state", "500");
							result.put("info", msg);
						}
					}
				}else{
					result.put("state", "500");
					result.put("info", "退款金额出错,红包总金额："+redpacket.getTotalAmount()+",红包已领取金额:"+redpacket.getGetRedpacket()+",领取记录合计："+realSpending+",退款金额："+refundAmount);
				}
			}else{
				String msg=null;
			    if(redpacket.getPayStatus().intValue()== RedpacketConsts.PAYNO){
					msg="未支付";
				}else if(redpacket.getPayStatus().intValue()== RedpacketConsts.PAYCALCEN){
					msg="取消支付";
				}else if(redpacket.getPayStatus().intValue()== RedpacketConsts.PAYFAILURE){
					msg="支付失败";
				}else if(redpacket.getPayStatus().intValue()== RedpacketConsts.REFUND){
					msg="已退款";
				}
				
				result.put("state", "500");
				result.put("info", "红包状态不正确："+msg);
			}
		}
		return result;
	}
	
	/**
     * 获取用户信息
     */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public ResponseData getUserMsg(Map<String, String> paramMap) {
        ResponseData responseData = null;
        try {
            UserService.Client client =
                    ThriftBuilder.build(globalConfig.getThriftBusHost(),
                            Integer.parseInt(globalConfig.getThriftBusPort()), "UserService",
                            UserService.Client.class);

            ThriftBuilder.open();
            responseData = client.getUserMsg(paramMap);
        } catch (Exception e) {
        	logger.error("获取用户信息信息异常！", e);

        } finally {
            ThriftBuilder.close();
        }
        return responseData;
    }
	
	
	/**
	 * 新增红包领取记录信息
	 * @Title: addRedpacketRecord 
	 * @Description: TODO 
	 * @param @param redpacketRecord
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
    @Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer addRedpacketRecord(RedpacketRecord redpacketRecord)throws Exception{
		return redpacketRecordDao.insert(redpacketRecord);
	}
	
	/**
	 * 红包领取列表信息
	 * @Title: findRedpacketRecordByParams 
	 * @Description: TODO 
	 * @param @param paramMap
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return List<RedpacketRecord>    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<RedpacketRecord> findRedpacketRecordByParams(Map<String,Object> paramMap)throws Exception{
		return redpacketRecordDao.findRedpacketRecordByParams(paramMap);
	}
	
	/**
	 * 根据商家id查询红包信息
	 * @Title: findRedpacketBySellerid 
	 * @Description: TODO 
	 * @param @param sellerid
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Redpacket    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public Redpacket findRedpacketBySellerid(Long sellerid)throws Exception{
		// TODO Auto-generated method stub
		return redpacketDao.findRedpacketBySellerid(sellerid);
	}
	
	/**
	 * 根据参数获取红包信息
	 * @Title: getRedpacketByPrimaryKey 
	 * @Description: TODO 
	 * @param @param redpacketId
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Redpacket    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public Redpacket findRedpacketByPrimaryKey(Long redpacketId)throws Exception{
		// TODO Auto-generated method stub
		return redpacketDao.selectByPrimaryKey(redpacketId);
	}

	/**
	 * 新增红包
	 * @Title: addRedpacket 
	 * @Description: TODO 
	 * @param @param redpacket
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer addRedpacket(Redpacket redpacket) throws Exception {
		// TODO Auto-generated method stub
		return redpacketDao.insert(redpacket);
	}

	/**
	 * 编辑红包
	 * @Title: updateRedpacket 
	 * @Description: TODO 
	 * @param @param redpacket
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer updateRedpacket(Redpacket redpacket) throws Exception {
		// TODO Auto-generated method stub
		return redpacketDao.updateByPrimaryKey(redpacket);
	}
	
	/**
	 * 
	 * @Title: updateByPrimaryKeyOrVersionLock 
	 * @Description: TODO 
	 * @param @param redpacket
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Integer    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Integer updateByPrimaryKeyAndVersionLock(Redpacket redpacket)throws Exception{
		return redpacketDao.updateByPrimaryKeyAndVersionLock(redpacket);
	}
	
	/**
	 * 根据订单号获取红包信息
	 * @Title: findRedpacketByOrderNo 
	 * @Description: TODO 
	 * @param @param orderNo
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Redpacket    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Redpacket findRedpacketByOrderNo(String orderNo)throws Exception{
		// TODO Auto-generated method stub
		return redpacketDao.findRedpacketByOrderNo(orderNo);
	}

	/**
	 * 红包列表
	 * @Title: list 
	 * @Description: TODO 
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return List<Redpacket>    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<Redpacket> findRedpacketByParams(Map<String,Object> paramMap)throws Exception {
		return redpacketDao.findRedpacketByParams(paramMap);
	}
	
	/**
	 * 
	 * 方法描述：获取大转盘可选奖品
	 * 创建人：jianming  
	 * 创建时间：2016年10月10日 上午10:17:14   
	 * @param sellerId
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Redpacket> listRoulleteAward(Integer sellerId) {
		return redpacketDao.listRoulleteAward(sellerId);
	}
	
	/**
	 * 根据主键获取红包领取信息
	 * @Title: findRedpacketRecordByPrimaryKey 
	 * @Description: TODO 
	 * @param @param redpacketRecordId
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return RedpacketRecord    返回类型 
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public RedpacketRecord findRedpacketRecordByPrimaryKey(Long redpacketRecordId)throws Exception{
		return redpacketRecordDao.selectByPrimaryKey(redpacketRecordId);
	}

	@Override
	public void setAward(Integer awardId) {
		redpacketDao.setAward(awardId);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Redpacket> getActivityAward(Integer id, Integer activityType) {
		return redpacketDao.getActivityAward(id,activityType);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Redpacket findRedpacket(Redpacket param) {
        return redpacketDao.selectRedpacketBySellerIdAndRedpacketTypeAndStatus(param);
	}

	@Override
	public boolean isExpired(Redpacket redpacket) throws Exception {
		String endTime = redpacket.getEndTime();
		String s = CalendarUtil.dateFormat(redpacket.getEndDate(), "yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date closeDate = format.parse(s + " " + endTime);
        // 对比关闭时间是否小于当前实现
        return closeDate.compareTo(new Date()) < 0;
	}

    @Override
    public boolean redpacketStoppable(Redpacket redpacket) throws Exception {
        return this.isExpired(redpacket)
				&& redpacket.getPayStatus() == 2
				&& redpacket.getStatus() != 0
				 ;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int disableRedpacket(Redpacket updateRedp) {
		return redpacketDao.updateStatusById(updateRedp);
	}

}
