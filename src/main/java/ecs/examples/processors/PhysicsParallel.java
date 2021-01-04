package ecs.examples.processors;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import ecs.Processor;
import ecs.examples.components.Mass;
import ecs.examples.components.Position;
import ecs.examples.components.Velocity;
import ecs.utils.ComponentResult;

public class PhysicsParallel extends Processor {
    @Override
    public void update(float tpf) {

        //Gravitation
        world.getComponents(Velocity.class).parallelStream().forEach(
                (result) -> {
                    Velocity velo = result.get(Velocity.class);
                    velo.force = velo.force.add(new Vector3f(0f, -9.8f, 0f));
                }
        );
        //AirFriction

        for (ComponentResult result : world.getComponents(Mass.class, Velocity.class)) {

        }
        //Move

        world.getComponents(Velocity.class, Position.class).parallelStream().forEach(
                (result) -> {
                    Velocity velo = result.get(Velocity.class);
                    Position pos = result.get(Position.class);

                    pos.value = FastMath.interpolateLinear(tpf, pos.value, pos.value.add(velo.force));

                }
        );

    }
}
