package count;

import lombok.Getter;
import lombok.Setter;

public enum CountryEnum {
    ONE(1, "��"), TWO(2, "��"), THREE(3, "��"), FOUR(4, "��"), FIVE(5, "��"), SIX(6, "��");

    @Getter
    private Integer key;
    @Setter
    private String value;

    CountryEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String findCountryEnum(Integer key) {
        CountryEnum[] countries = CountryEnum.values();
        for (CountryEnum countryEnum : countries) {
            if (key == countryEnum.key) {
                return countryEnum.value;
            }
        }
        return null;
    }
}
