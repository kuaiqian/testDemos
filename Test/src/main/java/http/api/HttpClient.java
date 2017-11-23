package http.api;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * 基于apache httpclient 4.3.6
 * 
 * @author chen.cheng
 * @date 2017-8-28
 */
public final class HttpClient {
    CloseableHttpClient client;

    public HttpClient(Builder builder) {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(builder.connectTimeOut)
                .setSocketTimeout(builder.socketTimeOut).build();
        HttpClientBuilder clientBuilder = HttpClients.custom().setDefaultRequestConfig(config)
                .setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE);
        if(builder.useSSL) {
            clientBuilder.setSSLSocketFactory(builder.sslConnectionSocketFactory);
        }
        client = clientBuilder.build();
    }

    public HttpResponse execute(HttpRequest httpRequest) {
        return null;
    }

    public static class Builder {
        CloseableHttpClient client;

        RequestConfig config;

        SSLConnectionSocketFactory sslConnectionSocketFactory;

        SSLContext sslContext;

        int socketTimeOut = 5000;

        int connectTimeOut = 3000;

        int maxConnPerRoute;

        int maxConnTotal = 2 * maxConnPerRoute;

        boolean useSSL;

        public HttpClient build() {
            return new HttpClient(this);
        }

        public void setClient(CloseableHttpClient client) {
            this.client = client;
        }

        public void setConfig(RequestConfig config) {
            this.config = config;
        }

        public void setSocketTimeOut(int socketTimeOut) {
            this.socketTimeOut = socketTimeOut;
        }

        public void setConnectTimeOut(int connectTimeOut) {
            this.connectTimeOut = connectTimeOut;
        }

        public void setMaxConnPerRoute(int maxConnPerRoute) {
            this.maxConnPerRoute = maxConnPerRoute;
        }

        public void setMaxConnTotal(int maxConnTotal) {
            this.maxConnTotal = maxConnTotal;
        }

        public void setUseSSL(boolean useSSL) {
            this.useSSL = useSSL;
            if(!this.useSSL) {
                return;
            }
            if(sslContext == null) {
                try {
                    sslContext = SSLContexts.custom().build();
                    sslContext.init(null, new TrustManager[] { new X509TrustManager(){
                        @Override
                        public void checkClientTrusted(X509Certificate certificates[], String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] ax509certificate, String s)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    } }, null);
                }catch (KeyManagementException e) {
                    // 异常忽略,后续会抛出运行时异常
                }catch (NoSuchAlgorithmException e) {
                    // 异常忽略,后续会抛出运行时异常
                }
            }
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }

        public void setSslContext(SSLContext sslContext) {
            this.sslContext = sslContext;
        }
    }
}
