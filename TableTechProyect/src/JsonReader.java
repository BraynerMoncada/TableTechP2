import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase encargada de leer los datos del archivo json
 */
public class JsonReader {

    public static JSONObject leerArchivo(String rutaArchivo) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader(rutaArchivo);
        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
        fileReader.close();
        return jsonObject;
    }
}
