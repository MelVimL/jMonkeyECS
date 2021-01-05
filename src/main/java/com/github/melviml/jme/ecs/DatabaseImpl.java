package com.github.melviml.jme.ecs;

import com.github.melviml.jme.ecs.utils.ComponentResult;
import ecs.utils.*;

import java.util.*;

public class DatabaseImpl implements Database {
    Map<Long, Entity> idEntityMap= new HashMap<>();
    Map<Class<? extends Component>, List<Component>> components = new HashMap<>();
    Map<Class<? extends Component>, Set<Long>> componentEntityMap = new HashMap<>();
    List<Entity> entities = new LinkedList<>();
    Map<Set<Class<? extends Component>>,Set<ComponentResult>> cache = new HashMap<>();

    public DatabaseImpl(){

    }
    @Override
    public void addEntity(Entity entity) {
        Long id = entity.getID();

        this.idEntityMap.put(id, entity);
        this.entities.add(entity);
        for(Component component :entity.getAll()){
            Class<? extends Component> c = component.getClass();
            this.addToComponents(c, component);
            this.addToComponentEntity(id, c);
            this.addToCache(entity);
        }
    }

    @Override
    public void remove(Long id) {
        Entity entity = idEntityMap.get(id);

        remove_from_cache(entity);

        entities.remove(entity);
        idEntityMap.remove(id);
        //TODO: REMOVE FROM COMPONENTS

    }

    private void remove_from_cache(Entity entity){

        for(Set<Class<? extends Component>> cached: this.cache.keySet()){
            if(entity.hasAllComponents(cached)){
                Map<Class<? extends Component>, Component> components = new HashMap<>();
                for(Class<? extends Component> c: cached){
                    components.put(c, entity.get(c));
                }
                cache.get(cached).remove(new ComponentResult(entity.getID(), components ));

            }
        }
    }

    private void addToCache(Entity entity) {
        for(Set<Class<? extends Component>> cached: this.cache.keySet()){
            if(entity.hasAllComponents(cached)){
                Map<Class<? extends Component>, Component> components = new HashMap<>();

                for(Class<? extends Component> c: cached){
                    components.put(c, entity.get(c));
                }
                cache.get(cached).add(new ComponentResult(entity.getID(), components ));
            }
        }
    }

    private void addToComponentEntity(Long id, Class<? extends Component> c) {
        Set<Long> result = componentEntityMap.getOrDefault(c, new LinkedHashSet<>());
        result.add(id);
        componentEntityMap.putIfAbsent(c, result);
    }

    private void addToComponents(Class<? extends Component> c, Component component){
        
        List<Component> result = components.getOrDefault(c, new ArrayList<Component>());
        result.add(component);
        components.putIfAbsent(c, result);    
    }

    @Override
    public Entity getEntity(Long id) {
        return idEntityMap.get(id);
    }

    private Set<ComponentResult> getResultSet(Set<Class<? extends Component>> components){

        updateCache(components);

        return  cache.get(components);
    }
    @Override
    public Set<ComponentResult> getComponents(Class<? extends Component> ... components) {
        Set<Class<? extends Component>> search = new LinkedHashSet(Arrays.asList(components));

        return getResultSet(search);
    }

    @Override
    public void addComponent(Long id, Component c) {
        Entity e = idEntityMap.get(id);
        e.add(c);
        addEntity(e);
    }

    private void updateCache(Set<Class<? extends Component>> search) {
        if(!cache.containsKey(search)){
            cache.put(search, new LinkedHashSet<>());
            for(Entity entity: entities){
                addToCache(entity);
            }
        }
    }
}
