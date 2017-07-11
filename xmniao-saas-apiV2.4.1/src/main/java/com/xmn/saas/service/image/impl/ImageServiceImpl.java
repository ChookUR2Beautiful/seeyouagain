package com.xmn.saas.service.image.impl;

import com.xmn.saas.base.GlobalConfig;
import com.xmn.saas.base.UploadClientFactory;
import com.xmn.saas.entity.image.Image;
import com.xmn.saas.exception.SaasException;
import com.xmn.saas.service.image.ImageService;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 图片服务实现类
 * create 2016/09/29
 *
 * @author yangQiang
 */

@Service("imageService")
public class ImageServiceImpl implements ImageService{
    // 初始化日志类
    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private GlobalConfig globalConfig;

    // 注入FastDfs客户端工厂
    @Autowired
    private UploadClientFactory uploadClientFactory;

    @Override
    public List<Image> upload(List<MultipartFile> files) throws SaasException {
        StorageClient1 client = uploadClientFactory.getStorageClients();
        List<Image> imageList = new ArrayList<>();

        try {
            // 将文件通过DFS客户端存入,返回URI
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                NameValuePair[] metaList = new NameValuePair[3];
                metaList[0] = new NameValuePair("fileName", filename);
                metaList[1] = new NameValuePair("fileExtName","jpg");
                metaList[2] = new NameValuePair("fileLength",file.getSize()+"");
                String fileUri = client.upload_file1(file.getBytes(), "", metaList);
                imageList.add(new Image(fileUri,globalConfig.getImageHost(),filename));
            }
            return imageList;
        } catch (Exception e) {
            logger.error("调用DFS服务器出现异常",e);
            throw new SaasException("图片上传内部异常!");
        } finally {
            uploadClientFactory.close();
        }
    }

    @Override
    public Map<String, List<Map<String, String>>> getMenus() throws IOException {
        Map<String, List<Map<String, String>>> menus = new LinkedHashMap<>();
        // 营销功能
        List<Map<String, String>> marketing = new ArrayList<>();
        // 其他功能
        List<Map<String, String>> other = new ArrayList<>();

        // 将两个分类加入到响应中
        menus.put("marketing",marketing);
        menus.put("other",other);

        // 获取properties文件
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("/properties/menu.properties");
        try(InputStream in = resource.getInputStream()){
            properties.load(in);

            // 遍历properties, 初始化Buffer容器
            /* buffer结构 : 每个菜单一条记录
            {
                菜单1 : {icon:"xx", id:"xx", url:"xx",cat:"xxx"...}
                菜单2 : {icon:"xx", id:"xx", url:"xx",cat:"xxx"...}
            }
             */
            Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
            HashMap<String, Map<String, String>> buffer = new HashMap<>();
            for (Map.Entry<Object, Object> entry : entrySet) {

                // properties key 例如 : redPackage.icon
                String key = entry.getKey().toString();
                // properties 属性头 例如 : redPackage
                String keyHead = key.split("\\.")[0];
                Map<String, String> keyMap = buffer.get(keyHead);
                if (keyMap == null) {
                    keyMap = new HashMap<>();
                    buffer.put(keyHead,keyMap);
                }
                keyMap.put(key.split("\\.")[1],entry.getValue().toString());
            }

            // 遍历 buffer容器(遍历每条菜单)
            for (Map.Entry<String, Map<String, String>> entry : buffer.entrySet()) {
                Map<String, String> value = entry.getValue();
                Map<String, String> map = new LinkedHashMap<>();

                String category = value.get("cat");
                Integer order = Integer.valueOf(value.get("order"));

                map.put("type",value.get("type"));
                map.put("name",value.get("name"));
                map.put("id",value.get("id"));
                map.put("url",value.get("url"));
                map.put("icon",value.get("icon"));

                // 表里每个菜单, 按照order排序插入到List中
                switch (category){
                    case "marketing" :
                        // 初始化容器大小
                        for(int i=marketing.size(); i<order; i++) {
                            marketing.add(null);
                        }
                        marketing.set(order-1,map);
                        break;
                    case "other" :
                        for(int i=other.size(); i<order; i++){
                            other.add(null);
                        }
                        other.set(order-1,map);
                        break;
                    default:
                        break;
                }
            }

        }

        return menus;

    }

}
