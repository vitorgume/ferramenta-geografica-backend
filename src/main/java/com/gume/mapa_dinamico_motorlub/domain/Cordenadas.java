package com.gume.mapa_dinamico_motorlub.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Cordenadas {
    private String latitude;
    private String longitude;
}
