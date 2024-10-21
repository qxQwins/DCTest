package qwins.dctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import qwins.dctest.models.Product;
import qwins.dctest.services.ProductElasticService;
import qwins.dctest.services.ProductService;
import qwins.dctest.services.SKUService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DcTestApplication {
    public static void main(String[] args) throws IOException {
        var context = SpringApplication.run(DcTestApplication.class, args);
    }
}
