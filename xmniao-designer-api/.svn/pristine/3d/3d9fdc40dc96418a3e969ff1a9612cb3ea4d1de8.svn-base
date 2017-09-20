/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.xmn.designer.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {

	public static String doGet(String url) throws Exception {
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();

		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		try {
			HttpGet httpget = new HttpGet(url);

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					return  EntityUtils.toString(entity, "UTF-8");
				}

			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		
		return null;
	}
	
	public static String doPost(String url) throws Exception {
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();

		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		try {
			HttpPost httppost = new HttpPost(url);

			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					return  EntityUtils.toString(entity, "UTF-8");
				}

			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		
		return null;
	}
	

	
	public static void main(String[] args) throws Exception{
		 //https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf22563034b1ca356 &secret=2cfc9ddf999b00e72feae165f42e9ae2&code=001vPrLG1DapCc0wopNG1tzpLG1vPrLZ&grant_type=authorization_code
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf22563034b1ca356&secret=2cfc9ddf999b00e72feae165f42e9ae2&code=021lzqaj175tki0aSU9j1Nnraj1lzqak&grant_type=authorization_code
		//String result = doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxf22563034b1ca356&secret=2cfc9ddf999b00e72feae165f42e9ae2&code=021lzqaj175tki0aSU9j1Nnraj1lzqak&grant_type=authorization_code");
		String url = "";
		String result = doGet(url);
		System.out.println(result);
	}

}
