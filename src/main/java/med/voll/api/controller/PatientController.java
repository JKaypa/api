package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<PatientResponseDto> postPatient(@RequestBody @Valid PatientDto patientDto, UriComponentsBuilder uriBuilder){
        var patient = new Patient(patientDto);
        var newPatient = patientRepository.save(patient);
        URI uri = uriBuilder.path("/patient/{id}").buildAndExpand(newPatient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientResponseDto(newPatient));
    }

    @GetMapping
    public Page<PatientResponseDto> getPatient(@PageableDefault(sort = "name") Pageable pagination){
        return patientRepository.findAllByActiveTrue(pagination).map(PatientResponseDto::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailDto> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(new PatientDetailDto(patientRepository.getReferenceById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientResponseDto> updatePatient(@RequestBody @Valid UpdatePatientDto updatePatientDto, @PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);
        patient.update(updatePatientDto);
        return ResponseEntity.ok(new PatientResponseDto(patient));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_admin")
    @Transactional
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientRepository.getReferenceById(id).logicDelete();

        return ResponseEntity.noContent().build();
    }
}
