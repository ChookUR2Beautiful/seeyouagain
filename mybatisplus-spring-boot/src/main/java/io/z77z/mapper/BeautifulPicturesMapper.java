package io.z77z.mapper;

import io.z77z.entity.BeautifulPictures;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author z77z
 * @since 2017-07-14
 */
public interface BeautifulPicturesMapper extends BaseMapper<BeautifulPictures> {

	List<BeautifulPictures> selectWhere(Map<String, Object> map);

}