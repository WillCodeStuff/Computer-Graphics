package DudunsparceGE.core;

import DudunsparceGE.Launcher;
import DudunsparceGE.core.utils.Consts;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class EngineManager {

    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 60;
    private static int fps;
    private static float frameTime = 1.0f / FRAMERATE;

    private boolean isRunning;
    private MouseInput mouseInput;
    private WindowManager window;
    private GLFWErrorCallback errorCallback;

    private ILogic gameLogic;

    public void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        window = Launcher.getWindow();
        gameLogic = Launcher.getGame();
        mouseInput = new MouseInput();
        window.init();
        gameLogic.init();
        mouseInput.init();
    }

    public void start() throws Exception {
        init();
        if(isRunning){
            return;
        }
        run();
    }

    private void run() {
        isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean  render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime/(double)NANOSECOND;
            frameCounter += passedTime;

            input();

            while (unprocessedTime >= frameTime) {
                render = true;
                unprocessedTime -= frameTime;

                if(window.windowShouldClose()){
                    stop();
                }

                if(frameCounter>=NANOSECOND){
                    setFPS(frames);
                    window.setTitle(Consts.TITLE + " FPS: " + getFPS());
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if(render) {
                //input();
                update(frameTime);
                render();
                frames++;
            }


        }
        cleanup();
    }

    private void stop() {
        if(!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void input(){
        gameLogic.input();
        mouseInput.input();
    }

    private void render(){
        gameLogic.render();
        window.update();
    }

    private void update(float interval){
        gameLogic.update(interval, mouseInput );

    }

    private void cleanup(){
        window.cleanup();
        gameLogic.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFPS() {
        return fps;
    }

    public static void setFPS(int fps) {
        EngineManager.fps = fps;
    }
}
