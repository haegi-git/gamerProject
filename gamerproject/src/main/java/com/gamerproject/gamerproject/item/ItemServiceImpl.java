package com.gamerproject.gamerproject.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    @Override
    public void addItem(ItemDto itemDto) {
        Item item = new Item();
        item.setTitle(itemDto.getTitle());
        item.setContents(itemDto.getContents());

        itemRepository.save(item);
    }
    @Override
    public void updateItem(Long id,ItemDto itemDto) throws Exception {

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setTitle(itemDto.getTitle());
            item.setContents(itemDto.getContents());
            itemRepository.save(item);
        }
    }
}
