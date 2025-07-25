package com.apskai.identifyservice.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

// 2 params, 1 cái là class được validate, 1 cái là kiểu dữ liệu muốn validate
public class DobValidator implements ConstraintValidator<DobConstraint, LocalDate> {

    private int min;

    // mỗi khi được khởi tạo có thể lấy được thông số của annotation đó - DobConstraint
    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    // Hàm xử lý data có đúng không
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate))
            return true;

        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        return years >= min;
    }
}
