package com.senai.conta_bancaria.interface_ui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;

public class ProblemDetailUtils {
    public static ProblemDetail buildProblem(HttpStatus status, String title, String details, String path){
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setDetail(details);
        problem.setInstance(URI.create(path));
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("application", "ContaBancariaAPI");
        return problem;
    }
}
