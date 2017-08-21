package okhttp3;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Test;

public class TestClient {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void testGet() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { ignoreCertificationTrustManger }, null);
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).build();
        Request request = new Request.Builder().url("https://192.168.47.11:8081/").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void testPost() throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, "1111");
        Request request = new Request.Builder().url("https://192.168.47.11:8081/").post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static X509TrustManager ignoreCertificationTrustManger = new X509TrustManager(){
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
