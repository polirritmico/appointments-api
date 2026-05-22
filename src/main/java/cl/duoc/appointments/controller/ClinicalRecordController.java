/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.controller;

import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.service.ClinicalRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clinical-records")
@RequiredArgsConstructor
@Tag(name = "Clinical Records", description = "Provides clinical records CRUD operations.")
public class ClinicalRecordController {
    private final ClinicalRecordService service;

    @GetMapping
    private ResponseEntity<List<ClinicalRecordResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
