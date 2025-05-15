package DudunsparceGE.core;

import DudunsparceGE.Launcher;
import com.sun.tools.javac.Main;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class MouseInput {
    private final Vector2d previousMousePosition, currentMousePosition;
    private final Vector2f displayVec;

    private boolean inWindow = false,
            leftButtonPress = false,
            rightButtonPress = false;

    public MouseInput() {

        previousMousePosition = new Vector2d(-1,-1);
        currentMousePosition = new Vector2d(0,0);
        displayVec = new Vector2f();
    }

    public void init(){
        GLFW.glfwSetCursorPosCallback(Launcher.getWindow().getWindow(),(window,xpos,ypos) -> {
            currentMousePosition.x = xpos;
            currentMousePosition.y = ypos;

        });

        GLFW.glfwSetCursorEnterCallback(Launcher.getWindow().getWindow(),(window,entered) -> {
            inWindow = entered;
        });

        GLFW.glfwSetMouseButtonCallback(Launcher.getWindow().getWindow(), (window,button,action,mods) -> {
           leftButtonPress = (button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS);
           rightButtonPress = (button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS);


        });
    }

    public void input(){
        //displayVec.x = 0;
        //displayVec.y = 0;

        if(previousMousePosition.x > 0 && previousMousePosition.y > 0 && inWindow){
            double x = currentMousePosition.x - previousMousePosition.x;
            double y = currentMousePosition.y - previousMousePosition.y;
            boolean rotateX = x!=0;
            boolean rotateY = y!=0;
            if(rotateX){
                displayVec.y = (float) x;
            }
            if(rotateY){
                displayVec.x = (float) y;
            }

        }
        previousMousePosition.x = currentMousePosition.x;
        previousMousePosition.y = currentMousePosition.y;
    }

    public boolean isLeftButtonPress() {
        return leftButtonPress;
    }

    public boolean isRightButtonPress() {
        return rightButtonPress;
    }

    public Vector2f getDisplayVec() {
        return displayVec;
    }

    public void setDisplayVec(float x, float y) {
        displayVec.x = x;
        displayVec.y = y;
    }
}