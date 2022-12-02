package com.skypro.employee_book.NameOfEmployees;

import org.springframework.util.StringUtils;

public class NamesOfEmployees {
    private final String lastName;
    private final String firstName;
    private final String patronymic;

    public NamesOfEmployees(String lastName, String firstName, String patronymic) {
        if (lastName == null || lastName.length() == 0) {
            throw new RuntimeException("400 Bad Request.");
        } else {
            lastName=lastName.trim();
            lastName=lastName.replaceAll("[0-9]", "");
            this.lastName = StringUtils.capitalize(lastName);
        }

        if (firstName == null || firstName.length() == 0) {
            throw new RuntimeException("400 Bad Request.");
        } else {
            firstName=firstName.trim();
            firstName=firstName.replaceAll("[0-9]", "");
            this.firstName = StringUtils.capitalize(firstName);
        }
        if (patronymic == null || patronymic.length() == 0) {
            throw new RuntimeException("400 Bad Request.");
        } else {
            patronymic=patronymic.trim();
            patronymic=patronymic.replaceAll("[0-9]", "");
            this.patronymic = StringUtils.capitalize(patronymic);
        }
    }

    @Override
    public String toString() {
        return " " + lastName +
                " " + firstName +
                " " + patronymic;
    }
}
