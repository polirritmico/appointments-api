/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.mapper.DtoModelMapper;
import cl.duoc.appointments.repository.ClinicalRecordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClinicalRecordService {
    private final ClinicalRecordRepository repo;

    private final DtoModelMapper mapper;

    private void logRequest(String msg) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(msg + " by user " + auth.getName());
    }

    public List<ClinicalRecordResponse> findAll() {
        logRequest("Starting findAll");
        return repo.findAll().stream().map(mapper::toClinicalRecordResponse).toList();
    }
}
