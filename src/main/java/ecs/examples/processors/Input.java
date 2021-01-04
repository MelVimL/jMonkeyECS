package ecs.examples.processors;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import ecs.Processor;
import ecs.examples.components.Jumpable;
import ecs.examples.components.Velocity;
import ecs.utils.ComponentResult;
import ecs.utils.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Input extends Processor {
    Map<String, List<Pair<Float, Float>>> analogLog = new HashMap();
    Map<String, List<Pair<Boolean, Float>>> actionLog = new HashMap();

    private ActionListener actionListener = (name, isPressed, tpf)-> {
        this.actionLog.putIfAbsent(name, new LinkedList<>());
        this.actionLog.get(name).add(new Pair<>(isPressed, tpf));
    };

    private AnalogListener analogListener = (name, value, tpf)->{
        this.analogLog.putIfAbsent(name, new LinkedList<>());
        this.analogLog.get(name).add(new Pair<>(value, tpf));
    };

    public Input(InputManager inputManager){
        inputManager.addMapping("JUMP", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addListener(actionListener, "JUMP");
        inputManager.addListener(analogListener, "JUMP");
    }
    @Override
    public void update(float tpf) {
        if(actionLog.containsKey("JUMP")) {
            Vector3f force = Vector3f.ZERO;
            for(Pair<Boolean,Float> jump: actionLog.get("JUMP")){

                if(jump.a){
                    force = Vector3f.UNIT_Y.mult(200);
                    break;
                };
            }
            for (ComponentResult result : world.getComponents(Jumpable.class, Velocity.class)) {

                Velocity velo = result.get(Velocity.class);
                velo.force = force;
            }

        }

        actionLog.clear();
        analogLog.clear();
    }
}
