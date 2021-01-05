package com.github.melviml.jme.ecs.examples.components;

import com.jme3.scene.Geometry;
import com.github.melviml.jme.ecs.Component;

public class Visible implements Component {
    public Geometry geom;

    public Visible(Geometry geom){
        this.geom = geom;
    }
}
