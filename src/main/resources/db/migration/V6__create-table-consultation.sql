create table consultation(

    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    date datetime not null,

    primary key(id),
    constraint fk_consultation_doctor_id foreign key(doctor_id) references doctor(id),
    constraint fk_consultation_patient_id foreign key(patient_id) references patient_id
);