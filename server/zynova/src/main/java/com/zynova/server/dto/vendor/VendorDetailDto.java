package com.zynova.server.dto.vendor;

import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.model.enums.VendorTier;
import java.time.Instant;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VendorDetailDto {
    Long id;
    String name;
    String slug;
    String taxNumber;
    String vatNumber;
    String country;
    String city;
    String addressLine;
    String postalCode;
    String phone;
    String email;
    String websiteUrl;
    String linkedinUrl;
    String description;
    String industry;
    String sapFocusAreas;
    Integer employeesFrom;
    Integer employeesTo;
    VendorStatus status;
    VendorTier tier;
    Boolean isVerified;
    Instant createdAt;
    Instant updatedAt;
}
