package org.ktest.java8.study.lambdas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.ktest.java8.study.lambdas.ComponentFactory.ComponentType.BUTTON;
import static org.ktest.java8.study.lambdas.ComponentFactory.ComponentType.LABEL;

public class ComponentFactory {
    public static enum ComponentType {
        BUTTON, LABEL
    }
    static Map<ComponentType, Function<String, Component>> map = new HashMap<>();
    static {
        map.put(BUTTON, Button::new);
        map.put(LABEL, Label::new);
    }

    public static Component createComponent(ComponentType type, String label) {
        Function<String, Component> function = map.get(type);
        if (function != null){
            return function.apply(label);
        }
        throw new IllegalArgumentException("No such component " + type);
    }

}
