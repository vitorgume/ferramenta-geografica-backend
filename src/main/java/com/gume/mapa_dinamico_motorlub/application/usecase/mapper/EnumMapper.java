package com.gume.mapa_dinamico_motorlub.application.usecase.mapper;

import com.gume.mapa_dinamico_motorlub.domain.NivelIcp;
import com.gume.mapa_dinamico_motorlub.domain.empresa.Segmento;

public class EnumMapper {

    public static Segmento mapperSegmento(String codigo) {
        switch (codigo) {
            case "0" -> {
                return Segmento.OFICINA_MECANICA;
            }
            case "1" -> {
                return Segmento.TROCA_OLEO;
            }
            default -> {
                return Segmento.AUTO_PECA;
            }
        }
    }

    public static NivelIcp mapperNivelIcp(String codigo) {
        switch (codigo) {
            case "0" -> {
                return NivelIcp.A_MAIS;
            }
            case "1" -> {
                return NivelIcp.B_MAIS;
            }
            case "2" -> {
                return NivelIcp.C_MAIS;
            }
            case "3" -> {
                return NivelIcp.A;
            }
            case "4" -> {
                return NivelIcp.B;
            }
            case "5" -> {
                return NivelIcp.C;
            }
            case "6" -> {
                return NivelIcp.A_MENOS;
            }
            case "7" -> {
                return NivelIcp.B_MENOS;
            }
            default -> {
                return NivelIcp.C_MENOS;
            }
        }
    }
}
