package ivan.json.service;

import ivan.json.domain.dtos.CategorySeedDto;

public interface CategoryService {

    void seedCategory(CategorySeedDto[] categorySeedDto);
}
