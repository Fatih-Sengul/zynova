package com.zynova.server.dto.lead;

import com.zynova.server.model.enums.LeadSource;
import com.zynova.server.model.enums.LeadStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LeadSummaryDto {
    Long id;
    String companyName;
    String contactName;
    String country;
    String city;
    LeadStatus status;
    LeadSource source;
    BigDecimal budgetMin;
    BigDecimal budgetMax;
    String currency;
    Integer probability;
    LocalDate expectedCloseDate;
    String vendorName;
    String productName;
}
