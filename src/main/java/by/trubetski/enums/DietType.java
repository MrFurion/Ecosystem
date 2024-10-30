package by.trubetski.enums;

public enum DietType {
    CARNIVORE("Хищник"),  //
    HERBIVORE("Травоядное"),  //
    OMNIVORE("Всеядное");//

    private final String dietType;

    DietType(String dietType) {
        this.dietType = dietType;
    }

    public String getDietType() {
        return dietType;
    }
}
