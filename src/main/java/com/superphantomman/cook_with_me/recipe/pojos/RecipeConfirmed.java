package com.superphantomman.cook_with_me.recipe.pojos;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Getter
@Setter
@ToString

/**
 * Recipe confirmed by moderators for displaying to other users
 * */

@NoArgsConstructor
@Table(name = "recipe_confirmed")
@Entity(name = "recipe_confirmed")
public class RecipeConfirmed extends Recipe {


    public RecipeConfirmed(
            RecipeUnconfirmed recipeUnconfirmed) {
        super(recipeUnconfirmed.getName(), recipeUnconfirmed.getDescription());
    }

    @Transient
    @Override
    public State state() {
        return State.CONFIRMED;
    }
}
