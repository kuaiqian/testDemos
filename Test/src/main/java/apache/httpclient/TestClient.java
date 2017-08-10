package apache.httpclient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestClient {
    private static final Logger logger = LogManager.getLogger(TestClient.class);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long current = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CloseableHttpClient httpclient = HttpClientUtil.createHttpClient();
            Task task = new Task(httpclient);
            executorService.execute(task);
        }
        System.out.println("costTime=" + (System.currentTimeMillis() - current));
        executorService.shutdown();
    }

    static class Task implements Runnable {
        final CloseableHttpClient httpclient;

        public Task(CloseableHttpClient httpclient) {
            super();
            this.httpclient = httpclient;
        }

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            try {
                // https://127.0.0.1:8081/ https://httpbin.org/
                HttpGet httpget = new HttpGet("https://127.0.0.1:8081/");
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    HttpEntity entity = response.getEntity();
                    EntityUtils.toString(entity);
                }finally {
                    response.close();
                }
            }catch (Exception e) {
                logger.error(e);
            }
            System.out.println("sub.costTime=" + (System.currentTimeMillis() - current));
        }
    }
}
