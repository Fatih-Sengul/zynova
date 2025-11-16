package com.zynova.server.repository;

import com.zynova.server.model.enums.LeadStatus;
import com.zynova.server.model.lead.Lead;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    Page<Lead> findByVendorId(Long vendorId, Pageable pageable);

    Page<Lead> findByVendorIdAndStatus(Long vendorId, LeadStatus status, Pageable pageable);

    Page<Lead> findByVendorIdAndOwnerId(Long vendorId, Long ownerId, Pageable pageable);

    Page<Lead> findByVendorIdAndStatusIn(Long vendorId, Collection<LeadStatus> statuses, Pageable pageable);

    Page<Lead> findByVendorIdAndOwnerIdAndStatus(Long vendorId, Long ownerId, LeadStatus status, Pageable pageable);
}
