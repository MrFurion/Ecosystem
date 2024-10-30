package by.trubetski.enums;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    BINARY("Binary");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
