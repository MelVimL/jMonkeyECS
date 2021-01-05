package com.github.melviml.jme.ecs.examples.components;

import com.github.melviml.jme.ecs.Component;

public class Mass implements Component {
    public float weight;
    public float density;

    public Mass(float weight, float density){
        this.weight = weight;
        this.density = density;
    }

}
