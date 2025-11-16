package com.zynova.server.repository;

import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.model.vendor.Vendor;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorRepository extends JpaRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor> {

    Optional<Vendor> findBySlug(String slug);

    boolean existsByNameIgnoreCase(String name);

    boolean existsBySlugIgnoreCase(String slug);

    Page<Vendor> findByStatus(VendorStatus status, Pageable pageable);

    Page<Vendor> findByCountryIgnoreCaseAndStatus(String country, VendorStatus status, Pageable pageable);
}
