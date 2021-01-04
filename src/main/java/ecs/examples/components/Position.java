package ecs.examples.components;

import com.jme3.math.Vector3f;
import ecs.Component;

public class Position implements Component {
    public Vector3f value;

    public Position(Vector3f value){
        this.value = value;
    }
}
