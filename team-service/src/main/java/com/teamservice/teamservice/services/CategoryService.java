package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.Player;
import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.repositories.CategoryRepository;
import com.teamservice.teamservice.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        if(category.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return categoryRepository.save(category) ;
    }
    public Category updateCategory(Category category)
    {
        Optional<Category> optionalTeam = categoryRepository.findById(category.getId());
        if(optionalTeam.isPresent()){
            Category toUpdate = optionalTeam.get() ;
            toUpdate.updateCategory(category);
            return toUpdate;
        }

        return null ;

    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll() ;
    }

    public Category findById(String  id){
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()) throw new RuntimeException("category not found");
        return category.get() ;
    }

}
