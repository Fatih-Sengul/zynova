package com.zynova.server.dto.lead;

import com.zynova.server.model.enums.LeadSource;
import com.zynova.server.model.enums.LeadStatus;
import jakarta.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class LeadUpdateRequest {

    private LeadSource source;
    private String companyName;
    private String contactName;
    @Email
    private String contactEmail;
    private String contactPhone;
    private String country;
    private String city;
    private String message;
    private BigDecimal budgetMin;
    private BigDecimal budgetMax;
    private String currency;
    private LeadStatus status;
    private String lostReason;
    private Integer probability;
    private LocalDate expectedCloseDate;
    private Long productId;
    private Long ownerId;
}
