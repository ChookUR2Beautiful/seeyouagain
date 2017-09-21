package com.xmniao.service.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.DateUtil;
import com.xmniao.dao.live.LiveSalaryDao;
import com.xmniao.domain.live.LiveSalary;
import com.xmniao.domain.live.LiveSalaryData;
import com.xmniao.domain.live.LiverBean;
import com.xmniao.domain.live.SalaryAdmin;
import com.xmniao.thrift.pay.LiveWalletService;
import com.xmniao.thrift.pay.ResponseData;
import com.xmniao.urs.dao.LiverDao;

/**
 * 主播每月工资统计定时任务
 * 
 * @author jianming
 *
 */
@Service
public class LiveSalaryQuertzService {

	private final Logger log = Logger.getLogger(LiveSalaryQuertzService.class);
	
	@Autowired
	private LiveSalaryDao liveSalaryDao;

	@Autowired
	private LiverDao liverDao;
	
	@Autowired
	private ModifyAnchorGiftBirdEggQuertzService anchorGiftBirdEggQuertz;
	
	
	 /**
     * 注入分账系统支付服务的修改钱包余额接口的IP地址
     */
	@Resource(name="transLedgerIP")
    private String transLedgerIP;
    
    /**
     * 注入分账系统支付服务的修改钱包余额接口的端口号
     */
	@Resource(name="transLedgerPort")
    private int transLedgerPort;
	


	/**
	 * 
	 * 方法描述： 统计每个主播上个月的工资,每月第一天执行一次 1.统计礼物获得数量 2.对比等级,最低最高工资计算出实际工资 3.扣除上月所得鸟蛋
	 * 4.生成工资单 创建人：jianming 创建时间：2017年3月30日 上午10:08:19
	 */

	public void exected() {
		
		log.info("开始统计主播工资");
		
		try {
			anchorGiftBirdEggQuertz.giveBack();
		} catch (TTransportException e) {
			log.error("[执行还原丢失礼物鸟蛋方法出现异常]",e);
		}
		
		SalaryAdmin salaryAdmin = new SalaryAdmin(); // 工资管理对象

		List<LiverBean> liverBeans = getLiveSalaryDatas(salaryAdmin.getBeginDate(), salaryAdmin.getEndDate());

		List<LiveSalary> liveSalarys = salaryAdmin.accounting(liverBeans); // 计算工资

		// 调用支付服务,扣除鸟币
		List<LiveSalary> liveSalarys1=updateZbalanceAfterSalary(liveSalarys,salaryAdmin.getFail());
		int result=0;
		if(!liveSalarys1.isEmpty()){
			result=liveSalaryDao.addBatch(liveSalarys1); // 批量添加工资条
		}
		
		if(!salaryAdmin.getFail().isEmpty()){
			HashMap<String,Object> hashMap = new HashMap<>();
			hashMap.put("fail", salaryAdmin.getFail());
			hashMap.put("countTime", DateUtil.format(salaryAdmin.getBeginDate(), "yyyyMM"));
			liveSalaryDao.addFailBatch(hashMap);
		}
		
		if(result!=liveSalarys.size()){
			log.error("添加工资条与计算出的工资条数量不符  计算出"+liveSalarys1.size()+"条,实际添加了"+result+"条");
		}
		
		log.info("主播工资统计完成,共统计出:"+result+"条");
		
	}

	
	public List<LiveSalary> updateZbalanceAfterSalary(List<LiveSalary> liveSalarys, List<LiverBean> fail) {
		List<LiveSalary> success=new ArrayList<>();
		TTransport transport = null;
        try
        {
            //调用分账服务的IP和端口号
            transport = new TSocket(transLedgerIP, transLedgerPort);
            TFramedTransport frame = new TFramedTransport(transport);
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(frame);
            //分账服务的综合服务接口模块
            TMultiplexedProtocol orderProtocol = new TMultiplexedProtocol(protocol, "LiveWalletService");
            LiveWalletService.Client client = new LiveWalletService.Client(orderProtocol);
            //打开端口,开始调用
            transport.open();
            
            for (LiveSalary liveSalary : liveSalarys) {
				HashMap<String,String> hashMap = new HashMap<>();
				hashMap.put("rtype", "17");
				hashMap.put("balance", liveSalary.getBalance().toString());
				hashMap.put("uid", liveSalary.getUid().toString());
            	hashMap.put("option", "1");
            	try {
					ResponseData liveWalletOption = client.liveWalletOption(hashMap);
					if(liveWalletOption.getState()==0){
						//扣除鸟币成功
						success.add(liveSalary);
					}else{
						LiverBean liverBean = new LiverBean();
						liverBean.setMsg("主播账号鸟蛋不足,应发工资:"+liveSalary.getBaseSalary()+" 应扣鸟蛋:"+liveSalary.getBalance());
						liverBean.setId(liveSalary.getAnchorId());
						liverBean.setUid(liveSalary.getUid());
						fail.add(liverBean);
					}
				} catch (Exception e) {
					log.error("调用支付服务扣除鸟蛋时出现异常",e);
					LiverBean liverBean = new LiverBean();
					liverBean.setMsg("调用支付服务扣除鸟蛋时出现异常");
					liverBean.setId(liveSalary.getAnchorId());
					fail.add(liverBean);
				}
            	Thread.sleep(50);
			}
        }
        catch (TException e)
        {
            //若调用抛出异常,则返回标识为-1
            log.error("调用支付服务修改用户商家额度接口异常", e);
        } catch (InterruptedException e) {
        	log.error("线程睡眠出现异常", e);
		}
        finally
        {
            //关闭连接
            transport.close();
        }
        return success;
	}


	/**
	 * 
	 * 方法描述：统计主播活动礼物情况和等级信息
	 * 创建人：jianming  
	 * 创建时间：2017年3月31日 下午5:37:33   
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private List<LiverBean> getLiveSalaryDatas(Date beginDate, Date endDate) {
		List<LiverBean> livers = liverDao.getLiverLever();
		List<LiveSalaryData> lastMonGive = liveSalaryDao.getLastMonGive(beginDate, endDate);
		one: for (int i = 0; i < livers.size(); i++) {
			for (LiveSalaryData liveSalaryData : lastMonGive) {
				if (liveSalaryData.getAnchorId().equals(livers.get(i).getId())) {
					livers.get(i).setLiveSalaryData(liveSalaryData);
					continue one;
				}
			}
		}
		return livers;
	}

}
