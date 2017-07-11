package com.xmn.saas.entity.image;

/**
 * create 2016/09/30
 *
 * @author yangQiang
 */

public class Image {

    // 图片的URI 例如:/img/M00/02/16/XXXXXXXX
    private String uri;

    // 图片的URL,可以直接访问 例如:http://gzdev.xmniao.com:88/img/M00/02/16/XXXXXXXX
    private String url;

    // 图片上传时的原图片名
    private String filename;

    public Image(String uri, String url, String filename) {
        this.uri = uri;
        this.url = url + uri;
        // 替换掉后缀名
        this.filename = filename.replaceAll("\\.\\w+$","");
    }

    public Image() {
        super();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
