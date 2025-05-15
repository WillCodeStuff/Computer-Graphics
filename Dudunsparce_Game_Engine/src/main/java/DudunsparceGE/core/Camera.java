package DudunsparceGE.core;

import org.joml.Vector3f;

public class Camera {

    private Vector3f position, rotation;

    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
    }

    public Camera( Vector3f position,Vector3f rotation) {
        this.rotation = rotation;
        this.position = position;
    }

    public void movePosition( float x, float y, float z) {
        if(z!=0){
            position.x += (float)Math.sin(Math.toRadians(rotation.y))*z*-1.0f;
            position.z += (float)Math.cos(Math.toRadians(rotation.y))*z;
        }
        if(x!=0){
            position.x += (float)Math.sin(Math.toRadians(rotation.y-90))*x*-1.0f;
            position.z += (float)Math.cos(Math.toRadians(rotation.y-90))*x;
        }

        position.y += y;
    }

    public void setPosition( float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public void setRotation( float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public void rotate( float x, float y, float z) {
        rotation.x += x;
        rotation.y += y;
        rotation.z += z;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
