package com.gamerproject.gamerproject.comment;

import com.gamerproject.gamerproject.item.Item;
import jakarta.servlet.http.HttpSession;

public interface CommentService {
    public void addComment(CommentDto commentDto, HttpSession session, Item item);
}
