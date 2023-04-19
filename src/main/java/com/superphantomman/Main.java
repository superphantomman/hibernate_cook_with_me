package com.superphantomman;

import com.superphantomman.cook_with_me.ingredient.pojos.IngredientConfirmed;
import com.superphantomman.cook_with_me.ingredient.pojos.IngredientUnconfirmed;
import com.superphantomman.cook_with_me.recipe.pojos.RecipeConfirmed;
import com.superphantomman.cook_with_me.recipe.pojos.RecipePrivate;
import com.superphantomman.cook_with_me.recipe.pojos.RecipeUnconfirmed;
import com.superphantomman.cook_with_me.util.MeasurementType;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.superphantomman.cook_with_me");


    public static void main(String[] args) {
        var em = emf.createEntityManager();
        var t = em.getTransaction();
        RecipePrivate recipePrivate =
                new RecipePrivate("sandwich", "under construction");
        RecipeUnconfirmed recipeUnconfirmed =
                new RecipeUnconfirmed(new RecipePrivate("chesse", "to publish but not confirmed"));
        RecipeConfirmed recipeConfirmed =
                new RecipeConfirmed(new RecipeUnconfirmed("sandwich with fish", "confirmed and to be publish"));


        IngredientUnconfirmed ingredientUnconfirmed = new IngredientUnconfirmed("apple", 100 , MeasurementType.GRAM);
        IngredientConfirmed ingredientConfirmed = new IngredientConfirmed("banana", 100, MeasurementType.GRAM);

        t.begin();
        em.persist(ingredientConfirmed);
        em.persist(ingredientUnconfirmed);
        em.persist(recipePrivate);
        em.persist(recipeUnconfirmed);
        em.persist(recipeConfirmed);
        em.flush();
        t.commit();



        t.begin();
        recipePrivate.addIngredient(ingredientUnconfirmed, 10f , MeasurementType.GRAM);
        recipePrivate.addIngredient(ingredientConfirmed, 10f , MeasurementType.GRAM);
        recipeUnconfirmed.addIngredient(ingredientUnconfirmed, 10f , MeasurementType.GRAM);
        recipeUnconfirmed.addIngredient(ingredientConfirmed, 10f , MeasurementType.GRAM);
        recipeConfirmed.addIngredient(ingredientUnconfirmed, 10f , MeasurementType.GRAM);
        recipeConfirmed.addIngredient(ingredientConfirmed, 10f , MeasurementType.GRAM);

        t.commit();






        em.close();

    }
}

