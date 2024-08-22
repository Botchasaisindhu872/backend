package com.example.Todo.service;

import com.example.Todo.dto.requestDto.CategoryRequestDTO;
import com.example.Todo.dto.responseDto.CategoryResponseDTO;
import com.example.Todo.dto.DTOmapper.request.CategoryRequestMapper;
import com.example.Todo.dto.DTOmapper.response.CategoryResponseMapper;
import com.example.Todo.repositories.RepositoryFactory;
import com.example.Todo.repositories.read.CategoryReadRepository;
import com.example.Todo.repositories.write.CategoryWriteRepository;
import com.example.Todo.model.Category;
import com.example.Todo.validations.CategoryValidations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface{
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryReadRepository categoryReadRepo;
    private final CategoryWriteRepository categoryWriteRepo;


    @Autowired
    public CategoryService(RepositoryFactory repositoryFactory) {
        this.categoryReadRepo = repositoryFactory.getCategoryReadRepo();
        this.categoryWriteRepo = repositoryFactory.getCategoryWriteRepo();
    }

    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryDTO) {
        logger.info("Adding new category with data: {}", categoryDTO);

        CategoryValidations.validateCategory(categoryDTO);
        Category category = CategoryRequestMapper.dTOToCategory(categoryDTO);

        try {
            categoryWriteRepo.save(category);
            logger.info("Category successfully added with ID: {}", category.getCategoryId());
        } catch (Exception e) {
            logger.error("Could not create Category", e);
            throw new RuntimeException("Could not create Category", e);
        }

        return CategoryResponseMapper.categoryToDTO(category);
    }
    @Override
    public CategoryResponseDTO updateCategoryById(Long id, CategoryRequestDTO newCategory) {
        logger.info("Updating category with ID: {} and new data: {}", id, newCategory);

        CategoryValidations.validateCategory(newCategory);
        Optional<Category> oldCategoryOpt = categoryReadRepo.findById(id);

        if (oldCategoryOpt.isPresent()) {
            Category oldCategory = oldCategoryOpt.get();
            oldCategory.setCategoryName(newCategory.getCategoryName());

            try {
                categoryWriteRepo.save(oldCategory);
                logger.info("Category successfully updated with ID: {}", id);
            } catch (Exception e) {
                logger.error("Could not update Category details", e);
                throw new RuntimeException("Could not update Category details", e);
            }
            return CategoryResponseMapper.categoryToDTO(oldCategory);
        }

        logger.error("Could not find category with ID: {}", id);
        throw new NoSuchElementException("Could not find category");
    }
@Override
    public List<CategoryResponseDTO> getAllCategories() {
        logger.info("Fetching all categories");

        List<Category> categoryList = categoryReadRepo.findAll();
        logger.info("Fetched all categories successfully");
        List<CategoryResponseDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categoryList) {
            categoryDTOList.add(CategoryResponseMapper.categoryToDTO(category));
        }

        return categoryDTOList;
    }
    @Override
    public CategoryResponseDTO getCategoryByID(Long c_id) {
        logger.info("Fetching category with ID: {}", c_id);

        Optional<Category> optCategoryObject = categoryReadRepo.findById(c_id);

        if (optCategoryObject.isPresent()) {
            Category category = optCategoryObject.get();
            logger.info("Fetched category with ID: {}", c_id);

            return CategoryResponseMapper.categoryToDTO(category);
        }

        logger.error("Could not find category with ID: {}", c_id);
        throw new NoSuchElementException("Could not find category");
    }

    @Override
    public CategoryResponseDTO deleteCategoryById(Long c_id) {
        logger.info("Deleting category with ID: {}", c_id);

        Optional<Category> optCategoryObject = categoryReadRepo.findById(c_id);

        if (optCategoryObject.isPresent()) {
            Category category = optCategoryObject.get();
            try {
                categoryWriteRepo.deleteById(c_id);
                logger.info("Category successfully deleted with ID: {}", c_id);
            } catch (Exception e) {
                logger.error("Could not delete category", e);
                throw new RuntimeException("Could not delete category", e);
            }
            return CategoryResponseMapper.categoryToDTO(category);
        }

        logger.error("Could not find category with ID: {}", c_id);
        throw new NoSuchElementException("Could not find category");
    }
}
