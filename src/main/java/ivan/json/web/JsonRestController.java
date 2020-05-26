//package ivan.json.web;
//
//import com.google.gson.Gson;
//import ivan.json.domain.dtos.ProductsInRangeDto;
//import ivan.json.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.List;
//
//@RestController
//public class JsonRestController {
//
//    private final Gson gson;
//    private final ProductService productService;
//
//    @Autowired
//    public JsonRestController(Gson gson, ProductService productService) {
//        this.gson = gson;
//        this.productService = productService;
//    }
//
//    @GetMapping("/")
//    private String productsInRange() throws IOException {
//
//        List<ProductsInRangeDto> products = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//        String s = this.gson.toJson(products);
//
//        return s;
//
//    }
//
//}
