package DudunsparceGE.core.utils;

import org.lwjgl.system.MemoryUtil;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static FloatBuffer storeDataInFloatBuffer(float[] data){

        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;

    }

    public static IntBuffer storeDataInIntBuffer(int[] data){

        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();
        return buffer;

    }

    public static String loadResource(String fileName) throws Exception {
        String results;
        try(InputStream in = Utils.class.getResourceAsStream(fileName);
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())){
            results = scanner.useDelimiter("\\A").next();
        }
        return results;
    }

    public static List<String> readAllLines(String fileName){
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(Class.forName(Utils.class.getName()).getResourceAsStream(fileName)))){
            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
