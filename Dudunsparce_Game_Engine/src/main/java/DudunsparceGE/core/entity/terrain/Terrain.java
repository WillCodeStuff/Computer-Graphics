package DudunsparceGE.core.entity.terrain;

import DudunsparceGE.core.ObjectLoader;
import DudunsparceGE.core.entity.Model;
import DudunsparceGE.core.entity.Texture;
import org.joml.Vector3f;
import org.lwjgl.system.CallbackI;

public class Terrain {
    private static final float SIZE =800;
    private static final int VERTEX_COUNT = 120;

    private Vector3f position;

    private Model model;

    public Terrain(Vector3f position, ObjectLoader loader) {
        this.position = position;
        model = generateTerrain(loader);
        try {
            this.model.setTexture(new Texture(loader.loadTexture("textures/gray.png")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Model generateTerrain(ObjectLoader loader) {

        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count*3];
        float[] normals = new float[count*3];
        float[] texCoords = new float[count*2];
        int[] indices = new int[6 * (VERTEX_COUNT -1)*(VERTEX_COUNT -1)];
        int vertexPointer = 0;

        for (int i = 0; i < VERTEX_COUNT; i++) {
            for (int j = 0; j < VERTEX_COUNT; j++) {
                vertices[vertexPointer * 3] = j/(VERTEX_COUNT-1f) * SIZE;
                vertices[vertexPointer * 3 +1] = 0;//j/(VERTEX_COUNT-1f) * SIZE;
                vertices[vertexPointer * 3 +2] = i/(VERTEX_COUNT-1f) * SIZE;
                normals[vertexPointer * 3] = 0;
                normals[vertexPointer * 3 +1] = 1;
                normals[vertexPointer * 3 +2] = 0;
                texCoords[vertexPointer * 2] = j/(VERTEX_COUNT-1f) * SIZE;
                texCoords[vertexPointer * 2 + 1] = i/(VERTEX_COUNT-1f) * SIZE;
                vertexPointer++;
            }
        }

        int pointer =0;
        for (int i = 0; i < VERTEX_COUNT-1.0; i++) {
            for (int j = 0; j < VERTEX_COUNT-1; j++) {
                int topLeft = i*(VERTEX_COUNT) +j;
                int topRight = topLeft + 1;
                int bottomLeft = ((i+ 1) * VERTEX_COUNT) +j;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }

        return loader.loadModel(vertices,texCoords,indices);

    }

    public Vector3f getPosition() {
        return position;
    }

    public Model getModel() {
        return model;
    }
}
