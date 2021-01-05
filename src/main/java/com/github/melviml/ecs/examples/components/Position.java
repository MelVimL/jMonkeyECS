package com.github.melviml.ecs.examples.components;

import com.jme3.math.Vector3f;
import com.github.melviml.ecs.Component;

public class Position implements Component {
    public Vector3f value;

    public Position(Vector3f value){
        this.value = value;
    }
}
