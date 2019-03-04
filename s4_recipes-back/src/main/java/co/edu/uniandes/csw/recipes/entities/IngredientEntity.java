/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.entities;

import java.io.Serializable;
import javax.persistence.OneToOne;

/**
 *
 * @author estudiante
 */
public class IngredientEntity extends BaseEntity implements Serializable{
    private String name;
    private Long calories;
    
    @OneToOne()
    private RecipeEntity receta;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the calories
     */
    public Long getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(Long calories) {
        this.calories = calories;
    }

    /**
     * @return the receta
     */
    public RecipeEntity getReceta() {
        return receta;
    }

    /**
     * @param receta the receta to set
     */
    public void setReceta(RecipeEntity receta) {
        this.receta = receta;
    }
    
    
}
