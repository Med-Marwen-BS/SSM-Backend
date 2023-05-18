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
    private final TeamRepository teamRepository;

    public Category addCategory(Category category){
        Optional<List<Category>> optionalCategory = categoryRepository.findByNameIgnoreCaseAndTeamId(category.getName(),category.getTeam());
        Optional<Team> team = teamRepository.findById(category.getTeam().getId());
        List<Category> c;
        if(optionalCategory.isPresent()){
            c = optionalCategory.get() ;
        }else throw new RuntimeException("categorie exist with this name");

        if(c.size()!=0) throw new RuntimeException("categorie exist with this name");
        if(team.isEmpty())throw new RuntimeException("the team is required");
        if(!category.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return categoryRepository.save(category) ;
    }
    public Category updateCategory(Category category)
    {
        Optional<Category> optionalTeam = categoryRepository.findById(category.getId());
        if(optionalTeam.isPresent()){
            Category toUpdate = optionalTeam.get() ;
            toUpdate.updateCategory(category);
            return categoryRepository.save(category) ;
        }

        return null ;

    }
    public List<Category> getAllCategories(String teamId){
        return categoryRepository.findByTeamId(teamId) ;
    }

    public Category findById(String  id){
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()) throw new RuntimeException("category not found");
        return category.get() ;
    }

}
