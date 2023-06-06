public class ArmsValidationUtils {
    public static <T> void validate(@NonNull Validator validator, @NonNull T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, groups);
        if (constraintViolations.size() > 0) {
            StringBuilder validateError = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                validateError.append(constraintViolation.getPropertyPath()).append(" ")
                        .append(constraintViolation.getMessage()).append("; ");
            }

            throw new RuntimeException(validateError.toString());
        }
    }
}
