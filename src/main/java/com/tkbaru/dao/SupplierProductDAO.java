package com.tkbaru.dao;

import com.tkbaru.model.SupplierProduct;
import java.util.List;

/**
 *
 * @author Erwin
 */
public interface SupplierProductDAO {

    public List<SupplierProduct> getAllProductBySupplier(int supplierId);
    
    public List<SupplierProduct> getAllProductByProduct(int productId);
    
    public SupplierProduct getSupplierProductById(int id);

    public void addProduct(SupplierProduct supplierProduct);

    public void editProduct(SupplierProduct supplierProduct);

    public void deleteProduct(int selectedId);

}
