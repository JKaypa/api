package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.consultation.AppointmentResponseDto;
import med.voll.api.domain.consultation.CancelAppointmentRequestDto;
import med.voll.api.domain.consultation.CancelAppointmentResponseDto;
import med.voll.api.domain.consultation.ConsultationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("consultations")    
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> schedule(@RequestBody @Valid AppointmentRequestDto appointmentRequestDto){
        var consultation = consultationService.bookingConsultation(appointmentRequestDto);
        return ResponseEntity.ok(consultation);
    }

    @DeleteMapping
    public ResponseEntity<CancelAppointmentResponseDto> cancelScheduling(@RequestBody @Valid CancelAppointmentRequestDto cancellation){
        var cancelled = consultationService.cancelConsultation(cancellation);
        return ResponseEntity.ok(cancelled);
    }
}
