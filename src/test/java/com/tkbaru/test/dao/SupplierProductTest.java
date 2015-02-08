package com.tkbaru.test.dao;

import com.tkbaru.dao.ProductDAO;
import com.tkbaru.model.Product;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.SupplierService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Erwin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SupplierProductTest {
    
    @Autowired
    ProductService productService;
    
    @Autowired
    SupplierService supplierService;
    
    private static final Logger logger = LoggerFactory.getLogger(SupplierProductTest.class);

    @Test
    public void findById() {
        List<Product> products = productService.getAllProduct();
        for(Product product : products) {
            logger.info(product.toString());
        }
    }

}
