package com.xmn.saas.service.image;

import com.xmn.saas.entity.image.Image;
import com.xmn.saas.exception.SaasException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 图片服务类
 * create 2016/09/29
 *
 * @author yangQiang
 */
public interface ImageService {
    /**
     * 通过DFS客户端将图片上传到文件服务器
     * @param files
     * @return      图片信息列表
     */
    List<Image> upload(List<MultipartFile> files) throws SaasException;

    /**
     * 返回首页页面所需的图标
     * @return
     */
    Map<String, List<Map<String, String>>> getMenus() throws IOException;
}
