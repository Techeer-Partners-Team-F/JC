package com.example.first.project.service;

import com.example.first.project.dto.ArticleForm;
import com.example.first.project.entity.Article;
import com.example.first.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if (article.getId() != null) {
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, articleRepository.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        articleRepository.delete(target);
        return target;
    }

    public Article updateBookmark(Long id, ArticleForm request) {
        Article bookmark = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bookmark not found with id: " + id));
        boolean marked = request.isMarked();
        bookmark.setMarked(marked);
        Article updatedbookmark = articleRepository.save(bookmark);
        return updatedbookmark;
    }

    public List<Article> getBookmarks() {
        List<Article> allArticles = articleRepository.findAll();
        return allArticles.stream()
                .filter(Article::isMarked)
                .collect(Collectors.toList());    }
}
