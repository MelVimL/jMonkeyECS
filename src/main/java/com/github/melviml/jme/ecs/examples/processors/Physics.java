package com.github.melviml.jme.ecs.examples.processors;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.github.melviml.jme.ecs.Processor;
import com.github.melviml.jme.ecs.examples.components.Mass;
import com.github.melviml.jme.ecs.examples.components.Position;
import com.github.melviml.jme.ecs.examples.components.Velocity;
import com.github.melviml.jme.ecs.utils.ComponentResult;

public class Physics extends Processor {
    @Override
    public void update(float tpf) {

        //Gravitation
        for(ComponentResult result: world.getComponents(Velocity.class)){
            Velocity velo = result.get(Velocity.class);
            velo.force = velo.force.add(new Vector3f(0f,-9.8f, 0f));
        }
        //AirFriction

        for(ComponentResult result: world.getComponents(Mass.class, Velocity.class)){

        }
        //Move

        for(ComponentResult result: world.getComponents(Velocity.class, Position.class)){
            Velocity velo = result.get(Velocity.class);
            Position pos = result.get(Position.class);

            pos.value = FastMath.interpolateLinear(tpf, pos.value, pos.value.add(velo.force));

        }

    }
}
