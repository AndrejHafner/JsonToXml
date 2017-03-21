import org.json.JSONObject;
import org.json.JSONException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.internal.$Gson$Preconditions;
import org.apache.commons.io.FileUtils;
import java.io.*;

public class JsonToXml {
  private static String file;
  private static HashMap<String,String> string_map;
  private static JSONObject json_file;
  private static PrintWriter writer;
  private static Scanner sc;

  public static void main(String[] args) {

    sc = new Scanner(System.in);

    System.out.print("Insert JSON file name to transform to XML(with the .json): ");
    String original_filename= sc.next();
    System.out.println();
    System.out.print("Insert XML filename to create: ");
    String output_filename = sc.next();


    try {
      file = FileUtils.readFileToString(new File(original_filename));
    } catch(IOException e) {
      System.out.println(e);
    }

    try {
            json_file = new JSONObject(file);
            string_map = new Gson().fromJson(file, new TypeToken<HashMap<String, String>>(){}.getType());
    } catch(JSONException e) {
      System.out.println(e.toString());
    }
    try{
        writer = new PrintWriter(output_filename+".xml", "UTF-8");
        writer.println("<resource>");


    } catch (IOException e) {
      System.out.println(e.toString());

    }

    for (Map.Entry<String, String> entry : string_map.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        writer.println("  <string name=\""+key+"\">"+value+"</string>");
        //<string name="navigation_drawer_close">Close navigation drawer</string>
        // ...
    }
    writer.println("<resource>");
    writer.close();


    //System.out.println(string_map);
  }


}
