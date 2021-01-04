package ecs.examples.components;

import ecs.Component;

public class Mass implements Component {
    public float weight;
    public float density;

    public Mass(float weight, float density){
        this.weight = weight;
        this.density = density;
    }

}