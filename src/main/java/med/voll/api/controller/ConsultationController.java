package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.consultation.AppointmentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultations")
public class ConsultationController {

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> schedule(@RequestBody @Valid AppointmentRequestDto appointmentRequestDto){
        System.out.println(appointmentRequestDto);
        return ResponseEntity.ok(new AppointmentResponseDto(null, null, null, null));
    }
}
