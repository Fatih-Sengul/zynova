package com.zynova.server.dto.vendor;

import com.zynova.server.model.enums.VendorTier;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorUpdateRequest {

    private String name;
    private String slug;
    private String country;
    private String city;
    @Email
    private String email;
    private String taxNumber;
    private String vatNumber;
    private String addressLine;
    private String postalCode;
    private String phone;
    private String websiteUrl;
    private String linkedinUrl;
    private String description;
    private String industry;
    private String sapFocusAreas;
    private Integer employeesFrom;
    private Integer employeesTo;
    private VendorTier tier;
    private Boolean isVerified;
}
