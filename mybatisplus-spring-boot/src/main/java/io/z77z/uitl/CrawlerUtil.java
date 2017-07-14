package io.z77z.uitl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import com.alibaba.fastjson.JSONObject;

import io.z77z.entity.BeautifulPictures;
import io.z77z.entity.Picture;

public class CrawlerUtil {

	// 访问接口，返回json封装的数据格式
	public static JSONObject getReturnJson(String url) {
		try {
			URL httpUrl = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpUrl.openStream(), "UTF-8"));
			String line = null;
			String content = "";
			while ((line = in.readLine()) != null) {
				content += line;
			}
			in.close();
			return JSONObject.parseObject(content);
		} catch (Exception e) {
			System.err.println("访问失败:" + url);
			e.printStackTrace();
		}
		return null;
	}
	// 获取网站的document对象
	public static List<Picture> getArticleInfo(String url, BeautifulPictures beautifulPictures) {
		List<Picture> list = new ArrayList<>();
		try {
			Connection connect = Jsoup.connect(url);
			Document document;
			document = connect.get();
			//Elements article = document.getElementsByClass("article-content");
			//if (article.size() > 0) {
				Elements elementsByClass = document.getElementsByClass("cha");
				for (Element e : elementsByClass) {
					if("a".equals(e.tagName())){
							String url2 = e.attr("href");
							// 下载img标签里面的图片到本地
							saveToFile(url2);
							Picture picture = new Picture();
							picture.setId(beautifulPictures.getId());
							picture.setUrl(url2);
							list.add(picture);
					}
				}
			//}
			return list;
		} catch (IOException e) {
			System.err.println("访问文章页失败:" + url + "  原因" + e.getMessage());
			return null;
		}
	}
	
	// 通过url获取图片并保存在本地
	public static void saveToFile(String destUrl) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		String uuid = UUID.randomUUID().toString();
		String fileAddress = "D:\\imag/" + uuid;// 存储本地文件地址
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			String Type = httpUrl.getHeaderField("Content-Type");
			if (Type.equals("image/gif")) {
				fileAddress += ".gif";
			} else if (Type.equals("image/png")) {
				fileAddress += ".png";
			} else if (Type.equals("image/jpeg")) {
				fileAddress += ".jpg";
			} else {
				System.err.println("未知图片格式");
				return;
			}
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(fileAddress);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			System.out.println("图片保存成功！地址：" + fileAddress);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
}
