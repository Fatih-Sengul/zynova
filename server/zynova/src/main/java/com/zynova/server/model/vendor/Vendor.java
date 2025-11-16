package com.zynova.server.model.vendor;

import com.zynova.server.model.common.BaseEntity;
import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.model.enums.VendorTier;
import com.zynova.server.model.lead.Lead;
import com.zynova.server.model.product.VendorProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users", "products", "leads"})
public class Vendor extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;

    private String taxNumber;

    private String vatNumber;

    private String country;

    private String city;

    private String addressLine;

    private String postalCode;

    private String phone;

    private String email;

    private String websiteUrl;

    private String linkedinUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    @Enumerated(EnumType.STRING)
    private VendorTier tier;

    private String industry;

    private String sapFocusAreas;

    private Integer employeesFrom;

    private Integer employeesTo;

    @Builder.Default
    private Boolean isVerified = Boolean.FALSE;

    @Builder.Default
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorUser> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorProduct> products = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    public void addUser(VendorUser user) {
        if (user == null) {
            return;
        }
        user.setVendor(this);
        this.users.add(user);
    }
}
