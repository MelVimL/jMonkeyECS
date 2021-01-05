package com.github.melviml.ecs;

import com.github.melviml.ecs.utils.ComponentResult;
import ecs.utils.*;
import java.util.Set;

public interface Database {
    static Database create(){
        return new DatabaseImpl();
    }

    void addEntity(Entity entity);
    void remove(Long id);
    Entity getEntity(Long id);


    Set<ComponentResult> getComponents(Class<? extends Component> ... components);


    void addComponent(Long id, Component c);
}
