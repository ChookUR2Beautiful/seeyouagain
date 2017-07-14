package io.z77z.service.impl;

import io.z77z.entity.BeautifulPictures;
import io.z77z.mapper.BeautifulPicturesMapper;
import io.z77z.service.BeautifulPicturesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author z77z
 * @since 2017-07-14
 */
@Service
public class BeautifulPicturesServiceImpl extends ServiceImpl<BeautifulPicturesMapper, BeautifulPictures> implements BeautifulPicturesService {
	
	@Override
	public List<BeautifulPictures> selectWhere(Map<String, Object> map) {
		BeautifulPicturesMapper mapper =  this.baseMapper;
		return mapper.selectWhere(map);
	}
	
}
