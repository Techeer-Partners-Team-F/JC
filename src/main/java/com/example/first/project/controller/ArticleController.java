package com.example.first.project.controller;

import com.example.first.project.repository.ArticleRepository;
import com.example.first.project.dto.ArticleForm;
import com.example.first.project.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString()); > @Slf4j, log.info()

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id="+id);
        Optional<Article> articleEntity = articleRepository.findById(id);
        model.addAttribute("article", articleEntity.orElse(null));

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Delect Complete!");
        }
        return "redirect:/articles";
    }

    @PostMapping("/articles/{id}/like")
    public String like(@PathVariable Long id, RedirectAttributes rttr) {
        Optional<Article> articleEntity = articleRepository.findById(id);
        articleEntity.ifPresent(article -> {
            if (!article.isLiked()) {
                article.setLikeCount(article.getLikeCount() + 1);
                article.setLiked(true);
            } else {
                article.setLikeCount(article.getLikeCount() - 1);
                article.setLiked(false);
            }
            articleRepository.save(article);
        });
        return "redirect:/articles/{id}";
    }

//    @PatchMapping("/bookmark/{id}")
//    public ResponseEntity<Void> redirectUrl(@PathVariable String hash) {
//        Url url = service.findByHash(hash);
//        if (url.isActive()) {
//            return ResponseEntity.status(302).location(URI.create(url.getOriginurl())).build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/articles/{id}/like")
//    public String like(@PathVariable Long id, RedirectAttributes rttr) {
//        articleRepository.findById(id).ifPresent(article -> {
//            article.setLikeCount(article.getLikeCount() + 1);
//            articleRepository.save(article);
//        });
//        return "redirect:/articles/{id}";
//    }

}
