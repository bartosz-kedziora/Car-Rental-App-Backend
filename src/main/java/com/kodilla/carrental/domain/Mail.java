package com.kodilla.carrental.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mail {
    @NonNull
    private final String mailTo;

    private String toCc;

    @NonNull
    private final String subject;

    @NonNull
    private final String message;
}
