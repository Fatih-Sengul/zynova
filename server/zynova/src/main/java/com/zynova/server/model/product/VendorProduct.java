package com.zynova.server.model.product;

import com.zynova.server.model.common.BaseEntity;
import com.zynova.server.model.vendor.Vendor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendor_products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"vendor"})
public class VendorProduct extends BaseEntity {

    private String code;

    private String name;

    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    private String category;

    private String subCategory;

    private String targetCustomerType;

    private String sapModules;

    private String deliveryModel;

    private String pricingModel;

    private BigDecimal minBudget;

    private BigDecimal maxBudget;

    private Boolean published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    public boolean isRangeBudget() {
        return minBudget != null && maxBudget != null;
    }
}
