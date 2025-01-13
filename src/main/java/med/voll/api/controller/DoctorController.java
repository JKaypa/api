package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> postDoctor(@RequestBody @Valid DoctorDto doctorDto,
            UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(doctorDto);
        var newDoctor = doctorRepository.save(doctor);
        URI uri = uriBuilder.path("/doctor/{id}").buildAndExpand(newDoctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorResponseDto(newDoctor));
    }

    @GetMapping
    public Page<DoctorResponseDto> getDoctors(@PageableDefault(sort = "name") Pageable pagination) {
        return doctorRepository.findAllByActiveTrue(pagination).map(DoctorResponseDto::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(new DoctorDetailDto(doctorRepository.getReferenceById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DoctorResponseDto> updateDoctor(
            @RequestBody @Valid UpdateDoctorDto updateDoctorDto, @PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.update(updateDoctorDto);
        return ResponseEntity.ok(new DoctorResponseDto(doctor));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_admin")
    @Transactional
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorRepository.getReferenceById(id).logicDelete();

        return ResponseEntity.noContent().build();
    }
}
