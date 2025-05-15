package DudunsparceGE.core.entity;

import org.joml.Vector3f;

public class Entity {
    private Model model;
    private Vector3f position, rotation;
    private float scale;

    public Entity(Model model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void incPoz(float x, float y, float z) {
        position.x += x;
        position.y += y;
        position.z += z;
    }
    public void incRot(float x, float y, float z) {
        rotation.x += x;
        rotation.y += y;
        rotation.z += z;
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Model getModel() {
        return model;
    }
}
