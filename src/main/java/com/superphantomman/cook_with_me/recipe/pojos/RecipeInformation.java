package com.superphantomman.cook_with_me.recipe.pojos;


import com.superphantomman.cook_with_me.ingredient.pojos.Ingredient;
import com.superphantomman.cook_with_me.ingredient.pojos.IngredientRecipe;
import com.superphantomman.cook_with_me.profile.Profile;
import com.superphantomman.cook_with_me.util.MeasurementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "recipe_information")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

abstract public class RecipeInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "creation_date")
    private LocalDate creationDate;


    //Owned side by RecipeInformation
    @OneToOne(mappedBy = "recipeInformation")
    private Recipe recipe;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.setRecipeInformation(this);
    }

    public boolean addIngredient(Ingredient i, Float weight, MeasurementType mt){
        return recipe.addIngredient(i, weight, mt );
    }

    public boolean addIngredient (IngredientRecipe ir){
        return recipe.addIngredient(ir);
    }

    public boolean removeIngredient(Ingredient i){
        return  recipe.removeIngredient(i);
    }

    public RecipeInformation(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    @Transient
    public abstract State state();
}
