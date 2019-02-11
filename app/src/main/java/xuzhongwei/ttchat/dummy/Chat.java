package xuzhongwei.ttchat.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat {

    public static List<ChatItem> ITEMS = new ArrayList<>();

    public static Map<String,ChatItem> ITEM_MAP = new HashMap<>();

    public static int COUNT = 6;

    static {
        for(int i=0;i<COUNT;i++){
            addItem(createDummyData(i));
        }
    }

    public static void addItem(ChatItem item){
        ITEMS.add(item);
        ITEM_MAP.put("" + item.id,item);
    }

    private static ChatItem createDummyData(int position){
        if(position > 0 && position % 2 == 0){
            return new ChatItem(position,"person","2019-02-03 12:33","chat content",ChatIdentity.ISME);
        }else{
            return new ChatItem(position,"person","2019-02-03 12:33","chat content",ChatIdentity.ISOPPOSITE);
        }
    }

    public static void insertNewItem(String content){
        ChatItem item = generateNewItem(content);
        ITEMS.add(item);
        ITEM_MAP.put("" + item.id,item);
    }

    private static ChatItem generateNewItem(String content){
        return new ChatItem(ITEMS.size(),"person","2019-02-03 12:33",content,ChatIdentity.ISME);
    }

    public static class ChatItem{
        public final int id;
        public final String name;
        public final String time;
        public final String content;
        public final ChatIdentity identity;

        public ChatItem(int id,String name, String time, String content,ChatIdentity identity) {
            this.id = id;
            this.name = name;
            this.time = time;
            this.content = content;
            this.identity = identity;
        }

        public String toString(){
            return this.content;
        }
    }

    public static enum ChatIdentity{
        ISME,
        ISOPPOSITE,
        ISTHIRDPART
    }
}
