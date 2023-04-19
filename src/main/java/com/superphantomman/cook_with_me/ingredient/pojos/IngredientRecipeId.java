package com.superphantomman.cook_with_me.ingredient.pojos;

import com.superphantomman.cook_with_me.recipe.pojos.Recipe;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class IngredientRecipeId implements Serializable {


    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;


}
