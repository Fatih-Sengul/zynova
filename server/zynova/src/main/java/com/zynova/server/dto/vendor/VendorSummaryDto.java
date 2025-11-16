package com.zynova.server.dto.vendor;

import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.model.enums.VendorTier;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VendorSummaryDto {
    Long id;
    String name;
    String city;
    String country;
    String industry;
    String sapFocusAreas;
    VendorStatus status;
    VendorTier tier;
    Boolean isVerified;
}
