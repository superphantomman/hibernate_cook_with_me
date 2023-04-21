package com.superphantomman.cook_with_me.recipe.pojos;


import com.superphantomman.cook_with_me.ingredient.pojos.Ingredient;
import com.superphantomman.cook_with_me.ingredient.pojos.IngredientRecipe;
import com.superphantomman.cook_with_me.util.MeasurementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;



@Getter
@Setter
@ToString

@NoArgsConstructor
@Entity


public class Recipe {


    @Id
    private Long id;

    @Column(name = "description")
    private String description;



    //Being in possession of recipeInformation
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private RecipeInformation recipeInformation;


    @ToString.Exclude
    @OneToMany(mappedBy = "id.recipe", cascade = ALL, orphanRemoval = true)
    List<IngredientRecipe> ingredientRecipes = new ArrayList<>(7);


    public List<IngredientRecipe> getIngredientRecipes() {
        return new ArrayList<>(ingredientRecipes);
    }

    boolean addIngredient(Ingredient i, Float weight, MeasurementType measurementType) {
        if (i == null || weight == null || measurementType == null)
            throw new NullPointerException();
        if (weight.doubleValue() <= 0d)
            throw new IllegalArgumentException("Illegal argument for weigh parameter");

        if (i.state() == State.UNCONFIRMED && recipeInformation.state() == State.CONFIRMED)
            return false;


        final IngredientRecipe ir = new IngredientRecipe(this, i, weight, measurementType);
        System.out.println(ir);
        return addIngredient(ir);
    }

    /*
     * Remove first occurrence of element
     * */
    boolean removeIngredient(Ingredient i) {
        for (var ir : ingredientRecipes) {
            if (ir.getIngredient().equals(i))
                return ingredientRecipes.remove(ir);
        }
        return false;
    }

    boolean addIngredient(IngredientRecipe ir) {
        return ingredientRecipes.add(ir);

    }

    public Recipe(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return getId() != null && Objects.equals(getId(), recipe.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
