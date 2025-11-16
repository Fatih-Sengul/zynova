package com.zynova.server.service.vendor;

import com.zynova.server.common.dto.PageResponse;
import com.zynova.server.common.exception.NotFoundException;
import com.zynova.server.dto.vendor.VendorCreateRequest;
import com.zynova.server.dto.vendor.VendorDetailDto;
import com.zynova.server.dto.vendor.VendorMapper;
import com.zynova.server.dto.vendor.VendorSummaryDto;
import com.zynova.server.dto.vendor.VendorUpdateRequest;
import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.model.vendor.Vendor;
import com.zynova.server.repository.VendorRepository;
import java.util.Locale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Transactional(readOnly = true)
    public PageResponse<VendorSummaryDto> getVendors(
            VendorStatus status, String country, String searchText, Pageable pageable) {
        Specification<Vendor> spec = Specification.where(null);

        if (status != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("status"), status));
        }

        if (country != null && !country.isBlank()) {
            String normalizedCountry = country.toLowerCase(Locale.ROOT);
            spec = spec.and((root, query, builder) -> builder.equal(builder.lower(root.get("country")), normalizedCountry));
        }

        if (searchText != null && !searchText.isBlank()) {
            String like = "%" + searchText.trim().toLowerCase(Locale.ROOT) + "%";
            spec = spec.and((root, query, builder) -> builder.or(
                    builder.like(builder.lower(root.get("name")), like),
                    builder.like(builder.lower(root.get("city")), like),
                    builder.like(builder.lower(root.get("industry")), like)));
        }

        Page<Vendor> page = vendorRepository.findAll(spec, pageable);
        return PageResponse.from(page.map(vendorMapper::toSummaryDto));
    }

    @Transactional(readOnly = true)
    public VendorDetailDto getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor %d not found".formatted(id)));
        return vendorMapper.toDetailDto(vendor);
    }

    @Transactional(readOnly = true)
    public VendorDetailDto getVendorBySlug(String slug) {
        Vendor vendor = vendorRepository.findBySlug(slug)
                .orElseThrow(() -> new NotFoundException("Vendor %s not found".formatted(slug)));
        return vendorMapper.toDetailDto(vendor);
    }

    @Transactional
    public VendorDetailDto createVendor(VendorCreateRequest request) {
        validateUniqueFields(request.getName(), request.getSlug(), null);
        Vendor vendor = vendorMapper.toEntity(request);
        vendor.setStatus(VendorStatus.ACTIVE);
        vendor.setIsVerified(Boolean.FALSE);
        Vendor saved = vendorRepository.save(vendor);
        return vendorMapper.toDetailDto(saved);
    }

    @Transactional
    public VendorDetailDto updateVendor(Long id, VendorUpdateRequest request) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor %d not found".formatted(id)));
        validateUniqueFields(request.getName(), request.getSlug(), vendor);
        vendorMapper.updateEntityFromDto(request, vendor);
        Vendor saved = vendorRepository.save(vendor);
        return vendorMapper.toDetailDto(saved);
    }

    @Transactional
    public void changeVendorStatus(Long id, VendorStatus status) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendor %d not found".formatted(id)));
        vendor.setStatus(status);
        vendorRepository.save(vendor);
    }

    private void validateUniqueFields(String name, String slug, Vendor currentVendor) {
        if (name != null && vendorRepository.existsByNameIgnoreCase(name)) {
            if (currentVendor == null || !name.equalsIgnoreCase(currentVendor.getName())) {
                throw new IllegalArgumentException("Vendor name already exists");
            }
        }
        if (slug != null && vendorRepository.existsBySlugIgnoreCase(slug)) {
            if (currentVendor == null || !slug.equalsIgnoreCase(currentVendor.getSlug())) {
                throw new IllegalArgumentException("Vendor slug already exists");
            }
        }
    }
}
