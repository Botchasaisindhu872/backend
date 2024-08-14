package com.example.Todo.Service;

import com.example.Todo.DTO.requestDto.CategoryRequestDTO;
import com.example.Todo.DTO.responseDto.CategoryResponseDTO;
import com.example.Todo.DTO.DTOmapper.request.CategoryRequestMapper;
import com.example.Todo.DTO.DTOmapper.response.CategoryResponseMapper;
import com.example.Todo.Exceptions.CategoryException;
import com.example.Todo.Repositories.read.CategoryReadRepository;
import com.example.Todo.Repositories.write.CategoryWriteRepository;
import com.example.Todo.Model.Category;
import com.example.Todo.Validations.CategoryValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private  CategoryWriteRepository categoryWriteRepo;
    @Autowired
    private  CategoryReadRepository categoryReadRepo;
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryDTO){
        CategoryValidations.validateCategory(categoryDTO);
        Category category= CategoryRequestMapper.dTOToCategory(categoryDTO);
        try {
            categoryWriteRepo.save(category);
        }
        catch(Exception e){
            throw new CategoryException("Could not create Category",e);
        }
        return CategoryResponseMapper.categoryToDTO(category);

    }

    public  CategoryResponseDTO  updateCategoryById (Long id ,CategoryRequestDTO newCategory){
        CategoryValidations.validateCategory(newCategory);
        Optional<Category> oldCategoryOpt= categoryReadRepo.findById(id);
        Category oldCategory;
        if(oldCategoryOpt.isPresent()){
            oldCategory=oldCategoryOpt.get();
            oldCategory.setCategoryName(newCategory.getCategoryName());


            try {
                categoryWriteRepo.save(oldCategory);
            }
            catch(Exception e){
                throw new CategoryException("Could not update Category details",e);
            }
            return CategoryResponseMapper.categoryToDTO(oldCategory);

            }



           throw new CategoryException("Could not be able to find category");

    }


    public  List<CategoryResponseDTO>  getAllCategories(){
        List<Category> categoryList=categoryReadRepo.findAll();
        List<CategoryResponseDTO> categoryDTOList =new ArrayList<>();
        for (Category category : categoryList ){
            categoryDTOList.add(CategoryResponseMapper.categoryToDTO(category));

        }

        return categoryDTOList;

        }



public  CategoryResponseDTO getCategoryByID(Long c_id){
    Optional<Category> optCategoryObject=categoryReadRepo.findById(c_id);

    if(optCategoryObject.isPresent()) {
        Category category = optCategoryObject.get();

        return CategoryResponseMapper.categoryToDTO(category);
    }
    throw new CategoryException("Could not find category");
}





public  CategoryResponseDTO deleteCategoryById(Long c_id){
    Optional<Category> optCategoryObject=categoryReadRepo.findById(c_id);

    if(optCategoryObject.isPresent()) {
        Category category = optCategoryObject.get();
        try {
            categoryWriteRepo.deleteById(c_id);
        }
        catch(Exception e){
            throw new CategoryException("Could not delete category",e);
        }

        return CategoryResponseMapper.categoryToDTO(category);
    }
    throw new CategoryException("Could not find category");
}


}
