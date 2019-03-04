/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.ejb;

import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author CesarF
 */
@Stateless
public class RecipeLogic {
    @Inject
    private RecipePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public RecipeEntity getRecipe(Long id) {
        return persistence.find(id);
    }

    //TODO crear el método createRecipe
    public RecipeEntity createRecipe(RecipeEntity receta) throws BusinessLogicException {
         if(receta.getName().length() > 30 || receta.getName() == null || receta.getName().equals(""))
         {
             throw new BusinessLogicException("El nombre de la receta no puede ser vacio ni nulo y tampoco supera los 30 caracteres");
         }
         if(receta.getDescription().length() > 150 || receta.getDescription() == null || receta.getDescription().equals(""))
         {
            throw new BusinessLogicException("La descripcion de la receta no puede ser vacio ni nulo y tampoco supera los 150 caracteres");
         }
         if(persistence.findByName(receta.getName()) != null) {
             throw new BusinessLogicException("No pueden haber dos recetas con el mismo nombre");
         }
         if(receta.getIngrediente() == null)
         {
             throw new BusinessLogicException("Toda receta debe tener al menos un ingrediente");
         }
         return receta;
    }
    
}
