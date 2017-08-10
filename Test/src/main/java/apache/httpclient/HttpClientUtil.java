package apache.httpclient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {
    public static CloseableHttpClient createHttpClient() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(createSSLSocketFactory()).setMaxConnPerRoute(1).setMaxConnTotal(1).build();
        return httpClient;
    }

    private static LayeredConnectionSocketFactory createSSLSocketFactory() throws Exception {
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSv1.2").build();
        sslContext.init(null, new TrustManager[] { ignoreCertificationTrustManger }, null);
        return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager(){
        @Override
        public void checkClientTrusted(X509Certificate certificates[], String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
}
