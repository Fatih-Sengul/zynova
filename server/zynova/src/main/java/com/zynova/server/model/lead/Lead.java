package com.zynova.server.model.lead;

import com.zynova.server.model.common.BaseEntity;
import com.zynova.server.model.enums.LeadSource;
import com.zynova.server.model.enums.LeadStatus;
import com.zynova.server.model.product.VendorProduct;
import com.zynova.server.model.vendor.Vendor;
import com.zynova.server.model.vendor.VendorUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "leads")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"vendor", "product", "owner", "createdBy"})
public class Lead extends BaseEntity {

    private String externalId;

    @Enumerated(EnumType.STRING)
    private LeadSource source;

    private String companyName;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    private String country;

    private String city;

    @Column(columnDefinition = "TEXT")
    private String message;

    private BigDecimal budgetMin;

    private BigDecimal budgetMax;

    private String currency;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String lostReason;

    private Integer probability;

    private LocalDate expectedCloseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private VendorProduct product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private VendorUser owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private VendorUser createdBy;

    public boolean isClosed() {
        return LeadStatus.WON.equals(status) || LeadStatus.LOST.equals(status) || LeadStatus.ARCHIVED.equals(status);
    }

    public boolean isOpen() {
        return !isClosed();
    }
}
