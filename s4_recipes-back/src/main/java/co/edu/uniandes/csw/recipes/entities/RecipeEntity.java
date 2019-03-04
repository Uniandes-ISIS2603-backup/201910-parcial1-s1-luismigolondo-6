/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author CesarF
 */
@Entity
public class RecipeEntity extends BaseEntity {
    private String name;
    private String description;
    
    @OneToOne()
    private IngredientEntity ingrediente;
    
    public RecipeEntity(){
    
    }
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the ingrediente
     */
    public IngredientEntity getIngrediente() {
        return ingrediente;
    }

    /**
     * @param ingrediente the ingrediente to set
     */
    public void setIngrediente(IngredientEntity ingrediente) {
        this.ingrediente = ingrediente;
    }
    
    
}
