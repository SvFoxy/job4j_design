package ru.job4j.generic;

public class RoleStore implements Store<User> {

    private final Store<User> rolestore = new MemStore<>();

    @Override
    public void add(User model) {
        rolestore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return rolestore.replace(id, model);
    }

    @Override
    public void delete(String id) {
        rolestore.delete(id);
    }

    @Override
    public User findById(String id) {
        return rolestore.findById(id);
    }
}
