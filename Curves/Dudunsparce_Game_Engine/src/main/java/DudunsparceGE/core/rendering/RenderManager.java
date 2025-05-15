package DudunsparceGE.core.rendering;

import DudunsparceGE.Launcher;
import DudunsparceGE.core.Camera;
import DudunsparceGE.core.ShaderManager;
import DudunsparceGE.core.WindowManager;
import DudunsparceGE.core.entity.Entity;
import DudunsparceGE.core.entity.Model;
import DudunsparceGE.core.entity.terrain.Terrain;
import DudunsparceGE.core.utils.Transformation;
import DudunsparceGE.core.utils.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.*;
import java.util.HashMap;

public class RenderManager {

    private final WindowManager window;
    private EntityRenderer entityRenderer;
    //private ShaderManager shader;
    private TerrainRenderer terrainRenderer;

    //private Map<Model,List<Entity>> entities = new HashMap<>();

    public RenderManager() {

        window = Launcher.getWindow();
    }

    public void init() throws Exception {

        entityRenderer = new EntityRenderer();

        entityRenderer.init();
        terrainRenderer = new TerrainRenderer();
        terrainRenderer.init();


    }

    public void bind(Model model) {


     }

    public void unbind() {

    }

    public void prepare(Entity entity, Camera camera) {

    }

    public static void renderLights(Camera camera) {
        //lights here
    }

    public void render(Camera camera ) {
        clear();

        if(window.isResize()){
            GL20.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setResize(false);

        }

        entityRenderer.render(camera);
        terrainRenderer.render(camera);

    }

    public void processEntity(Entity entity) {
        List<Entity> entityList = entityRenderer.getEntities().get(entity.getModel());
        if(entityList != null) {
            entityList.add(entity);
        } else {
            List<Entity> newEntityList = new ArrayList<>();
            newEntityList.add(entity);
            entityRenderer.getEntities().put(entity.getModel(), newEntityList);
        }
    }

    public void processTerrain(Terrain terrain) {
        terrainRenderer.getTerrains().add(terrain);
    }

    public void clear(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


    }

    public void cleanup() {
        entityRenderer.cleanup();
        terrainRenderer.cleanup();

    }
}
