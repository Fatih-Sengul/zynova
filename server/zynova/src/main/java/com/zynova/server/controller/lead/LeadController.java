package com.zynova.server.controller.lead;

import com.zynova.server.common.dto.PageResponse;
import com.zynova.server.dto.lead.LeadCreateRequest;
import com.zynova.server.dto.lead.LeadDetailDto;
import com.zynova.server.dto.lead.LeadSummaryDto;
import com.zynova.server.dto.lead.LeadUpdateRequest;
import com.zynova.server.model.enums.LeadStatus;
import com.zynova.server.service.lead.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendors/{vendorId}/leads")
@RequiredArgsConstructor
@Validated
public class LeadController {

    private final LeadService leadService;

    @GetMapping
    public ResponseEntity<PageResponse<LeadSummaryDto>> getLeads(
            @PathVariable Long vendorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) LeadStatus status,
            @RequestParam(required = false) Long ownerId) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponse<LeadSummaryDto> response = leadService.getLeadsForVendor(vendorId, status, ownerId, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDetailDto> getLead(@PathVariable Long vendorId, @PathVariable Long id) {
        return ResponseEntity.ok(leadService.getLead(id, vendorId));
    }

    @PostMapping
    public ResponseEntity<LeadDetailDto> createLead(
            @PathVariable Long vendorId,
            @RequestParam Long createdByUserId,
            @Valid @RequestBody LeadCreateRequest request) {
        if (!vendorId.equals(request.getVendorId())) {
            throw new IllegalArgumentException("Vendor mismatch between path and payload");
        }
        LeadDetailDto lead = leadService.createLead(createdByUserId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(lead);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadDetailDto> updateLead(
            @PathVariable Long vendorId,
            @PathVariable Long id,
            @Valid @RequestBody LeadUpdateRequest request) {
        LeadDetailDto updated = leadService.updateLead(id, vendorId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long vendorId, @PathVariable Long id) {
        leadService.deleteLead(id, vendorId);
        return ResponseEntity.noContent().build();
    }
}
