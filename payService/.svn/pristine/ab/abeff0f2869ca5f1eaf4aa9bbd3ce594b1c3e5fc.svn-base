package com.xmniao.wpay;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;


public class SSLContextBuilderUtil {

    static final String TLS   = "TLS";
    static final String SSL   = "SSL";

    private String protocol;
    private Set<KeyManager> keymanagers;
    private Set<TrustManager> trustmanagers;
    private SecureRandom secureRandom;

    

    public SSLContextBuilderUtil() {
		super();
        this.keymanagers = new LinkedHashSet<KeyManager>();
        this.trustmanagers = new LinkedHashSet<TrustManager>();
	}

	public SSLContextBuilderUtil loadKeyMaterial(
            final KeyStore keystore,
            final char[] keyPassword)
            throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        final KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        kmfactory.init(keystore, keyPassword);
        final KeyManager[] kms =  kmfactory.getKeyManagers();
        if (kms != null) {
            for (final KeyManager km : kms) {
                keymanagers.add(km);
            }
        }
        return this;
    }

    public SSLContext build() throws NoSuchAlgorithmException, KeyManagementException {
        final SSLContext sslcontext = SSLContext.getInstance(
                this.protocol != null ? this.protocol : TLS);
        sslcontext.init(
                !keymanagers.isEmpty() ? keymanagers.toArray(new KeyManager[keymanagers.size()]) : null,
                !trustmanagers.isEmpty() ? trustmanagers.toArray(new TrustManager[trustmanagers.size()]) : null,
                secureRandom);
        return sslcontext;
    }
}
