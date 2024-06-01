package com.example.first.project.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Integer count;
    @Column
    private boolean liked;
    @Column
    private boolean marked;
    public void setMarked(boolean marked) { this.marked = marked; }
    public boolean isMarked() { return marked; }
    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null);
        this.content = article.content;
    }

    public int getLikeCount() {
        return count;
    }

    public void setLikeCount(Integer count) {
        this.count = count;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

//    @Entity
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", Title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
