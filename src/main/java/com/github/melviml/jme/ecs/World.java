package com.github.melviml.jme.ecs;

import com.github.melviml.jme.ecs.utils.ComponentResult;

import java.util.Set;

public interface World {
    static World create(){
        return new WorldImpl();
    }
    void clean();
    void update(float tpf);

    //Processor
    void add(Processor processor);
    int processors();


    //Entities
    void add(Entity entity);
    void remove(Long id);

    int entities();

    //components
    void addComponent(Long id, Component c);
    Set<ComponentResult> getComponents(Class<? extends Component> ... components);

    int components();




}
