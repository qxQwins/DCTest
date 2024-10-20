package qwins.dctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import qwins.dctest.services.ProductService;
import qwins.dctest.services.SKUService;

@SpringBootApplication
public class DcTestApplication {



    public static void main(String[] args)
    {
        var context = SpringApplication.run(DcTestApplication.class, args);
        var productService = context.getBean(ProductService.class);
        var skuService = context.getBean(SKUService.class);
        System.out.println(skuService.findAll());
    }
}
