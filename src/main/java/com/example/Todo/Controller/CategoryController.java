package com.example.Todo.Controller;

import com.example.Todo.DTO.requestDto.CategoryRequestDTO;
import com.example.Todo.DTO.responseDto.CategoryResponseDTO;
import com.example.Todo.Model.Category;
import com.example.Todo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/categories")
public class CategoryController {
    //get mappings
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){

        List<CategoryResponseDTO> categoryDTOList = categoryService.getAllCategories();
        if(categoryDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(categoryDTOList, HttpStatus.OK);


    }


    @GetMapping("/{c_id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByID(@PathVariable Long c_id){
        CategoryResponseDTO categoryDTO= categoryService.getCategoryByID(c_id);
        return  new ResponseEntity<>(categoryDTO, HttpStatus.OK);


    }
    //post mappings

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryData){

        CategoryResponseDTO categoryDTO= categoryService.addCategory(categoryData);
        return  new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }



    @PutMapping("/{c_id}")
    public ResponseEntity<CategoryResponseDTO> updateByID(@PathVariable Long c_id,@RequestBody CategoryRequestDTO newCategoryData){

        CategoryResponseDTO categoryDTO= categoryService.updateCategoryById(c_id,newCategoryData);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);

    }




    //delete mappings
    @DeleteMapping("/{c_id}")
    public ResponseEntity<CategoryResponseDTO> deleteCategoryByID(@PathVariable Long c_id){

        CategoryResponseDTO categoryDTO= categoryService.deleteCategoryById(c_id);
        return  new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }



}