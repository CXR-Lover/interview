package com;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class entity {
    private String name;
    private int age;

    public entity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public entity() {
    }
}
