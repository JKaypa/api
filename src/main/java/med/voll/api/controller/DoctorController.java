package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDto;
import med.voll.api.doctor.DoctorListDto;
import med.voll.api.doctor.DoctorRepository;
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
        System.out.println(doctor);
        doctorRepository.save(doctor);
    }

    @GetMapping
    public Page<DoctorListDto> getDoctors(@PageableDefault(size = 2, sort = "name") Pageable pagination){
        return doctorRepository.findAll(pagination).map(DoctorListDto::new);
    }
}
