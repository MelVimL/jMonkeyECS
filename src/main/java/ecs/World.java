package ecs;

import ecs.utils.ComponentResult;
import java.util.Set;

public interface World {
    static World create(){
        return new WorldImpl();
    }
    void clean();
    void update(float tpf);

    //Processor
    void add(Processor processor);


    //Entities
    void add(Entity entity);
    void remove(Long id);
    //components
    void addComponent(Long id, Component c);
    Set<ComponentResult> getComponents(Class<? extends Component> ... components);




}
