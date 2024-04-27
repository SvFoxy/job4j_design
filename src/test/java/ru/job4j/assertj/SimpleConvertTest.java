package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void toArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] simpleCollection = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(simpleCollection).isNotNull()
            /*все элементы выполняют условие*/
            .allSatisfy(e -> {
                assertThat(e.length()).isLessThan(10);
                assertThat(e.length()).isGreaterThan(0);
            })
            /*хотя бы один элемент выполняет условие*/
            .anySatisfy(e -> {
                assertThat(e.length()).isLessThan(5);
                assertThat(e).isEqualTo("four");
            })
            .allMatch(e -> e.length() < 10)
            .anyMatch(e -> e.length() == 5)
            .noneMatch(e -> e.length() < 1);
    }

    @Test
    void toList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List simpleCollection = simpleConvert.toList("first", "second", "three", "four", "five");
        /*первый элемент*/
        assertThat(simpleCollection).first()
                .isEqualTo("first");
        /*элемент по индексу*/
        assertThat(simpleCollection).element(0).isNotNull()
                .isEqualTo("first");
        /*последний элемент*/
        assertThat(simpleCollection).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkFilteredList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List simpleCollection = simpleConvert.toList("first", "second", "second", "four", "five");
        /*фильтруем источник по предикату и работаем с результатом фильтрации*/
        assertThat(simpleCollection).filteredOn(e -> e.equals("second"))
                .hasSize(2)
                .first().isEqualTo("second");
    }

    @Test
    void toSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleCollection = simpleConvert.toSet("first", "second", "three", "four", "second");
        assertThat(simpleCollection).hasSize(4)
                /*содержит элементы:*/
                .contains("first", "second", "three", "four")
                /*содержит это в любом порядке, дубликаты не важны:*/
                .containsOnly("first", "second", "three", "four")
                /*содержит только это и только в указанном порядке:*/
                .containsExactly("four", "three", "first", "second")
                /*содержит только это в любом порядке:*/
                .containsExactlyInAnyOrder("first", "second", "three", "four")
                /*содержит хотя бы один из:*/
                .containsAnyOf("first", "second")
                /*не содержит ни одного из:*/
                .doesNotContain("five", "six")
                /*начинается с последовательности:*/
                .startsWith("four", "three")
                /*заканчивается на последовательность:*/
                .endsWith("first", "second")
                /* содержит последовательность:*/
                .containsSequence("three", "first");
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert
                .toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                /*содержит ключи*/
                .containsKeys("first", "second", "three", "four", "five")
                /*содержит значения*/
                .containsValues(0, 1, 2, 3, 4)
                /*не содержит ключ*/
                .doesNotContainKey("zero")
                /*не содержит значение*/
                .doesNotContainValue(5)
                /*содержит пару ключ-значение*/
                .containsEntry("second", 1);
    }


}