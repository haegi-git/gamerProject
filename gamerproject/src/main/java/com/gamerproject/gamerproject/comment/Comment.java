package com.gamerproject.gamerproject.comment;

import com.gamerproject.gamerproject.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String displayName;
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate = new Date();

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
