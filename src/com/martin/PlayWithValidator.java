package com.martin;

import com.martin.model.Person;
import com.martin.validators.Validator;

public class PlayWithValidator {

    public static void main(String[] args) {

        Person sarah = new Person("Sarah", 29);
        Person james = new Person(null, 29);
        Person marry = new Person("Marry", -10);
        Person john = new Person("John", 1_000);
        Person linda = new Person(null, 1_000);

        sarah =
        Validator.validate(p -> p.getName() != null, "The name should not be null")
                .on(sarah)
                .validate();
        System.out.println("Sarah: " + sarah);

        james =
        Validator.validate(p -> p.getName() != null, "The name should not be null")
                .on(james)
                .validate();
        System.out.println("James: " + james);

        marry =
        Validator.validate(p -> p.getName() != null, "The name should not be null")
                .thenValidate(p -> p.getAge() > 0, "The age should be greather than 0")
                .thenValidate(p -> p.getAge() < 150, "The age should be less than150")
                .on(marry)
                .validate();
        System.out.println("Marry: " + marry);

        john =
                Validator.validate(p -> p.getName() != null, "The name should not be null")
                        .thenValidate(p -> p.getAge() > 0, "The age should be greather than 0")
                        .thenValidate(p -> p.getAge() < 150, "The age should be less than150")
                        .on(john)
                        .validate();
        System.out.println("John: " + john);

        linda =
        Validator.validate(p -> p.getName() != null, "The name should not be null")
                .thenValidate(p -> p.getAge() > 0, "The age should be greather than 0")
                .thenValidate(p -> p.getAge() < 150, "The age should be less than150")
                .on(linda)
                .validate();
        System.out.println("Linda: " + linda);

    }
}
