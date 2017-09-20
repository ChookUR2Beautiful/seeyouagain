package com.xmn.designer.service.postage.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.oned.CodaBarReader;
import com.xmn.designer.constants.DesignerConsts;
import com.xmn.designer.dao.material.MaterialDao;
import com.xmn.designer.dao.postage.PostageConditionsDao;
import com.xmn.designer.dao.postage.PostageTemplateDao;
import com.xmn.designer.entity.material.Material;
import com.xmn.designer.entity.postage.PostageConditions;
import com.xmn.designer.entity.postage.PostageTemplate;
import com.xmn.designer.service.postage.PostageService;
@Service
public class PostageServiceImpl implements PostageService{
	@Autowired
	private PostageConditionsDao postageConditionsDao;
	
	@Autowired
	private MaterialDao materialDao;
	
	@Autowired
	private PostageTemplateDao postageTemplateDao;
	
	
	@Override
	public PostageConditions getCondition(Long materialId, Integer areaId) {
		List<PostageConditions> postageConditions= postageConditionsDao.selectCondition(materialId,areaId);
		if(postageConditions==null||postageConditions.size()==0){
			return null;
		}
		return postageConditions.get(0);
	}

	@Override
	public BigDecimal calculateCondittion(Long materialId, Integer areaId,Integer num) {
		PostageConditions condition = getCondition(materialId,areaId);
		PostageTemplate template = postageTemplateDao.selectByPrimaryKey(condition.getTemplateId());
		String type = template.getType();
		if(DesignerConsts.POSTAGE_CONDITIONS_TYPE_WEIGHT.equals(type)){
			Material material = materialDao.selectByPrimaryKey(materialId);
			num=num*material.getWeight().intValue();
		}
		if(num<=condition.getFirstNum()){
			return condition.getFirstItem();
		}else{
			//计算续件数量
			Integer continuedNum=(num-condition.getFirstNum())/condition.getContinuedNum()+((num-condition.getFirstNum())%condition.getContinuedNum()>0?1:0);
			//首件价格加上续件价格
			return condition.getFirstItem().add(condition.getContinuedItem().multiply(new BigDecimal(continuedNum)));
		}
	}

}
