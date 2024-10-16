package com.gamerproject.gamerproject.item;

import com.gamerproject.gamerproject.ResourceNotFoundException;
import com.gamerproject.gamerproject.comment.Comment;
import com.gamerproject.gamerproject.comment.CommentDto;
import com.gamerproject.gamerproject.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final ItemService itemService;
    private final S3Service s3Service;

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
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found")); // 아이템이 없을 경우 예외 처리
        List<Comment> comments = commentRepository.findByItemId(id);
        model.addAttribute("item", item); // 모델에 아이템 추가
        model.addAttribute("commentsItem", comments);
        model.addAttribute("commentDto", new CommentDto());

        return "detail.html";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {

        Optional<Item> item = itemRepository.findById(id);

        model.addAttribute("item", item); // 모델에 아이템 추가

        return "update.html";
    }
    @PostMapping("/update/boasting/{id}")
    public String updateBoasting(@PathVariable Long id, Model model,
                                 ItemDto itemDto
    ) throws Exception {

        model.addAttribute("item", itemRepository.findById(id).get());
        itemService.updateItem(id,itemDto);

        return "redirect:/boasting";

    }

    @PostMapping("/write/boasting")
    public String writeBoasting(ItemDto itemDto
                                ) throws Exception {

        itemService.addItem(itemDto);

        return "redirect:/boasting";
    }

    @GetMapping("/write-imgurl")
    @ResponseBody
    public String writeImgurl(@RequestParam String filename) {
        var result = s3Service.createPreSignedUrl("test/" + filename);
        System.out.println(result);
        return result;

    }


}
