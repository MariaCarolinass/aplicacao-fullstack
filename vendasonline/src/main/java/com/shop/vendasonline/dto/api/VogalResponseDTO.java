package com.shop.vendasonline.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class VogalResponseDTO {
    
    private String string;
    private String vogal;
    private String tempoTotal;

}
