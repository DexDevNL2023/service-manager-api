package net.service.manager.offers.generic.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    @NotBlank(message = "Veillez renseignez le mot recherché svp!")
    private String text;
}