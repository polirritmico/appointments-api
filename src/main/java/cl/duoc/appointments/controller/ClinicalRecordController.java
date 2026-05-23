/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.controller;

import cl.duoc.appointments.dto.request.ClinicalRecordCreationRequest;
import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.service.ClinicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/clinical-records")
@RequiredArgsConstructor
@Tag(name = "Clinical Records", description = "Provides clinical records CRUD operations.")
public class ClinicalRecordController {
    private final ClinicalRecordService service;

    @Operation(
            summary = "List all clinical records",
            description = "Retrieves a comprehensive list of all clinical records registered in the system.")
    @GetMapping
    public ResponseEntity<List<ClinicalRecordResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Get clinical record by Id",
            description = "Retrieves the details of a specific clinical record using its unique identifier.")
    @GetMapping("/{recordId}")
    public ResponseEntity<ClinicalRecordResponse> findById(@PathVariable Long recordId) {
        return ResponseEntity.ok(service.findById(recordId));
    }

    @Operation(
            summary = "Get clinical records by pet Id",
            description = "Retrieves the complete medical history and all associated records for a specific pet.")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ClinicalRecordResponse>> findByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(service.findByPetId(petId));
    }

    @Operation(
            summary = "Create a new clinical record",
            description =
                    "Creates medical documentation for a completed or in-progress appointment, including diagnosis, treatment, and notes.")
    @PostMapping
    public ResponseEntity<ClinicalRecordResponse> createRecord(@Valid @RequestBody ClinicalRecordCreationRequest req) {
        ClinicalRecordResponse res = service.saveRecord(req);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{id}")
                        .buildAndExpand(res.getId())
                        .toUri())
                .body(res);
    }
}
