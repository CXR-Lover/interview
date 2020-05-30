package count;

import lombok.Getter;
import lombok.Setter;

public enum CountryEnum {
    ONE(1, "Цы"), TWO(2, "Гў"), THREE(3, "ед"), FOUR(4, "КЋ"), FIVE(5, "бр"), SIX(6, "Уї");

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
