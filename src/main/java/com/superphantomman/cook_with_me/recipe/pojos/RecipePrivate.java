package com.superphantomman.cook_with_me.recipe.pojos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.beans.Transient;


/**
 *  Recipe which can be seen only by author.
 *
 *
 *
 * */

@Getter
@Setter
@ToString
@Table(name = "recipe_private")
@NoArgsConstructor
@Entity(name = "recipe_private")
public class RecipePrivate extends Recipe {


    public RecipePrivate(String name, String description) {
        super(name, description);
    }
    @Transient
    @Override
    public State state() {
        return State.PRIVATE;
    }
}
