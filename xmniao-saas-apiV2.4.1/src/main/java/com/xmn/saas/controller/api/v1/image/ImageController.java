package com.xmn.saas.controller.api.v1.image;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;
import com.xmn.saas.controller.api.v1.image.vo.UploadRequest;
import com.xmn.saas.entity.image.Image;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.service.image.ImageService;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("api-v1-image-controller")
@RequestMapping("api/v1/image")
public class ImageController extends AbstractController{

    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private RedisService redisService;

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
    public void upload(UploadRequest uploadRequest
            ,HttpServletRequest httpServletRequest) throws IOException {
        logger.info("[调用图片上传接口 - /api/v1/image], 请求参数:[" + uploadRequest.toString() + "]");

        try {
            String sessionToken = uploadRequest.getSessionToken();

            // 验证sessionToken
            if ( StringUtils.isEmpty(sessionToken)
                    || !redisService.checkSessionCacheObject(sessionToken)) {
                new Response(ResponseCode.TOKENERR, "会话令牌错误或过期!").write();
                return;
            }

            // 获取上传的文件
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
            List<MultipartFile> files = multiFileMap.get("image");

            // 判断是否上传了文件
            if (files == null || files.size() < 1) {
                new Response(ResponseCode.FAILURE, "未上传图片!").write();
                return;
            }

            for (MultipartFile file : files) {
                // 图片大小不能超过2MB
                if (file.getSize() > 2097152) {     // 1024*1024*2 = 2097152
                    new Response(ResponseCode.FAILURE, "单个文件不能超过2MB").write();
                    return;
                }
                // 判断文件是否为图片
                if (!file.getContentType().matches("image/\\w+")) {
                    new Response(ResponseCode.FAILURE, file.getOriginalFilename() + "不是图片").write();
                    return;
                }
            }

            // 将图片上传到文件服务器
            final List<Image> imageList = imageService.upload(files);
            Map<String, Object> map = new HashMap<>();
            map.put("images", imageList);
            new Response(ResponseCode.SUCCESS, "图片上传成功!", map).write();

        } catch (SaasException e) {
            new Response(ResponseCode.FAILURE,e.getMessage()).write();
        } catch (Exception e) {
            logger.error("图片上传接口出现异常!",e);
            new Response(ResponseCode.FAILURE,"失败!").write();
        }


    }

}
