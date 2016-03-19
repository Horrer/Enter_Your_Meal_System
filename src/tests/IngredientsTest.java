package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.AlreadyExistingIngredientException;
import Exceptions.WritingInFileErrorException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import enteryourmealsystem.Ingredients;

/**
 * @author Pierre
 *
 */
public class IngredientsTest{
	
	/**
	 * @param args
	 * @throws IOException
	 * @throws AlreadyExistingIngredientException
	 * 
	 * This test was first made to see if adding and then removing an ingredient works
	 * @throws WritingInFileErrorException 
	 * 
	 */
	public static void main(String[] args) throws IOException, AlreadyExistingIngredientException, WritingInFileErrorException {
		IngredientsTest IT = new IngredientsTest();
		IT.testAddIngredient();
	}
	
	
	/**
	 * @throws WritingInFileErrorException
	 * @throws IOException
	 * @throws AlreadyExistingIngredientException
	 * 
	 * This test expects "pomme 1.0 0.5" in the textfile and checks if that's what is happening
	 * 
	 */
	@Test
	public void testAddIngredient() throws WritingInFileErrorException, IOException, AlreadyExistingIngredientException {
		Ingredients ingr�dient = new Ingredients();
		ingr�dient.name =  "pomme";
		ingr�dient.quantity = 1.0;
		ingr�dient.priceForThisQuantity = 0.5;
		ingr�dient.removeIngredient("pomme"); //pour enlever les occurences pr�c�dentes
		Ingredients.addIngredient(ingr�dient.name, ingr�dient.quantity, ingr�dient.priceForThisQuantity);
		
		String expected = "pomme 1.0 0.5";
		String fichier =ingr�dient.fichier;
			
			//lecture du fichier texte	
		InputStream ips=new FileInputStream(fichier); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		try{
			String ligne;
			ligne=br.readLine();
			assertEquals(expected, ligne);
			}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			br.close(); 
		}

	}
	@Test
	public void testRemoveIngredient() throws IOException, AlreadyExistingIngredientException{
		Ingredients ingr�dient = new Ingredients();
		ingr�dient.name =  "pomme";
		ingr�dient.quantity = 1.0;
		ingr�dient.priceForThisQuantity = 0.5;
		ingr�dient.removeIngredient("pomme"); //pour enlever les occurences pr�c�dentes
		Ingredients.addIngredient(ingr�dient.name, ingr�dient.quantity, ingr�dient.priceForThisQuantity);
		Ingredients.removeIngredient(ingr�dient.name);
		
		String fichier =ingr�dient.fichier;
		
		InputStream ips=new FileInputStream(fichier); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		try{
			String ligne;
			ligne=br.readLine();
			assertNull(ligne);
			}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			br.close(); 
		}
		
	}
	
	@Test(expected = AlreadyExistingIngredientException.class)
	public void testTwiceSameIngredient() throws IOException, AlreadyExistingIngredientException {
		Ingredients ingr�dient = new Ingredients();
		ingr�dient.name =  "pomme";
		ingr�dient.quantity = 1.0;
		ingr�dient.priceForThisQuantity = 0.5;
		ingr�dient.removeIngredient("pomme"); //pour enlever les occurences pr�c�dentes
		Ingredients.addIngredient(ingr�dient.name, ingr�dient.quantity, ingr�dient.priceForThisQuantity);
		Ingredients.addIngredient(ingr�dient.name, ingr�dient.quantity, ingr�dient.priceForThisQuantity);
		
	}
}
