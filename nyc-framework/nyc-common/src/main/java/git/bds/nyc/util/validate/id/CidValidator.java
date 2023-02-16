package git.bds.nyc.util.validate.id;

import cn.hutool.core.util.IdcardUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 成大事
 * @since 2022/8/15 16:58
 */
public class CidValidator implements ConstraintValidator<Cid,String> {
    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return IdcardUtil.isValidCard(id);
    }
}
