package com.superphantomman.cook_with_me.ingredient.pojos;


import com.superphantomman.cook_with_me.recipe.pojos.State;
import com.superphantomman.cook_with_me.util.MeasurementType;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@ToString
@NoArgsConstructor
@Table(name = "ingredient_confirmed")
@Entity(name = "ingredient_confirmed")

public class IngredientConfirmed extends Ingredient{
    public IngredientConfirmed(String name, Integer calories, MeasurementType measurementType) {
        super(name, calories, measurementType);
    }

    @Transient
    @Override
    public State state() {
        return State.CONFIRMED;
    }
}
