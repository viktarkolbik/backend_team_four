package by.exadel.internship.dto.enums;

public enum FormStatus {
    REGISTERED,
    ADMIN_INTERVIEW_ASSIGNED,
    ADMIN_INTERVIEW_PASSED,
    TECH_INTERVIEW_ASSIGNED,
    TECH_INTERVIEW_PASSED,
    ACCEPTED,
    NOT_MATCHED,      //не подходит (example нет офиса, не тот регион, и т д английский) нужен ли???
    REJECTED,         // отклонен (не соответствие требованиям (тех знания))
    CANDIDATE_REFUSED,   // отказался (сам кандидат)

    WAITING, //спросить
    GOT_JOB_OFFER,
    DIDNT_GOT_JOB_OFFER

}
