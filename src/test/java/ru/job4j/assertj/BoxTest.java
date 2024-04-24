package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("cube")
                .doesNotContain("Unknown")
                .startsWithIgnoringCase("c")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUNKNOWN() {
        Box box = new Box(20, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .contains("Unknown", "object")
                .doesNotContain("Sphere","Tetrahedron", "Cube")
                .startsWith("Unknown")
                .endsWith("object")
                .isEqualTo("Unknown object");
    }

    @Test
    void isNumberOfVertices0() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isEven()
                .isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isNumberOfVertices8() {
        Box box = new Box(20, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(20, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void getArea() {
        Box box = new Box(0, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(50.26d, withPrecision(0.006d))
                .isCloseTo(50.26d, withPrecision(0.01d))
                .isCloseTo(50.26d, Percentage.withPercentage(1.0d))
                .isGreaterThan(50.26d)
                .isLessThan(50.27d);
    }

    @Test
    void getArea20() {
        Box box = new Box(20, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(0.0d);
    }


}
