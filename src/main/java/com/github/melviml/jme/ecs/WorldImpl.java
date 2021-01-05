package com.github.melviml.jme.ecs;

import com.github.melviml.jme.ecs.utils.ComponentResult;
import java.util.*;

public class WorldImpl implements World {
    Set<Processor> processors = new LinkedHashSet<>();
    Database database;

    public WorldImpl(){
        database = Database.create();
    }

    @Override
    public void clean() {
        processors = new LinkedHashSet<>();
    }

    @Override
    public void update(float tpf) {
        for(Processor p: processors){
            p.update(tpf);
        }
    }

    @Override
    public void add(Processor processor) {
        processor.setWorld(this);
        this.processors.add(processor);
    }

    @Override
    public void add(Entity entity) {
        database.addEntity(entity);
    }

    @Override
    public void remove(Long id) {
        database.remove(id);
    }


    @Override
    public void addComponent(Long id, Component c) {
        database.addComponent(id, c);
    }

    @Override
    public Set<ComponentResult> getComponents(Class<? extends Component> ... components) {
        return database.getComponents(components);
    }


}
