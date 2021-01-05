package com.github.melviml.jme.ecs.examples;

import com.github.melVim.ecs.examples.components.*;
import com.github.melviml.jme.ecs.examples.processors.Input;
import com.github.melviml.jme.ecs.examples.processors.MovementParallel;
import com.github.melviml.jme.ecs.examples.processors.PhysicsParallel;
import com.github.melviml.ecs.examples.components.*;
import com.github.melviml.jme.ecs.examples.components.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.github.melviml.jme.ecs.Entity;
import com.github.melviml.jme.ecs.World;
import ecs.examples.components.*;

import java.util.Random;

public class simpleECSAppState extends AbstractAppState {
    private AppStateManager stateManager;
    private SimpleApplication app;
    private World world;
    private Random r = new Random();

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
        this.world = World.create();

        //adds

        //this.world.add(new Physics());
        this.world.add(new PhysicsParallel());
        //this.world.add(new Movement());
        this.world.add(new MovementParallel());
        this.world.add(new Input(app.getInputManager()));

        for(int i=0; i<10000; i++){
            Entity e  = Entity.create();
            e.add(new Mass(random(0,200), random(0,200)));
            e.add(new Position(new Vector3f(random(-1000,1000),random(-10000,10000), random(-1000,1000))));
            e.add(new Velocity(Vector3f.ZERO));
            e.add(new Visible(createCube(i)));
            e.add(new Jumpable());

            world.add(e);
        }
    }


    private float random(float from, float to){
        return r.nextFloat()*(to-from)+from;
    }

    private Geometry createCube(int i){
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box"+i, b);

        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        app.getRootNode().attachChild(geom);

        return geom;

    }
    @Override
    public void update(float tpf) {
        world.update(tpf);
    }

    @Override
    public void cleanup() {
        world.clean();
    }
}
