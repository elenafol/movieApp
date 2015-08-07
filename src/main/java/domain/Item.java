package domain;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class Item  {
    private long Id;
    private long readTnc;
    private String type;
    private Item  item;


    public Item(JsonObject itemFromJson) {
        Id = itemFromJson.getJsonNumber("id").longValue();
        readTnc = itemFromJson.getJsonNumber("readTnc").longValue();
        type = itemFromJson.getString("type");
    }

    public Item(){

    }


  /* public Item getItemFromSet (JsonArray set) {
       for (int i = 0; i < set.size(); i++) {
           JsonObject itemFromJson = set.getJsonObject(i);
           Item item = new Item (itemFromJson);

       } return item;
   }*/

    public long getId() {
        return Id;
    }


}
