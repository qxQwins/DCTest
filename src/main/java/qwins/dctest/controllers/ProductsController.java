package qwins.dctest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qwins.dctest.models.Product;
import qwins.dctest.services.ProductElasticService;
import qwins.dctest.services.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductsController {

    private final ProductElasticService productElasticService;

    private final ProductService productService;

    public ProductsController(ProductElasticService productElasticService, ProductService productService) {
        this.productElasticService = productElasticService;
        this.productService = productService;
    }

    @GetMapping("/reindex")
    public String reindex() throws IOException {
        List<Product> products = productService.getAllProducts();
        productElasticService.createNestedIndex();
        for(Product p : products){
            productElasticService.saveProduct(p);
        }
        return "reindex";
    }

    @GetMapping("/search")
    public String searchGet(){
        return "search";
    }
    @PostMapping("/search")
    public String searchPost(Model model,
                             @RequestParam String request) throws IOException {
        model.addAttribute("products", productElasticService.searchProducts(request));
        return "search";
    }
}
