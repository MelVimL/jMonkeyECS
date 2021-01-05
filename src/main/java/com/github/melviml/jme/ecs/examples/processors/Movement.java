package com.github.melviml.jme.ecs.examples.processors;

import com.github.melviml.jme.ecs.Processor;
import com.github.melviml.jme.ecs.examples.components.Position;
import com.github.melviml.jme.ecs.examples.components.Visible;
import com.github.melviml.jme.ecs.utils.ComponentResult;

public class Movement extends Processor {
    @Override
    public void update(float tpf) {

        for(ComponentResult result: world.getComponents(Position.class, Visible.class)){
            Position pos = result.get(Position.class);
            Visible vis = result.get(Visible.class);

            vis.geom.setLocalTranslation(pos.value);
        }
    }
}
