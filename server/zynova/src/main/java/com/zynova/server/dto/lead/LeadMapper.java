package com.zynova.server.dto.lead;

import com.zynova.server.model.enums.LeadStatus;
import com.zynova.server.model.lead.Lead;
import com.zynova.server.model.product.VendorProduct;
import com.zynova.server.model.vendor.Vendor;
import com.zynova.server.model.vendor.VendorUser;
import org.springframework.stereotype.Component;

@Component
public class LeadMapper {

    public LeadSummaryDto toSummaryDto(Lead lead) {
        if (lead == null) {
            return null;
        }
        return LeadSummaryDto.builder()
                .id(lead.getId())
                .companyName(lead.getCompanyName())
                .contactName(lead.getContactName())
                .country(lead.getCountry())
                .city(lead.getCity())
                .status(lead.getStatus())
                .source(lead.getSource())
                .budgetMin(lead.getBudgetMin())
                .budgetMax(lead.getBudgetMax())
                .currency(lead.getCurrency())
                .probability(lead.getProbability())
                .expectedCloseDate(lead.getExpectedCloseDate())
                .vendorName(lead.getVendor() != null ? lead.getVendor().getName() : null)
                .productName(lead.getProduct() != null ? lead.getProduct().getName() : null)
                .build();
    }

    public LeadDetailDto toDetailDto(Lead lead) {
        if (lead == null) {
            return null;
        }
        return LeadDetailDto.builder()
                .id(lead.getId())
                .externalId(lead.getExternalId())
                .source(lead.getSource())
                .companyName(lead.getCompanyName())
                .contactName(lead.getContactName())
                .contactEmail(lead.getContactEmail())
                .contactPhone(lead.getContactPhone())
                .country(lead.getCountry())
                .city(lead.getCity())
                .message(lead.getMessage())
                .budgetMin(lead.getBudgetMin())
                .budgetMax(lead.getBudgetMax())
                .currency(lead.getCurrency())
                .status(lead.getStatus())
                .lostReason(lead.getLostReason())
                .probability(lead.getProbability())
                .expectedCloseDate(lead.getExpectedCloseDate())
                .vendorName(lead.getVendor() != null ? lead.getVendor().getName() : null)
                .vendorId(lead.getVendor() != null ? lead.getVendor().getId() : null)
                .productName(lead.getProduct() != null ? lead.getProduct().getName() : null)
                .productId(lead.getProduct() != null ? lead.getProduct().getId() : null)
                .ownerId(lead.getOwner() != null ? lead.getOwner().getId() : null)
                .ownerFullName(lead.getOwner() != null ? lead.getOwner().getFullName() : null)
                .createdById(lead.getCreatedBy() != null ? lead.getCreatedBy().getId() : null)
                .createdByFullName(lead.getCreatedBy() != null ? lead.getCreatedBy().getFullName() : null)
                .createdAt(lead.getCreatedAt())
                .updatedAt(lead.getUpdatedAt())
                .build();
    }

    public Lead toEntity(LeadCreateRequest request, Vendor vendor, VendorProduct product, VendorUser owner,
            VendorUser createdBy) {
        Lead lead = Lead.builder()
                .source(request.getSource())
                .companyName(request.getCompanyName())
                .contactName(request.getContactName())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .country(request.getCountry())
                .city(request.getCity())
                .message(request.getMessage())
                .budgetMin(request.getBudgetMin())
                .budgetMax(request.getBudgetMax())
                .currency(request.getCurrency())
                .probability(request.getProbability())
                .expectedCloseDate(request.getExpectedCloseDate())
                .status(LeadStatus.NEW)
                .vendor(vendor)
                .product(product)
                .owner(owner)
                .createdBy(createdBy)
                .build();
        return lead;
    }

    public void updateEntityFromDto(LeadUpdateRequest request, Lead lead, VendorProduct product, VendorUser owner) {
        if (request == null || lead == null) {
            return;
        }
        if (request.getSource() != null) {
            lead.setSource(request.getSource());
        }
        if (request.getCompanyName() != null) {
            lead.setCompanyName(request.getCompanyName());
        }
        if (request.getContactName() != null) {
            lead.setContactName(request.getContactName());
        }
        if (request.getContactEmail() != null) {
            lead.setContactEmail(request.getContactEmail());
        }
        if (request.getContactPhone() != null) {
            lead.setContactPhone(request.getContactPhone());
        }
        if (request.getCountry() != null) {
            lead.setCountry(request.getCountry());
        }
        if (request.getCity() != null) {
            lead.setCity(request.getCity());
        }
        if (request.getMessage() != null) {
            lead.setMessage(request.getMessage());
        }
        if (request.getBudgetMin() != null) {
            lead.setBudgetMin(request.getBudgetMin());
        }
        if (request.getBudgetMax() != null) {
            lead.setBudgetMax(request.getBudgetMax());
        }
        if (request.getCurrency() != null) {
            lead.setCurrency(request.getCurrency());
        }
        if (request.getStatus() != null) {
            lead.setStatus(request.getStatus());
        }
        if (request.getLostReason() != null) {
            lead.setLostReason(request.getLostReason());
        }
        if (request.getProbability() != null) {
            lead.setProbability(request.getProbability());
        }
        if (request.getExpectedCloseDate() != null) {
            lead.setExpectedCloseDate(request.getExpectedCloseDate());
        }
        if (request.getProductId() != null) {
            lead.setProduct(product);
        }
        if (request.getOwnerId() != null) {
            lead.setOwner(owner);
        }
    }
}
