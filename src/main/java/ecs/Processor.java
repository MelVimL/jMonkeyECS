package ecs;

public abstract class Processor {
    protected World world;

    public void setWorld(World world){
        this.world = world;
    }

    public abstract void update(float tpf);
}
