package com.superphantomman.cook_with_me.recipe.pojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Recipe which was marked by user to be published
 * and is waiting for confirmation by moderator
 *
 * */

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "recipe_unconfirmed")
@Entity(name = "recipe_unconfirmed")
public class RecipeUnconfirmed extends Recipe {

    @Transient
    @Override
    public State state() {
        return State.UNCONFIRMED;
    }

    public RecipeUnconfirmed(String name, String description) {
        super(name, description);
    }
    public RecipeUnconfirmed(RecipePrivate recipePrivate){
        super(recipePrivate.getName(), recipePrivate.getDescription());
    }

}
