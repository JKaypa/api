package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public void postDoctor(@RequestBody @Valid DoctorDto doctorDto){
        var doctor = new Doctor(doctorDto);
        doctorRepository.save(doctor);
    }

    @GetMapping
    public Page<DoctorListDto> getDoctors(@PageableDefault(sort = "name") Pageable pagination){
        return doctorRepository.findAllByActiveTrue(pagination).map(DoctorListDto::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDto updateDoctorDto, @PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.update(updateDoctorDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.logicDelete();
    }
}
