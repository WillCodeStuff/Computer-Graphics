package DudunsparceGE.core.rendering;

import DudunsparceGE.Launcher;
import DudunsparceGE.core.Camera;
import DudunsparceGE.core.ShaderManager;
import DudunsparceGE.core.entity.Entity;
import DudunsparceGE.core.entity.Model;
import DudunsparceGE.core.entity.terrain.Terrain;
import DudunsparceGE.core.utils.Transformation;
import DudunsparceGE.core.utils.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerrainRenderer implements iRenderer{




    ShaderManager shader;
    private List<Terrain> terrains;



    public TerrainRenderer() throws Exception{

        terrains = new ArrayList<Terrain>();
        shader = new ShaderManager();
    }

    @Override
    public void init() throws Exception {

        //shader = new ShaderManager();
        shader.createVertexShader(Utils.loadResource("/shaders/vertex.vs"));
        shader.createFragmentShader(Utils.loadResource("/shaders/fragment.fs"));
        shader.link();
        shader.createUniform("textureSampler");
        shader.createUniform("transformationMatrix");
        shader.createUniform("projectionMatrix");
        shader.createUniform("viewMatrix");

    }

    @Override
    public void render(Camera camera) {

        shader.bind();

        shader.setUniform("projectionMatrix", Launcher.getWindow().updateProjectionMatrix());
        RenderManager.renderLights(camera);
        for(Terrain terrain: terrains) {
            bind(terrain.getModel());
            prepare(terrain, camera);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            unbind();
        }


        terrains.clear();
        shader.unbind();

    }

    @Override
    public void bind(Model model) {


        GL30.glBindVertexArray(model.getId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());



    }

    @Override
    public void unbind() {

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

    }

    @Override
    public void prepare(Object terrain, Camera camera) {

        shader.setUniform("textureSampler", 0);
        shader.setUniform("transformationMatrix", Transformation.createTransformationMatrix((Terrain) terrain));
        shader.setUniform("viewMatrix",Transformation.getViewMatrix(camera));

    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }
}
