package com.xmn.designer.controller.api.v1.image;


import com.xmn.designer.base.AbstractController;
import com.xmn.designer.entity.image.Image;
import com.xmn.designer.service.image.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create 2016/11/14
 *
 * @author yangQiang
 */

@Controller
@RequestMapping("api/v1/image")
public class ImageController extends AbstractController {

    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;


    /**
     * 图片上传接口
     * @param uploadRequest
     * @param httpServletRequest
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(MultipartHttpServletRequest request) throws IOException {
        logger.info("调用 [图片上传接口 - /api/v1/image], 请求参数:[" + "]");

        try {
            MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
            List<MultipartFile> files = multiFileMap.get("image");

            // 判断是否上传了文件
            if (files == null || files.size() < 1) {
                failure("未上传图片!");
                return;
            }

            for (MultipartFile file : files) {
                // 图片大小不能超过2MB
                if (file.getSize() > 2097152) {     // 1024*1024*2 = 2097152
                    failure("单个文件不能超过2MB");
                    return;
                }
                // 判断文件是否为图片
                if (!file.getContentType().matches("image/\\w+")) {
                    failure(file.getOriginalFilename() + "不是图片");
                    return;
                }
            }

            // 将图片上传到文件服务器
            final List<Image> imageList = imageService.upload(files);
            Map<String, Object> map = new HashMap<>();
            map.put("images", imageList);
            success(map);
        } catch (Exception e) {
            logger.error("图片上传接口出现异常!",e);
            failure(e.getMessage());
        }
    }

}
