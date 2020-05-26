package ivan.json.service;

import ivan.json.domain.dtos.CategorySeedDto;
import ivan.json.domain.entities.Category;
import ivan.json.repository.CategoryRepository;
import ivan.json.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCategory(CategorySeedDto[] categorySeedDto) {

        for (CategorySeedDto seedDto : categorySeedDto) {
            if(!this.validatorUtil.isValid(seedDto)) {
                this.validatorUtil.violations(seedDto).forEach(v -> System.out.println(v.getMessage()));
                continue;
            }

            Category entity = this.modelMapper.map(seedDto, Category.class);
            this.categoryRepository.saveAndFlush(entity);
        }

    }
}
