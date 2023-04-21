package com.superphantomman.cook_with_me.recipe.pojos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;


@Getter
@Setter
@ToString

/**
 * Recipe confirmed by moderators for displaying to other users
 * */

@NoArgsConstructor
@Table(name = "recipe_confirmed_information")
@Entity(name = "RecipeInformationConfirmed")
public class RecipeInformationConfirmed extends RecipeInformation {


    public RecipeInformationConfirmed(String name, LocalDate creationDate) {
        super(name, creationDate);
    }

    @Transient
    @Override
    public State state() {
        return State.CONFIRMED;
    }
}
