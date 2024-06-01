package com.example.first.project.dto;

import com.example.first.project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private Integer count;
    private boolean liked = false;
    private boolean marked = false;
//    AllargsConstructor;
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    //data check, Tostring
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content, count, liked, marked);
    }
}
