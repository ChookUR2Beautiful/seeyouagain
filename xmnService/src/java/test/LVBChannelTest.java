package test;

import java.math.BigInteger;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.vod.QcloudApiModuleCenter;
import com.xmniao.xmn.core.vod.Module.Live;

public class LVBChannelTest {

	public static void main(String[] args) {
		//查询腾讯云直播频道状态
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Live(),"GET");
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		//请求参数,频道号
		params.put("channelId","9896587163621002878");
		
		String result = null;
			try {
				/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
				result = module.call("DescribeLVBChannel", params);
				System.out.println(result);
				//解析数据
				JSONObject jsonObj = JSONObject.parseObject(result);
				//获取直播频道列表信息
				String channelInfoList = jsonObj.getString("channelInfo");
				//获取不到直播频道信息
				if (StringUtils.isEmpty(channelInfoList) && "4000".equals(jsonObj.get("code").toString())) {
					if ("20301".equals(jsonObj.get("codeDesc").toString())) {
						//同步主播信息
						System.out.println("................");
					}
				}
				//获取直播频道第一个信息
				JSONObject channelInfo = JSONObject.parseObject(JSONObject.parseArray(channelInfoList).getString(0));
				//获取直播频道状态0 无输入流 ，1   直播中， 2  异常，3  关闭',
				String channelStatus = channelInfo.getString("channel_status");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
