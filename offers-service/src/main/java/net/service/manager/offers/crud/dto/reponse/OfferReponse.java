package net.service.manager.offers.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.offers.generic.dto.reponse.BaseReponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferReponse extends BaseReponse {
    private String name;
    private String description;
    private List<String> images;
    private Long partnerId;
    private Boolean isVisible;
}
