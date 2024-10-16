package com.gamerproject.gamerproject.item;

import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

    void addItem(ItemDto itemDto) throws Exception;

    void updateItem(Long id,ItemDto itemDto) throws Exception;

}
