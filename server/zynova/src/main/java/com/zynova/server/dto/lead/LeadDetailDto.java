package com.zynova.server.dto.lead;

import com.zynova.server.model.enums.LeadSource;
import com.zynova.server.model.enums.LeadStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LeadDetailDto {
    Long id;
    String externalId;
    LeadSource source;
    String companyName;
    String contactName;
    String contactEmail;
    String contactPhone;
    String country;
    String city;
    String message;
    BigDecimal budgetMin;
    BigDecimal budgetMax;
    String currency;
    LeadStatus status;
    String lostReason;
    Integer probability;
    LocalDate expectedCloseDate;
    String vendorName;
    Long vendorId;
    String productName;
    Long productId;
    Long ownerId;
    String ownerFullName;
    Long createdById;
    String createdByFullName;
    Instant createdAt;
    Instant updatedAt;
}
