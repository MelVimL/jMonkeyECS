package com.github.melviml.ecs.examples.processors;

import com.github.melviml.ecs.Processor;
import com.github.melviml.ecs.examples.components.Position;
import com.github.melviml.ecs.examples.components.Visible;
import com.github.melviml.ecs.utils.ComponentResult;

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
