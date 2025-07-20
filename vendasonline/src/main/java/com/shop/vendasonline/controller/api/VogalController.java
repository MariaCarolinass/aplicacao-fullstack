package com.shop.vendasonline.controller.api;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.vendasonline.dto.api.VogalResponseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vogal")
public class VogalController {
    
    @GetMapping
    public ResponseEntity<VogalResponseDTO> buscarVogal(@RequestParam String string) {
        long inicio = System.currentTimeMillis();
        String resultado = validarVogal(string);
        long fim = System.currentTimeMillis();
        String tempo = (fim - inicio) + "ms";
        
        VogalResponseDTO response = new VogalResponseDTO(string, resultado, tempo);
        
        return ResponseEntity.ok(response);
    }

    private String validarVogal(String str) {
        if (str == null || str.length() < 3) return null;

        Set<Character> repetidos = new HashSet<>();
        Set<Character> unicos = new LinkedHashSet<>();

        for (char c : str.toCharArray()) {
            char lower = Character.toLowerCase(c);
            if (unicos.contains(lower)) {
                unicos.remove(lower);
                repetidos.add(lower);
            } else if (!repetidos.contains(lower)) {
                unicos.add(lower);
            }
        }

        char[] chars = str.toCharArray();
        for (int i = 2; i < chars.length; i++) {
            char anterior = chars[i - 2];
            char meio = chars[i - 1];
            char atual = chars[i];

            if (isVogal(anterior) && isConsoante(meio) && isVogal(atual)) {
                char candidato = Character.toLowerCase(atual);
                if (unicos.contains(candidato)) {
                    return String.valueOf(atual);
                }
            }
        }

        return null;
    }

    private boolean isVogal(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private boolean isConsoante(char c) {
        return Character.isLetter(c) && !isVogal(c);
    }

}
