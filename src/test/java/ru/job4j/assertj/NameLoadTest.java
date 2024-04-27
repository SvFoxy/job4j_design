package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void checkValidateWordMessageNotContain() {
        NameLoad nameLoad = new NameLoad();
        String names = "key, value, key1, value1, key2, value2";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageMatching("^.+")
                    .hasMessageContaining(names)
                    .hasMessageContaining("does not contain the symbol '='");
    }

    @Test
    void checkValidateWordMessageNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String names = "=key=value, key1=value1, key2=value2";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkValidateWordMessageNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String names = "key=";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void checkGetMap() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class);
    }
}