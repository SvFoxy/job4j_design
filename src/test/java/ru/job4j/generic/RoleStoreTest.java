package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void add() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new User("1", "Qwe"));
        User result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Qwe");
    }

    @Test
    void replace() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new User("1", "Qwe"));
        roleStore.replace("1", new User("1", "Asd"));
        User result = roleStore.findById("1");
        assertThat(result.getUsername()).isEqualTo("Asd");
    }

    @Test
    void delete() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new User("1", "Qwe"));
        roleStore.delete("1");
        User result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void findById() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new User("1", "Qwe"));
        roleStore.add(new User("2", "Asd"));
        User result = roleStore.findById("2");
        assertThat(result.getUsername()).isEqualTo("Asd");
    }
}