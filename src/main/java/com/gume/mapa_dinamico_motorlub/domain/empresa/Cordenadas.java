package com.gume.mapa_dinamico_motorlub.domain.empresa;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class Cordenadas {
    private String latitude;
    private String longitude;
}
