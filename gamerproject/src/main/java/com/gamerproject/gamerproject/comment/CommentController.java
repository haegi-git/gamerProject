package com.gamerproject.gamerproject.comment;

import com.gamerproject.gamerproject.item.Item;
import com.gamerproject.gamerproject.item.ItemRepository;
import com.gamerproject.gamerproject.member.Member;
import com.gamerproject.gamerproject.member.MemberRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    final CommentRepository commentRepository;
    final CommentService commentService;
    private final ItemRepository itemRepository;


    @PostMapping("/comment/{itemId}")
    public String commentPost(@Valid CommentDto commentDto,
                              BindingResult bindingResult,
                             HttpSession session,
                              RedirectAttributes redirectAttributes,
                              @PathVariable("itemId") Long itemId
                             ) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템이 존재하지 않습니다."));

        if (bindingResult.hasErrors()) {
            System.out.println("댓글공백에러" + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", "댓글을 입력해주세요.");
            return "redirect:/detail/"+itemId;
        }
        commentService.addComment(commentDto,session,item);

        return "redirect:/detail/"+itemId;
    }


}
