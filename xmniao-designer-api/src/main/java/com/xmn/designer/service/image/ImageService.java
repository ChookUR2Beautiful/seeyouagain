package com.xmn.designer.service.image;

import com.xmn.designer.entity.image.Image;
import com.xmn.designer.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    List<Image> upload(List<MultipartFile> files) throws CustomException;

    /**
     * 标记图片为已被使用, 否是图片过期会被清理, 导致无法访问
     * @param uri 图片地址
     * @throws CustomException
     */
    void useImage(String uri) ;
}