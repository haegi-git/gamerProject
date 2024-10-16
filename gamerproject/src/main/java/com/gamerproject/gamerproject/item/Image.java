package com.gamerproject.gamerproject.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrls; // 이미지 URL 리스트
    private String imageNames; // 이미지 이름 리스트

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
