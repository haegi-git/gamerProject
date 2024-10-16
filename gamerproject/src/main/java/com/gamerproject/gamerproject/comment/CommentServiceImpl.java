package com.gamerproject.gamerproject.comment;

import com.gamerproject.gamerproject.item.Item;
import com.gamerproject.gamerproject.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void addComment(CommentDto commentDto,
                           HttpSession session,
                           Item item) {

        Comment comment = new Comment();
        var loginUser = session.getAttribute("member");
            Member member = (Member) loginUser;
            comment.setComment(commentDto.getComment());
            comment.setDisplayName(member.getDisplayName());
            comment.setItem(item);
            commentRepository.save(comment);
    }
}
