package com.superphantomman;

import com.superphantomman.cook_with_me.ingredient.pojos.Ingredient;
import com.superphantomman.cook_with_me.ingredient.pojos.IngredientConfirmed;
import com.superphantomman.cook_with_me.ingredient.pojos.IngredientUnconfirmed;
import com.superphantomman.cook_with_me.recipe.pojos.*;
import com.superphantomman.cook_with_me.util.MeasurementType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {


    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.superphantomman.cook_with_me");
    final static EntityManager em = emf.createEntityManager();


    public static void main(String[] args) {
//        var
        var t = em.getTransaction();

        List<RecipeInformation> rInformations = List.of(
                new RecipeInformationPrivate("sliced fruit", LocalDate.now()),
                new RecipeInformationUnconfirmed("sliced fruit unconfirmed", LocalDate.now()),
                new RecipeInformationConfirmed("sliced fruit confirmed", LocalDate.now())

        );

        List<Recipe> recipes = List.of(
                new Recipe("slice fruits"),
                new Recipe("slice fruits"),
                new Recipe("slice fruits")
        );

        List<Ingredient> ingredients = List.of(
                new IngredientUnconfirmed("apple", 100, MeasurementType.GRAM),
                new IngredientConfirmed("banana", 100, MeasurementType.GRAM)

        );


        t.begin();

        ingredients.forEach(em::persist);
        rInformations.forEach(em::persist);

        em.flush();
        t.commit();

        for (int i = 0; i < rInformations.size(); i++) {
            rInformations.get(i).setRecipe(recipes.get(i));
        }

        t.begin();

        recipes.forEach(em::persist);

        t.commit();


        t.begin();

        rInformations.forEach(ri -> {
            ri.addIngredient(ingredients.get(0), 10f, MeasurementType.GRAM);
            ri.addIngredient(ingredients.get(1), 10f, MeasurementType.GRAM);
        });


        t.commit();

        t.begin();
        rInformations.forEach( ri -> ri.removeIngredient(ingredients.get(0)));
        t.commit();


        em.close();

    }
}

