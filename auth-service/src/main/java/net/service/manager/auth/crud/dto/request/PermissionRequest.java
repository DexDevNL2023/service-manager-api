package net.service.manager.auth.crud.dto.request;

import net.service.manager.auth.generic.dto.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest extends BaseRequest {
    private Long roleId;
    private Long droitId;
    private Boolean hasPermission = false;
}
