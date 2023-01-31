package hello.itemservice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class Item {
    //왜 long이 아니라 Long을쓸까? - null사용을 위해
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    public void update(String name, Integer price, Integer stock){
        //값이 있는 경우만 update되게끔
        if(!name.isEmpty()) this.itemName = name;
        if(price!=null) this.price = price;
        if(stock!=null) this.quantity = stock;
    }
}
