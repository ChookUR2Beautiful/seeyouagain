package com.xmniao.xmn.core.base.export.tag;

import org.apache.poi.ss.usermodel.Cell;

import com.xmniao.xmn.core.base.export.PoiTag;
import com.xmniao.xmn.core.base.export.bean.CellDefinition;


public class ValueNameTag implements PoiTag{

	@Override
	public CellDefinition parse(Cell cell, String tag) {
		CellDefinition cellDefinition =null;
		if(tag.startsWith(NAME_PREFIX)&&tag.endsWith(NAME_Suffix)){
			cellDefinition = new CellDefinition(cell, tag.substring(NAME_PREFIX.length(), tag.length()-NAME_Suffix.length()));
		}
		return cellDefinition;
	}

	

}
