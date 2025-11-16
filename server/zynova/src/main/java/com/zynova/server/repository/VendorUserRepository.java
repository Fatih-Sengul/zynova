package com.zynova.server.repository;

import com.zynova.server.model.vendor.VendorUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorUserRepository extends JpaRepository<VendorUser, Long> {

    Optional<VendorUser> findByIdAndVendorId(Long id, Long vendorId);
}
