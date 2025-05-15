package DudunsparceGE;




import DudunsparceGE.core.EngineManager;
import DudunsparceGE.core.WindowManager;
import DudunsparceGE.core.utils.Consts;
import DudunsparceGE.test.TestGameLogic;
import org.lwjgl.Version;

public class Launcher {

    private static WindowManager window;
    private static TestGameLogic game;


    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        window = new WindowManager(false, 2000, 2000, Consts.TITLE);
        game = new TestGameLogic();
        EngineManager engine = new EngineManager();

        try{
            engine.start();
        } catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public static WindowManager getWindow() {
        return window;
    }

    public static TestGameLogic getGame() {
        return game;
    }
}
