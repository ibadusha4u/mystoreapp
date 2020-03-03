package inventory.com.inventory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.core.exception.BusinessException;
import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import com.inventory.service.ProductServiceImpl;

public class ProductServiceTest {
	
	@Mock 
	private ProductDAO productDAO;
	
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
		
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testProductByProdid () {		
		 try {			 
            when(productDAO.get(any(Integer.class))).thenReturn(new Product());	           
            assertThat(productServiceImpl.getProductByProdid(0), is(notNullValue()));			  
        } catch (BusinessException e) {			
			e.printStackTrace();
		}
	}
	
    @Test
    public void testGetAllProduct() {
    	try {
    		 when(productDAO.getAll()).thenReturn(new ArrayList<Product>());
    		 assertThat(productServiceImpl.getAllProduct(), is(notNullValue()));
    	 } catch (BusinessException e) {			
 			e.printStackTrace();
 		}
    }

    /*@Test(expected = BusinessException.class)
    public void testAddCustomer_throwsException() {
    	try {
	    	when(productDAO.delete(any(Integer.class))).thenThrow(BusinessException.class);
	        Product product = new Product();
	        int productId=0;
	        productServiceImpl.deleteProduct(productId);        
    	 } catch (BusinessException e) {			
  			e.printStackTrace();
  		}
    }

	@Test
	public void testAdd() {
		try {
			when(productDAO.get(any(Integer.class))).thenAnswer(new Answer<Integer>() {

	            public Integer answer(InvocationOnMock invocation) throws Throwable {

	                Object[] arguments = invocation.getArguments();
	                if (arguments != null && arguments.length > 0 && arguments[0] != null){
	                	logger.info(arguments[0]);
	                	//int productId = (Integer) arguments[0];                	
	                    return (Integer)arguments[0];
	                }
	                return null;
	            }
	        });			
			int productId=0;
   		 	assertThat(productServiceImpl.getProductByProdid(productId), is(notNullValue()));
   	 	} catch (BusinessException e) {			
			e.printStackTrace();
		}
	}*/
	
	

}
