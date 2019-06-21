import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class MumlaFactory {

    private MumlaFactory() {
    }

    /*
     * <p>Возвращает новый объект типа Human</p>
     *
     * @param params - параметры для создания Human
     * @return Новый объект типа Human
     * @throws Exception при недостаточном количестве параметров
     */
 /*   public static Mumla newInstance(Map<String, Object> params) throws Exception {
        if (params.containsKey("name")) {
            Mumla mumla = new Mumla(params.get("name").toString());
            if (params.containsKey("height"))
                try {
                    mumla.setHeight(Integer.parseInt(params.get("height").toString()));
                } catch (NumberFormatException e) {
                    mumla.setHeight((int) Math.round((double) params.get("height")));
                }
            if (params.containsKey("weight"))
                try {
                    mumla.setWeight(Integer.parseInt(params.get("weight").toString()));
                } catch (NumberFormatException e) {
                    mumla.setWeight((int) Math.round((double) params.get("weight")));
                }
            if (params.containsKey("width"))
                try {
                    mumla.setWidth(Integer.parseInt(params.get("width").toString()));
                } catch (NumberFormatException e) {
                    mumla.setWeight((int) Math.round((double) params.get("width")));
                }
            if (params.containsKey("area"))
                mumla.setPlace((Place) params.get("area"));
            return mumla;
        } else {
            throw new Exception();
        }}
   */

    public static Mumla createMumla(String json) throws NumberFormatException, ClassCastException, ClassNotFoundException, JsonSyntaxException {
        Gson gson = new Gson();
        Map map;
        Type type = new TypeToken<LinkedTreeMap<String, Object>>() {
        }.getType();
        Object object = null;
        Homsa homsa;
        map = gson.fromJson(json, type);
        Mumla mumla = new Mumla();
        if (map.containsKey("weight")) mumla.setWeight((Double) map.get("weight"));
        if (map.containsKey("height")) mumla.setHeight((Double) map.get("height"));
        if (map.containsKey("width")) mumla.setWidth((Double) map.get("width"));
        if (map.containsKey("name")) mumla.setName(map.get("name").toString());
        if (map.containsKey("area")) mumla.setArea(map.get("area").toString());
        if (map.containsKey("troll")) {
            homsa = gson.fromJson(map.get("troll").toString(), Homsa.class);
            mumla.setTroll(homsa);
        }
        object = mumla;
        return mumla;
    }
}