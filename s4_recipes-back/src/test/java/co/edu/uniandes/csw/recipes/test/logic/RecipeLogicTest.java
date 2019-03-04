/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipes.test.logic;

import co.edu.uniandes.csw.recipes.ejb.RecipeLogic;
import co.edu.uniandes.csw.recipes.entities.RecipeEntity;
import co.edu.uniandes.csw.recipes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.recipes.persistence.RecipePersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author lm.gomezl
 */
@RunWith(Arquillian.class)
public class RecipeLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private RecipeLogic logic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private RecipeEntity r1;
    private RecipeEntity rMalo;

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from RecipeEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
       RecipeEntity buena = factory.manufacturePojo(RecipeEntity.class);
       buena.setName("Brownies");
       em.persist(buena);
       r1 = buena;
       
    }
        
    @Test
    public void createRecipeTest() throws BusinessLogicException{
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        RecipeEntity result = logic.createRecipe(newEntity);
        Assert.assertNotNull(result);
        RecipeEntity entity = em.find(RecipeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createSameNameRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("Brownies");
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createLongNameRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("Browniessssssssssssssssssssssssssssssssssssssssssssss");
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmptyNameRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName("");
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createNullNameRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setName(null);
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createLongDescriptionRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription("Browniesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
                + "esssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEmptyDescriptionRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription("");
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createNullDescriptionRecipeTest() throws BusinessLogicException {
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setDescription(null);
        RecipeEntity result = logic.createRecipe(newEntity);
    }
    
}
