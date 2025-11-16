package com.zynova.server.controller.vendor;

import com.zynova.server.common.dto.PageResponse;
import com.zynova.server.dto.vendor.VendorCreateRequest;
import com.zynova.server.dto.vendor.VendorDetailDto;
import com.zynova.server.dto.vendor.VendorSummaryDto;
import com.zynova.server.dto.vendor.VendorUpdateRequest;
import com.zynova.server.model.enums.VendorStatus;
import com.zynova.server.service.vendor.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
@Validated
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    public ResponseEntity<PageResponse<VendorSummaryDto>> getVendors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) VendorStatus status,
            @RequestParam(required = false) String country,
            @RequestParam(name = "search", required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<VendorSummaryDto> response = vendorService.getVendors(status, country, searchText, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDetailDto> getVendor(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<VendorDetailDto> getVendorBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(vendorService.getVendorBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<VendorDetailDto> createVendor(@Valid @RequestBody VendorCreateRequest request) {
        VendorDetailDto vendor = vendorService.createVendor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDetailDto> updateVendor(@PathVariable Long id,
            @Valid @RequestBody VendorUpdateRequest request) {
        return ResponseEntity.ok(vendorService.updateVendor(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable Long id, @RequestParam VendorStatus status) {
        vendorService.changeVendorStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}
