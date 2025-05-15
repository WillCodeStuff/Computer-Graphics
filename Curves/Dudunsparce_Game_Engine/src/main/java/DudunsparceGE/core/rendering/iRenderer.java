package DudunsparceGE.core.rendering;

import DudunsparceGE.core.Camera;
import DudunsparceGE.core.entity.Model;

public interface iRenderer<T> {

    public void init() throws Exception;

    public void render(Camera camera);

    public void bind(Model model);
    public void unbind();

    public void prepare(T t, Camera camera);

    public void cleanup();
}
