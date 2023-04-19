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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;

/**
 * Recipe represent general recipe which not include state.
 * id Number that will identify all recipes no matter of state;
 * name String which represent name of recipe given by user
 * description String which represent description given by user
 * creation_date Date of submitting forms by user
 */

@Getter
@Setter
@ToString

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private LocalDate creationDate;



    @ToString.Exclude
    @OneToMany(mappedBy = "id.recipe", cascade = ALL, orphanRemoval = true)
    List<IngredientRecipe> ingredientRecipes = new ArrayList<>(7);

    @Transient
    public abstract State state();


    public List<IngredientRecipe> getIngredientRecipes() {
        return new ArrayList<>(ingredientRecipes);
    }

    public boolean addIngredient(Ingredient i, Float weight, MeasurementType measurementType) {
        if (i == null ||  weight == null || measurementType == null)
            throw new NullPointerException();
        if(weight.doubleValue() <= 0d)
            throw new IllegalArgumentException("Illegal argument for weigh parameter");

        if (i.state() == State.UNCONFIRMED && this.state() == State.CONFIRMED)
            return false;


        final IngredientRecipe ir = new IngredientRecipe(this, i, weight, measurementType);
        System.out.println(ir);
        return addIngredient(ir);
    }

    /*
     * Remove first occurrence of element
     * */
    public boolean removeIngredient(Ingredient i) {
        for (var ir : ingredientRecipes) {
            if (ir.getIngredient().equals(i))
                return ingredientRecipes.remove(ir);
        }
        return false;
    }

    public boolean addIngredient(IngredientRecipe ir) {
        return ingredientRecipes.add(ir);

    }

    public Recipe(String name, String description) {
        this.name = name;
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
