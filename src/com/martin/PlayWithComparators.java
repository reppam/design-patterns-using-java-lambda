package com.martin;

import com.martin.chaining.function.Function;
import com.martin.comparator.Comparator;
import com.martin.model.Person;

public class PlayWithComparators {

    public static void main(String[] args) {

        Person mary = new Person("Marry", 28);
        Person john = new Person("John", 22);
        Person linda = new Person("Linda", 26);
        Person james = new Person("James", 32);
        Person jamesBis = new Person("James", 26);

        Function<Person, String> getname = p -> p.getName();
        Function<Person, Integer> getAge = p -> p.getAge();

        Comparator<Person> cmpName = Comparator.comparing(getname);

        System.out.println("Marry > John: " + (cmpName.compare(mary, john) > 0));
        System.out.println("John > James: " + (cmpName.compare(john, james) > 0));
        System.out.println("Linda > John: " + (cmpName.compare(linda, john) > 0));

        Comparator<Person> cmpNameReversed = cmpName.reversed();

        System.out.println("Marry > John: " + (cmpNameReversed.compare(mary, john) > 0));
        System.out.println("John > James: " + (cmpNameReversed.compare(john, james) > 0));
        System.out.println("Linda > John: " + (cmpNameReversed.compare(linda, john) > 0));

        Comparator<Person> cmpAge = Comparator.comparing(getAge);

        System.out.println("Marry > John: " + (cmpAge.compare(mary, john) > 0));
        System.out.println("John > James: " + (cmpAge.compare(john, james) > 0));
        System.out.println("Linda > John: " + (cmpAge.compare(linda, john) > 0));

        System.out.println("Comparing by names and ages:");
        Comparator<Person> cmp = cmpName.thenComparing(cmpAge);

        System.out.println("Marry > John: " + (cmp.compare(mary, john) > 0));
        System.out.println("John > James: " + (cmp.compare(john, james) > 0));
        System.out.println("Linda > John: " + (cmp.compare(linda, john) > 0));
        System.out.println("James > James Bis: " + (cmp.compare(james, jamesBis) > 0));

        System.out.println("Simplest way:");
        cmp = Comparator.comparing(Person::getName).thenComparing(Person::getAge);

        System.out.println("Marry > John: " + (cmp.compare(mary, john) > 0));
        System.out.println("John > James: " + (cmp.compare(john, james) > 0));
        System.out.println("Linda > John: " + (cmp.compare(linda, john) > 0));
        System.out.println("James > James Bis: " + (cmp.compare(james, jamesBis) > 0));

    }
}
