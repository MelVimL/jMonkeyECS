package ecs.examples.processors;

import ecs.Processor;
import ecs.examples.components.Position;
import ecs.examples.components.Visible;
import ecs.utils.ComponentResult;

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
