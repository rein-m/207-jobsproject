package entity;

public abstract class GenericFactory<T> {
    public final T create(String name, String password){
        return createEntity(name, password);
    }

    protected abstract T createEntity(String name, String password);
}