package com.github.melviml.ecs.utils;

import com.github.melviml.ecs.Component;
import java.util.Map;


public class ComponentResult {
    private final Long entityID;
    private final Map<Class<? extends Component>, Component> components;

    public ComponentResult(Long entityID, Map<Class<? extends Component>, Component> components){
        this.entityID = entityID;
        this.components = components;
    }

    public ComponentResult(Pair<Long, Map<Class<? extends Component>, Component>> result){
        this(result.a, result.b);
    }


    public <A extends Component> A get(Class<A> c){
        return (A)this.components.get(c);
    }

    public Long getID(){
        return this.entityID;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentResult result = (ComponentResult) o;
        return entityID.equals(result.entityID);
    }

    @Override
    public int hashCode() {
        return entityID.hashCode();
    }
}
