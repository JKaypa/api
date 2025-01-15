package med.voll.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.api.domain.consultation.AppointmentRequestDto;
import med.voll.api.domain.consultation.AppointmentResponseDto;
import med.voll.api.domain.consultation.ConsultationService;
import med.voll.api.domain.doctor.Specialty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class ConsultationControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private JacksonTester<AppointmentRequestDto> appointmentRequest;

	@Autowired
	private JacksonTester<AppointmentResponseDto> appointmentResponse;

	@MockitoBean
	ConsultationService consultationService;

	@Test
	@DisplayName("Should return a 400 status code when a request is made without a body")
	@WithMockUser
	void testScheduleScenario1() throws Exception {
		var response = mock.perform(post("/consultations")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Should return a 200 status code when a request is made with a valid body")
	@WithMockUser
	void testScheduleScenario2() throws Exception {
		var date = LocalDateTime.now().plusHours(1);
		var specialty = Specialty.CARDIOLOGY;
		var consultationResponse = new AppointmentResponseDto(null, 2l, 5l, date);
		when(consultationService.bookingConsultation(any())).thenReturn(consultationResponse);

		var response = mock
				.perform(post("/consultations").contentType(MediaType.APPLICATION_JSON).content(
						appointmentRequest.write(new AppointmentRequestDto(2l, specialty, 5l, date)).getJson()))
				.andReturn().getResponse();

		var jsonResponse = appointmentResponse
				.write(consultationResponse).getJson();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
	}

}
