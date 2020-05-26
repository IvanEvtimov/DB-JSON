package ivan.json.service;

import ivan.json.domain.dtos.ProductsInRangeDto;
import ivan.json.domain.dtos.ProductsSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(ProductsSeedDto[] productsSeedDtos);

    List<ProductsInRangeDto> productsInRange(BigDecimal from, BigDecimal to);
}
