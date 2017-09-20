package com.xmniao.xmn.core.base.export.tag;

import org.apache.poi.ss.usermodel.Cell;

import com.xmniao.xmn.core.base.export.bean.CellDefinition;
import com.xmniao.xmn.core.util.StringUtils;

public class ListTag implements com.xmniao.xmn.core.base.export.PoiTag{
	
	
	

	@Override
	public CellDefinition parse(Cell cell, String tag) {
		CellDefinition cellDefinition =null;
		if(tag.startsWith(NAME_PREFIX_START)){
			String[] values = StringUtils.paresToArray(tag, " ");
			if(values.length==4){
				cellDefinition = new CellDefinition(cell, values[3].substring(NAME_PREFIX.length(), values[3].length()-NAME_Suffix.length()), values[1]);
			}
		}
		return cellDefinition;
	}

}
