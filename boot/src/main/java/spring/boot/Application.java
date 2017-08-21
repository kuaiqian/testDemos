package spring.boot;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Application implements EmbeddedServletContainerCustomizer {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
        // throw new HTTPException(500);
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ResponseEntity<String> handle() throws URISyntaxException {
        URI location = new URI("https://127.0.0.1:8081/");
        return ResponseEntity.status(303).header("location", location.toString()).body("Hello World");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
    }
}
