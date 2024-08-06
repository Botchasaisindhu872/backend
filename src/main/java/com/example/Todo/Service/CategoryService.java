package com.example.Todo.Service;

import com.example.Todo.DTO.requestDto.CategoryRequestDTO;
import com.example.Todo.DTO.responseDto.CategoryResponseDTO;
import com.example.Todo.DTOmapper.request.CategoryRequestMapper;
import com.example.Todo.DTOmapper.response.CategoryResponseMapper;
import com.example.Todo.Repositories.read.CategoryReadRepository;
import com.example.Todo.Repositories.write.CategoryWriteRepository;
import com.example.Todo.Model.Category;
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
        Category category= CategoryRequestMapper.dTOToCategory(categoryDTO);
        categoryWriteRepo.save(category);
        return CategoryResponseMapper.categoryToDTO(category);

    }

    public  CategoryResponseDTO  updateCategoryById (Long id ,CategoryRequestDTO newCategory){

        Optional<Category> oldCategoryOpt= categoryReadRepo.findById(id);
        Category oldCategory;
        if(oldCategoryOpt.isPresent()){
            oldCategory=oldCategoryOpt.get();
            oldCategory.setCategoryName(newCategory.getCategoryName());


            categoryWriteRepo.save(oldCategory);
            return CategoryResponseMapper.categoryToDTO(oldCategory);

            }



            return  new CategoryResponseDTO();

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
    return new CategoryResponseDTO();
}





public  CategoryResponseDTO deleteCategoryById(Long c_id){
    Optional<Category> optCategoryObject=categoryReadRepo.findById(c_id);

    if(optCategoryObject.isPresent()) {
        Category category = optCategoryObject.get();
        categoryWriteRepo.deleteById(c_id);

        return CategoryResponseMapper.categoryToDTO(category);
    }
    return new CategoryResponseDTO();
}


}
