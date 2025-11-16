package com.zynova.server.dto.lead;

import com.zynova.server.model.enums.LeadSource;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class LeadCreateRequest {

    @NotNull
    private Long vendorId;

    private Long productId;

    private Long ownerId;

    private LeadSource source;

    @NotBlank
    private String companyName;

    @NotBlank
    private String contactName;

    @Email
    private String contactEmail;

    private String contactPhone;

    private String country;

    private String city;

    @NotBlank
    private String message;

    private BigDecimal budgetMin;

    private BigDecimal budgetMax;

    private String currency;

    private Integer probability;

    private LocalDate expectedCloseDate;
}
