package DudunsparceGE.test;

import DudunsparceGE.Launcher;
import DudunsparceGE.core.*;
import DudunsparceGE.core.entity.Entity;
import DudunsparceGE.core.entity.Model;
import DudunsparceGE.core.entity.Texture;
import DudunsparceGE.core.entity.terrain.Terrain;
import DudunsparceGE.core.rendering.RenderManager;
import DudunsparceGE.core.utils.Consts;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGameLogic implements ILogic {

    private int direction =0;
    private float colour =0.0f;

    private static final float cameraMoveSpeed = 0.05f;

    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;

    private List<Entity> entities;
    private List<Terrain> terrains;
    private Camera camera;

    private Vector3f cameraInc;

    public TestGameLogic() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0,0,0);
    }


    @Override
    public void init() throws Exception {
        renderer.init();

        terrains = new ArrayList<>();
        Terrain terrain = new Terrain(new Vector3f(-200,-1,-800),loader);
        terrains.add(terrain);

        entities = new ArrayList<Entity>();
        Random rnd = new Random();
        for(int i=0; i<200; i++){
            float x = rnd.nextFloat() *100 - 50;
            float y = rnd.nextFloat() * 100 - 50;
            float z = rnd.nextFloat() * -300;
            Model model=loader.loadOBJModel("/models/cube.obj");
            model.setTexture(new Texture(loader.loadTexture("textures/pink.png")));
            if(rnd.nextFloat()>0.45)
                model.setTexture(new Texture(loader.loadTexture("textures/lime.png")));
            if(rnd.nextFloat()>0.66)
                model.setTexture(new Texture(loader.loadTexture("textures/purple.png")));



            entities.add(
                    new Entity(model,new Vector3f(x,y,z),
                    new Vector3f(rnd.nextFloat() * 180,rnd.nextFloat() * 180,0),1)
            );
        }

        /*{
        float[] vertices = {
                -0.5f,  0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,  0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3,1,2
        };

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0
        };

        float[] vertices = new float[] {
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
        };
        float[] textCoords = new float[]{
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                0.0f, 0.0f,
                0.5f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 0.5f,
                0.5f, 0.5f,
                0.0f, 1.0f,
                0.5f, 1.0f,
                0.0f, 0.0f,
                0.0f, 0.5f,
                0.5f, 0.0f,
                0.5f, 0.5f,
                0.5f, 0.0f,
                1.0f, 0.0f,
                0.5f, 0.5f,
                1.0f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
                8, 10, 11, 9, 8, 11,
                12, 13, 7, 5, 12, 7,
                14, 15, 6, 4, 14, 6,
                16, 18, 19, 17, 16, 19,
                4, 6, 7, 5, 4, 7,
        };

         }*/
        Model model=loader.loadOBJModel("/models/bunny.obj");
        model.setTexture(new Texture(loader.loadTexture("textures/purple.png")));
        entities.add(new Entity(model,new Vector3f(0,0,-2f),new Vector3f(0,0,0),2));

    }

    @Override
    public void input() {
        cameraInc.set(0,0,0);
        if(window.isKeyPressed(GLFW.GLFW_KEY_W)){
            cameraInc.z = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_A)){
            cameraInc.x = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_S)){
            cameraInc.z = 1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_D)){
            cameraInc.x = 1;
        }



        if(window.isKeyPressed(GLFW.GLFW_KEY_UP)){
            cameraInc.z = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT)){
            cameraInc.x = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN)){
            cameraInc.z = 1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_RIGHT)){
            cameraInc.x = 1;
        }

        if(window.isKeyPressed(GLFW.GLFW_KEY_Z)){
            cameraInc.y = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_X)){
            cameraInc.y = 1;
        }




    }

    @Override
    public void update(float interval,MouseInput mouseInput) {
        camera.movePosition(cameraMoveSpeed*cameraInc.x,cameraMoveSpeed*cameraInc.y,cameraMoveSpeed*cameraInc.z);

        if(mouseInput.isRightButtonPress()){
            Vector2f rotVec = mouseInput.getDisplayVec();

            camera.rotate(rotVec.x * Consts.MOUSE_SENSITIVITY,rotVec.y* Consts.MOUSE_SENSITIVITY,0);
            mouseInput.setDisplayVec(0,0);
        }

        //entity.incRot(0.0f,0.5f,0.0f);

        for(Entity e: entities){
            renderer.processEntity(e);
        }
        for(Terrain t: terrains){
            renderer.processTerrain(t);
        }
    }

    @Override
    public void render() {
        if(window.isResize()){
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(true);
        }

        window.setClearColor(0.0f,0.6f,0.8f,0.0f);
        renderer.render(camera);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
