package com.gamerproject.gamerproject.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/boasting")
    public String boasting(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "boasting.html";
    }

    @GetMapping("/write")
    public String write() {
        return "write.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            model.addAttribute("item", item.get());
            return "detail.html";
        }else{
            return "error.html";
        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            model.addAttribute("item", item.get());
            return "update.html";
        }else{
            return "error.html";
        }
    }
    @PostMapping("/update/boasting/{id}")
    public String updateBoasting(@PathVariable Long id, Model model,
                                 String title,
                                 String contents,
                                 String imgUrl) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setTitle(title);       // 기존 객체의 title 업데이트
            item.setContents(contents);  // 기존 객체의 contents 업데이트
            item.setImgUrl(imgUrl);     // 기존 객체의 imgUrl 업데이트

            itemRepository.save(item);
            return "redirect:/boasting";
        } else {
            return "redirect:/error";
        }

    }

    @PostMapping("/write/boasting")
    public String writeBoasting(String title,
                                String contents,
                                String imgUrl) {

        Item item = new Item();
        item.setTitle(title);
        item.setContents(contents);
        item.setImgUrl(imgUrl);
        item.setWriteDate(new Date());
        itemRepository.save(item);

        return "redirect:/boasting";
    }
}
