package com.martin.validators;

import com.martin.model.Person;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Validator {

    ValidatorSupplier on(Person p);

    default Validator thenValidate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                if (predicate.test(p)) {
                    return () -> p;
                } else {
                    return () -> {
                        ValidationException exception = new ValidationException("The object is not valid");
                        exception.addSuppressed(new IllegalArgumentException(errorMessage));
                        throw exception;
                    };
                }
            } catch (ValidationException e) {
                if (predicate.test(p)) {
                    return () -> {throw e;};
                } else {
                    e.addSuppressed(new IllegalArgumentException(errorMessage));
                    return () -> {throw e;};
                }
            }
        };
    }

    static Validator validate(Predicate<Person> predicate, String errorMessage) {
        return p -> {
            if (predicate.test(p)) {
                return () -> p;
            } else {
                return () -> {
                    ValidationException exception = new ValidationException("The object is not valid");
                    exception.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw exception;
                };
            }
        };
    }

    interface ValidatorSupplier extends Supplier<Person> {

        default Person validate(){
            return get();
        }
    }

    static class ValidationException extends RuntimeException {
        public ValidationException(String errorMessage) {
            super(errorMessage);
        }

    }
}
