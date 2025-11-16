package com.zynova.server.service.lead;

import com.zynova.server.common.dto.PageResponse;
import com.zynova.server.common.exception.NotFoundException;
import com.zynova.server.dto.lead.LeadCreateRequest;
import com.zynova.server.dto.lead.LeadDetailDto;
import com.zynova.server.dto.lead.LeadMapper;
import com.zynova.server.dto.lead.LeadSummaryDto;
import com.zynova.server.dto.lead.LeadUpdateRequest;
import com.zynova.server.model.enums.LeadStatus;
import com.zynova.server.model.lead.Lead;
import com.zynova.server.model.product.VendorProduct;
import com.zynova.server.model.vendor.Vendor;
import com.zynova.server.model.vendor.VendorUser;
import com.zynova.server.repository.LeadRepository;
import com.zynova.server.repository.VendorProductRepository;
import com.zynova.server.repository.VendorRepository;
import com.zynova.server.repository.VendorUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeadService {

    private final LeadRepository leadRepository;
    private final VendorRepository vendorRepository;
    private final VendorProductRepository vendorProductRepository;
    private final VendorUserRepository vendorUserRepository;
    private final LeadMapper leadMapper;

    public PageResponse<LeadSummaryDto> getLeadsForVendor(Long vendorId, LeadStatus status,
            Long ownerId, Pageable pageable) {
        ensureVendorExists(vendorId);
        Page<Lead> page;
        if (status != null && ownerId != null) {
            page = leadRepository.findByVendorIdAndOwnerIdAndStatus(vendorId, ownerId, status, pageable);
        } else if (ownerId != null) {
            page = leadRepository.findByVendorIdAndOwnerId(vendorId, ownerId, pageable);
        } else if (status != null) {
            page = leadRepository.findByVendorIdAndStatus(vendorId, status, pageable);
        } else {
            page = leadRepository.findByVendorId(vendorId, pageable);
        }
        return PageResponse.from(page.map(leadMapper::toSummaryDto));
    }

    public LeadDetailDto getLead(Long id, Long vendorId) {
        Lead lead = loadLeadForVendor(id, vendorId);
        return leadMapper.toDetailDto(lead);
    }

    @Transactional
    public LeadDetailDto createLead(Long createdByUserId, LeadCreateRequest request) {
        if (createdByUserId == null) {
            throw new IllegalArgumentException("createdByUserId is required");
        }
        Vendor vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new NotFoundException("Vendor %d not found".formatted(request.getVendorId())));
        VendorProduct product = null;
        if (request.getProductId() != null) {
            product = vendorProductRepository.findByIdAndVendorId(request.getProductId(), vendor.getId())
                    .orElseThrow(() -> new NotFoundException(
                            "Product %d not found for vendor %d".formatted(request.getProductId(), vendor.getId())));
        }
        VendorUser owner = null;
        if (request.getOwnerId() != null) {
            owner = vendorUserRepository.findByIdAndVendorId(request.getOwnerId(), vendor.getId())
                    .orElseThrow(() -> new NotFoundException(
                            "Owner %d not found for vendor %d".formatted(request.getOwnerId(), vendor.getId())));
        }
        VendorUser createdBy = vendorUserRepository.findByIdAndVendorId(createdByUserId, vendor.getId())
                .orElseThrow(() -> new NotFoundException(
                        "Creator %d not found for vendor %d".formatted(createdByUserId, vendor.getId())));
        Lead lead = leadMapper.toEntity(request, vendor, product, owner, createdBy);
        if (lead.getStatus() == null) {
            lead.setStatus(LeadStatus.NEW);
        }
        Lead saved = leadRepository.save(lead);
        return leadMapper.toDetailDto(saved);
    }

    @Transactional
    public LeadDetailDto updateLead(Long id, Long vendorId, LeadUpdateRequest request) {
        Lead lead = loadLeadForVendor(id, vendorId);
        VendorProduct product = null;
        if (request.getProductId() != null) {
            product = vendorProductRepository.findByIdAndVendorId(request.getProductId(), vendorId)
                    .orElseThrow(() -> new NotFoundException(
                            "Product %d not found for vendor %d".formatted(request.getProductId(), vendorId)));
        }
        VendorUser owner = null;
        if (request.getOwnerId() != null) {
            owner = vendorUserRepository.findByIdAndVendorId(request.getOwnerId(), vendorId)
                    .orElseThrow(() -> new NotFoundException(
                            "Owner %d not found for vendor %d".formatted(request.getOwnerId(), vendorId)));
        }
        if (request.getStatus() == LeadStatus.LOST) {
            if (request.getLostReason() == null || request.getLostReason().isBlank()) {
                throw new IllegalArgumentException("Lost reason is required when marking a lead as LOST");
            }
        }
        leadMapper.updateEntityFromDto(request, lead, product, owner);
        if (request.getStatus() != null && request.getStatus() != LeadStatus.LOST) {
            lead.setLostReason(null);
        }
        Lead saved = leadRepository.save(lead);
        return leadMapper.toDetailDto(saved);
    }

    @Transactional
    public void deleteLead(Long id, Long vendorId) {
        Lead lead = loadLeadForVendor(id, vendorId);
        leadRepository.delete(lead);
    }

    private void ensureVendorExists(Long vendorId) {
        if (!vendorRepository.existsById(vendorId)) {
            throw new NotFoundException("Vendor %d not found".formatted(vendorId));
        }
    }

    private Lead loadLeadForVendor(Long id, Long vendorId) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lead %d not found".formatted(id)));
        if (!lead.getVendor().getId().equals(vendorId)) {
            throw new NotFoundException("Lead %d not found for vendor %d".formatted(id, vendorId));
        }
        return lead;
    }
}
