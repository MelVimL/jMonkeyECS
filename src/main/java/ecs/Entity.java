package ecs;

import ecs.utils.Pair;

import java.util.List;
import java.util.Set;

public interface Entity {
    static Entity create(){
        return new EntityImpl();
    }
    Long getID();
    void add(Component c);

    List<Component> getAll();
    Component get(Class<? extends Component> c);

    default boolean hasAllComponents(Set<Class<? extends Component>> cs){
        for(Class<? extends Component> c: cs){
            if(!this.hasComponent(c)){
                return false;
            }
        }
        return true;
    }
    boolean hasComponent(Class<? extends Component> c);

}
