package git.bds.nyc.util.validate.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author 成大事
 * @date 2022/3/15 18:47
 */

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return MOBILE_PATTERN.matcher(value).matches();
    }

}
