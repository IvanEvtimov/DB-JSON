package ivan.json.web.controller;

import com.google.gson.Gson;
import ivan.json.domain.dtos.CategorySeedDto;
import ivan.json.domain.dtos.ProductsInRangeDto;
import ivan.json.domain.dtos.ProductsSeedDto;
import ivan.json.domain.dtos.UserSeedDto;
import ivan.json.service.CategoryService;
import ivan.json.service.ProductService;
import ivan.json.service.UserService;
import ivan.json.util.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class JsonController implements CommandLineRunner {

    private final static String USER_FILE_PATH = "C:\\Users\\DelP88J\\Desktop\\Proj\\json\\src\\main\\resources\\files\\users.json";
    private final static String CATEGORY_FILE_PATH = "C:\\Users\\DelP88J\\Desktop\\Proj\\json\\src\\main\\resources\\files\\categories.json";
    private final static String PRODUCT_FILE_PATH = "C:\\Users\\DelP88J\\Desktop\\Proj\\json\\src\\main\\resources\\files\\products.json";

    private final FileIOUtil fileIOUtil;
    private final UserService userService;
    private final Gson gson;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public JsonController(FileIOUtil fileIOUtil, UserService userService, Gson gson, CategoryService categoryService, ProductService productService) {
        this.fileIOUtil = fileIOUtil;
        this.userService = userService;
        this.gson = gson;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedUsers();
//        this.seedCategory();
        this.seedProducts();
//        this.productsInRange();
    }

    private void seedUsers() throws IOException {
        String fileContent = this.fileIOUtil.readFile(USER_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(fileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategory() throws IOException {
        String fileContent = this.fileIOUtil.readFile(CATEGORY_FILE_PATH);

        CategorySeedDto[] categorySeedDto = this.gson.fromJson(fileContent, CategorySeedDto[].class);

        this.categoryService.seedCategory(categorySeedDto);
    }

    private void seedProducts() throws IOException {
        String fileContent = this.fileIOUtil.readFile(PRODUCT_FILE_PATH);

        ProductsSeedDto[] productsSeedDtos = this.gson.fromJson(fileContent, ProductsSeedDto[].class);

        this.productService.seedProducts(productsSeedDtos);
    }

    private void productsInRange() throws IOException {

        List<ProductsInRangeDto> products = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String s = this.gson.toJson(products);

        File file = new File("C:\\Users\\DelP88J\\Desktop\\Proj\\json\\src\\main\\resources\\files\\products-in-range.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(s);
        fileWriter.close();

    }

}