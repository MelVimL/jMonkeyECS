package com.github.melviml.jme.ecs;

import com.github.melviml.jme.ecs.utils.ComponentResult;
import java.util.Set;

public interface Database {
    static Database create(){
        return new DatabaseImpl();
    }

    void addEntity(Entity entity);
    void remove(Long id);
    Entity getEntity(Long id);
    int numberOfEntities();

    Set<ComponentResult> getComponents(Class<? extends Component> ... components);
    int numberOfComponents();

    void addComponent(Long id, Component c);
}
