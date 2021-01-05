package com.github.melviml.jme.ecs.examples.components;

import com.jme3.math.Vector3f;
import com.github.melviml.jme.ecs.Component;

public class Velocity implements Component {
    public Vector3f force;

    public Velocity(Vector3f force){
        this.force = force;
    }
}
