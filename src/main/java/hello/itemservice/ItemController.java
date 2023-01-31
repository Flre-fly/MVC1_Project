package hello.itemservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        if(items.isEmpty()) items = new ArrayList<>();
        model.addAttribute("items", items);
        return "basic/items";
    }
    @GetMapping("/{id}")
    public String item(@PathVariable Long id, Model model){
        Item item = itemRepository.getById(id);
        model.addAttribute("item", item);
        return "basic/item";
    }
    @GetMapping("/add")
    public String add(){
        return "basic/addForm";
    }
    @PostMapping("/add")
    //RequestParam -> url에서 데이터를 찾음
    //modelAttribute->form으로 요청할때 사용. xxx-form-urlencoded
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        //modelAttribute는 자동으로 model에 넣어주므로 아래의 코드가 필요없다
        //model.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        //return "basic/item";
        return "redirect:/basic/items/{itemId}";
        //"redirect:/basic/items/" + item.getId() redirect에서 +item.getId() 처럼 URL에 변수를
        //더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험하다. 다음에 설명하는 RedirectAttributes 를
        //사용하자.
    }

    //편집페이지로 이동
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.getById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
    //이걸로 편집해주세요! < 라는 요청
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }

}
