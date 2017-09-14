package com.javmin.mapper;

import com.javmin.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Yanghu
 * @since 2017-08-03
 */
public interface SysUserMapper {

	SysUser getObject(Long i);

}