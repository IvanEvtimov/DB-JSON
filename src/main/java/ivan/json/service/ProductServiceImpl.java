package ivan.json.service;

import ivan.json.domain.dtos.ProductsInRangeDto;
import ivan.json.domain.dtos.ProductsSeedDto;
import ivan.json.domain.entities.Category;
import ivan.json.domain.entities.Product;
import ivan.json.domain.entities.User;
import ivan.json.repository.CategoryRepository;
import ivan.json.repository.ProductRepository;
import ivan.json.repository.UserRepository;
import ivan.json.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductsSeedDto[] productsSeedDtos) {
        for (ProductsSeedDto productsSeedDto : productsSeedDtos) {
            if (!this.validatorUtil.isValid(productsSeedDto)) {
                this.validatorUtil.violations(productsSeedDto).forEach(violation -> System.out.println(violation.getMessage()));
                continue;
            }

            Product product = this.modelMapper.map(productsSeedDto, Product.class);
            product.setSeller(this.getRandomUser());

            Random random = new Random();
            if (random.nextInt() % 13 != 0) {
                product.setBuyer(this.getRandomUser());
            }

            product.setCategories(this.getRandomCategories());

            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public List<ProductsInRangeDto> productsInRange(BigDecimal from, BigDecimal to) {
        List<Product> productsEntity = this.productRepository.findAllByPriceBetweenAndBuyerOrderByPrice(from, to, null);
        List<ProductsInRangeDto> products = new ArrayList<>();

        for (Product productEntity : productsEntity) {
            ProductsInRangeDto product = this.modelMapper.map(productEntity, ProductsInRangeDto.class);
            product.setSeller(String.format("%s %s", productEntity.getSeller().getFirstName(), productEntity.getSeller().getLastName()));
            products.add(product);
        }

        return products;
    }

    private  User getRandomUser() {
        Random random = new Random();

        return this.userRepository.getOne(random.nextInt((int)this.userRepository.count()-1)+1);
    }

    private List<Category> getRandomCategories() {
        Random random = new Random();

        List<Category> categories = new ArrayList<>();

        int categoriesCount = random.nextInt((int)this.categoryRepository.count()-1) + 1;

        for (int i = 0; i < categoriesCount ; i++) {
            categories.add(this.categoryRepository.getOne(random.nextInt((int)this.categoryRepository.count()-1) + 1));
        }
        return categories;
    }
}
