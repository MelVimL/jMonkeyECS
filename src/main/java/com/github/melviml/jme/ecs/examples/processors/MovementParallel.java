package com.github.melviml.jme.ecs.examples.processors;

import com.github.melviml.jme.ecs.Processor;
import com.github.melviml.jme.ecs.examples.components.Position;
import com.github.melviml.jme.ecs.examples.components.Visible;

public class MovementParallel extends Processor {
    @Override
    public void update(float tpf) {

        world.getComponents(Position.class, Visible.class).parallelStream().forEach(
                (result)->{
                    Position pos = result.get(Position.class);
                    Visible vis = result.get(Visible.class);

                    vis.geom.setLocalTranslation(pos.value);
                }
        );


    }
}
