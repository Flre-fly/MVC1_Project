package hello.itemservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//스프링빈으로 등록해서 외부에서 사용할 수 있도록함
public class ItemRepository {
    private final Map<Long, Item> store = new HashMap();
    //싱글톤으로 관리되기때문에 이 id라는 변수를 모든 요청에대해 공유될것이다
    private Long id = 0l;
    public Item save(Item item){
        item.setId(id);
        store.put(id++,item);
        return item;
    }
    public List<Item> findAll(){
        ArrayList<Item> result = new ArrayList<>();
        store.forEach((k,v)->{
            result.add(v);
        });
        return result;
    }
    public Item getById(Long id){
        return store.get(id);
    }
    public void update(Long id, Item item){
        Item origin = store.get(id);
        origin.update(item.getItemName(), item.getPrice(), item.getPrice());
    }
    public void remove(Long id){
        store.remove(id);
    }

}
