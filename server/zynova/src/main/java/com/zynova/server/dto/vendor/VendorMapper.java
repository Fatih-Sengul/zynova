package com.zynova.server.dto.vendor;

import com.zynova.server.model.vendor.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public VendorSummaryDto toSummaryDto(Vendor vendor) {
        if (vendor == null) {
            return null;
        }
        return VendorSummaryDto.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .city(vendor.getCity())
                .country(vendor.getCountry())
                .industry(vendor.getIndustry())
                .sapFocusAreas(vendor.getSapFocusAreas())
                .status(vendor.getStatus())
                .tier(vendor.getTier())
                .isVerified(vendor.getIsVerified())
                .build();
    }

    public VendorDetailDto toDetailDto(Vendor vendor) {
        if (vendor == null) {
            return null;
        }
        return VendorDetailDto.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .slug(vendor.getSlug())
                .taxNumber(vendor.getTaxNumber())
                .vatNumber(vendor.getVatNumber())
                .country(vendor.getCountry())
                .city(vendor.getCity())
                .addressLine(vendor.getAddressLine())
                .postalCode(vendor.getPostalCode())
                .phone(vendor.getPhone())
                .email(vendor.getEmail())
                .websiteUrl(vendor.getWebsiteUrl())
                .linkedinUrl(vendor.getLinkedinUrl())
                .description(vendor.getDescription())
                .industry(vendor.getIndustry())
                .sapFocusAreas(vendor.getSapFocusAreas())
                .employeesFrom(vendor.getEmployeesFrom())
                .employeesTo(vendor.getEmployeesTo())
                .status(vendor.getStatus())
                .tier(vendor.getTier())
                .isVerified(vendor.getIsVerified())
                .createdAt(vendor.getCreatedAt())
                .updatedAt(vendor.getUpdatedAt())
                .build();
    }

    public Vendor toEntity(VendorCreateRequest request) {
        if (request == null) {
            return null;
        }
        return Vendor.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .country(request.getCountry())
                .city(request.getCity())
                .email(request.getEmail())
                .taxNumber(request.getTaxNumber())
                .vatNumber(request.getVatNumber())
                .addressLine(request.getAddressLine())
                .postalCode(request.getPostalCode())
                .phone(request.getPhone())
                .websiteUrl(request.getWebsiteUrl())
                .linkedinUrl(request.getLinkedinUrl())
                .description(request.getDescription())
                .industry(request.getIndustry())
                .sapFocusAreas(request.getSapFocusAreas())
                .employeesFrom(request.getEmployeesFrom())
                .employeesTo(request.getEmployeesTo())
                .tier(request.getTier())
                .build();
    }

    public void updateEntityFromDto(VendorUpdateRequest request, Vendor vendor) {
        if (request == null || vendor == null) {
            return;
        }
        if (request.getName() != null) {
            vendor.setName(request.getName());
        }
        if (request.getSlug() != null) {
            vendor.setSlug(request.getSlug());
        }
        if (request.getCountry() != null) {
            vendor.setCountry(request.getCountry());
        }
        if (request.getCity() != null) {
            vendor.setCity(request.getCity());
        }
        if (request.getEmail() != null) {
            vendor.setEmail(request.getEmail());
        }
        if (request.getTaxNumber() != null) {
            vendor.setTaxNumber(request.getTaxNumber());
        }
        if (request.getVatNumber() != null) {
            vendor.setVatNumber(request.getVatNumber());
        }
        if (request.getAddressLine() != null) {
            vendor.setAddressLine(request.getAddressLine());
        }
        if (request.getPostalCode() != null) {
            vendor.setPostalCode(request.getPostalCode());
        }
        if (request.getPhone() != null) {
            vendor.setPhone(request.getPhone());
        }
        if (request.getWebsiteUrl() != null) {
            vendor.setWebsiteUrl(request.getWebsiteUrl());
        }
        if (request.getLinkedinUrl() != null) {
            vendor.setLinkedinUrl(request.getLinkedinUrl());
        }
        if (request.getDescription() != null) {
            vendor.setDescription(request.getDescription());
        }
        if (request.getIndustry() != null) {
            vendor.setIndustry(request.getIndustry());
        }
        if (request.getSapFocusAreas() != null) {
            vendor.setSapFocusAreas(request.getSapFocusAreas());
        }
        if (request.getEmployeesFrom() != null) {
            vendor.setEmployeesFrom(request.getEmployeesFrom());
        }
        if (request.getEmployeesTo() != null) {
            vendor.setEmployeesTo(request.getEmployeesTo());
        }
        if (request.getTier() != null) {
            vendor.setTier(request.getTier());
        }
        if (request.getIsVerified() != null) {
            vendor.setIsVerified(request.getIsVerified());
        }
    }
}
