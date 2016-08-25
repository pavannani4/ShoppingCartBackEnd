package com.niit.shoppingcart;

import static org.junit.Assert.*;





import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;

public class TestCaseCategory {

	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	Category category;
	
	AnnotationConfigApplicationContext context;
	
	@Before
	public void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
		category = (Category)context.getBean("category");
	}

	@Test
	public void categoryListTestCase()
	{
		List<Category> list = categoryDAO.list();
		int rowCount = list.size();
		assertEquals("Category List Test Case", rowCount,3);
	}
	
	@Test
	public void categoryAddTestCase()
	{
		category.setId("CG004");
		category.setName("Home");
		category.setDescription("Home products");
		boolean flag = categoryDAO.save(category);
		assertEquals(flag, true);
	}
	
	@Test
	public void deleteTestCase(){
		category.setId("CG002");
		assertEquals(categoryDAO.delete(category),true);
	}
	
	@Test
	public void updateTestCase(){
		category.setId("CG001");
		category.setName("Moto");
		category.setDescription("this is MOTO");
		assertEquals(categoryDAO.update(category),true);
	}

	@Test
	public void getCategoryTestCase(){
	category=categoryDAO.get("CG001");
	System.out.println(category.getDescription());
	assertEquals(category.getName(),"Moto");
	}

}
