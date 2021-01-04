package ecs;


import java.util.*;

public class EntityImpl implements Entity {
    private final Long id;
    private final Set<Component> components = new HashSet<>();

    public EntityImpl(){
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
    @Override
    public Long getID() {
        return this.id;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public List<Component> getAll() {
        return new LinkedList<>(components);
    }

    @Override
    public Component get(Class<? extends Component> c) {
        for(Component component : components){
            if(component.getClass() == c){
                return component;
            }
        }
        return null;
    }

    @Override
    public boolean hasComponent(Class<? extends Component> c) {
        return this.get(c) != null;
    }
}
