package io.z77z.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;

import io.z77z.entity.BeautifulPictures;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author z77z
 * @since 2017-07-14
 */
@Service
public interface BeautifulPicturesService extends IService<BeautifulPictures> {

	List<BeautifulPictures> selectWhere(Map<String, Object> map);
	
}
