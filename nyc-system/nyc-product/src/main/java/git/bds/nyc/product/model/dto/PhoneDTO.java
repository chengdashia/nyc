package git.bds.nyc.product.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/2 18:56
 */
@Data
public class PhoneDTO implements Serializable {

    /** 电话 */
    private String phone;
}
