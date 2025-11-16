package com.zynova.server.repository;

import com.zynova.server.model.product.VendorProduct;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorProductRepository extends JpaRepository<VendorProduct, Long> {

    Optional<VendorProduct> findByIdAndVendorId(Long id, Long vendorId);
}
