/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.persistence;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author CesarF
 */

@Stateless
public class RecipePersistence {
    private static final Logger LOGGER = Logger.getLogger(RecipePersistence.class.getName());
    
    @PersistenceContext(unitName = "recipesPU")
    protected EntityManager em;
    
  
    public RecipeEntity find(Long id) {
        return em.find(RecipeEntity.class, id);
    }
       
    //TODO método crear de recipe
    public RecipeEntity createRecipe(RecipeEntity entidad) {
        em.persist(entidad);
        return entidad;
    }
    
    public RecipeEntity findByName(String nombre) {
        TypedQuery query = em.createQuery("Select u from RecipeEntity u where u.name = :name", RecipeEntity.class);
        query = query.setParameter("name", nombre);
        List<RecipeEntity> lista = query.getResultList();
        RecipeEntity resultado;
        if(lista == null) 
        {
            resultado = null;
        } 
        else if (lista.isEmpty()) 
        {
            resultado = null;
        } 
        else 
        {
            resultado = lista.get(0);
        }
        return resultado;
    }
    
}
