package apache.httpclient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestClient {
    private static final Logger logger = LogManager.getLogger(TestClient.class);

    @Test
    public void testGet() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long current = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            CloseableHttpClient httpclient = HttpClientUtil.createHttpClient();
            GetTask task = new GetTask(httpclient);
            executorService.execute(task);
        }
        System.out.println("costTime=" + (System.currentTimeMillis() - current));
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdown();
    }

    @Test
    public void testPost() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long current = System.currentTimeMillis();
        CloseableHttpClient httpclient = HttpClientUtil.createHttpClient();
        PostTask task = new PostTask(httpclient);
        for (int i = 0; i < 1; i++) {
            executorService.execute(task);
        }
        System.out.println("costTime=" + (System.currentTimeMillis() - current));
        TimeUnit.SECONDS.sleep(50);
        executorService.shutdown();
    }

    static class GetTask implements Runnable {
        final CloseableHttpClient httpclient;

        public GetTask(CloseableHttpClient httpclient) {
            super();
            this.httpclient = httpclient;
        }

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            CloseableHttpResponse response = null;
            try {
                // https://127.0.0.1:8081/ https://httpbin.org/
                HttpGet httpget = new HttpGet("https://127.0.0.1:8081/");
                response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                EntityUtils.toString(entity);
            }catch (Exception e) {
                logger.error(e);
            }
            System.out.println("sub.costTime=" + (System.currentTimeMillis() - current));
        }
    }

    static class PostTask implements Runnable {
        final CloseableHttpClient httpclient;

        public PostTask(CloseableHttpClient httpclient) {
            this.httpclient = httpclient;
        }

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            CloseableHttpResponse response = null;
            try {
                // https://127.0.0.1:8081/ https://httpbin.org/
                HttpPost post = new HttpPost("https://127.0.0.1:8081/");
                post.setHeader("Content_Type", "text/plain; charset=utf-8");
                post.setHeader("OriIssrId", "1112222");
                StringEntity se = new StringEntity("123456", "utf-8");
                post.setEntity(se);
                response = httpclient.execute(post);
                HttpEntity entity = response.getEntity();
                EntityUtils.toString(entity);
            }catch (Exception e) {
                logger.error(e);
            }finally {
                try {
                    response.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("sub.costTime=" + (System.currentTimeMillis() - current));
        }
    }
}
