package com.example.first.project.api;

import com.example.first.project.dto.ArticleForm;
import com.example.first.project.entity.Article;
import com.example.first.project.service.ArticleService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Slf4j
@RestController
@Tag(name = "Board")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @Operation(summary = "Read all articles")
    //@ApiResponse(responseCode = "200", description = "List of URLs retrieved successfully")
    @GetMapping("/swagger-ui/index.html/v1/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @Operation(summary = "Read a article by Id")
    @GetMapping("/swagger-ui/index.html/v1/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @Operation(summary = "Create Article", description = "input 'title' and 'content' only, Other parameters are completed automatically.")
    @PostMapping("/swagger-ui/index.html/v1/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Operation(summary = "Update Article", description = "input Id parameter and requestbody 'title','content'")
    @PatchMapping("/swagger-ui/index.html/v1/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Operation(summary = "Delete Article")
    @DeleteMapping("/swagger-ui/index.html/v1/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Operation(summary = "Update bookmark", description = "input true or false to marked only it's boolean type")
    @PatchMapping("/swagger-ui/index.html/v1/api/bookmark/{id}")
    public ResponseEntity<Article> updatebookmark(@PathVariable Long id, @RequestBody ArticleForm request) {
        boolean marked = request.isMarked();
        Article updatedbookmark = articleService.updateBookmark(id, request);
        return (updatedbookmark != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updatedbookmark) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Operation(summary = "Get All bookmarks")
    @GetMapping("/swagger-ui/index.html/v1/api/bookmarks")
    public ResponseEntity<List<Article>> getBookmarks() {
        List<Article> bookmarks = articleService.getBookmarks();
        return ResponseEntity.status(HttpStatus.OK).body(bookmarks);
    }

//    @Autowired
//    private ArticleRepository articleRepository;

//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        Article target = articleRepository.findById(id).orElse(null);
//
//        if (target == null || id != article.getId()) {
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        target.patch(article);
//
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        Article target = articleRepository.findById(id).orElse(null);
//
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }



}
