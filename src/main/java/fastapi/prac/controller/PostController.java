package fastapi.prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fastapi.prac.model.Post;
import fastapi.prac.service.PostService;

import java.util.Optional;
// api/post/ id ->게시글 생성

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
// 게시글 목록 조회
    @GetMapping
    public ResponseEntity<Page<Post>> ListPost(@RequestParam (defaultValue = "0")int page,
                                               @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> posts = postService.getPosts(pageable);
        return ResponseEntity.ok().body(posts);
    }
    //게시글  단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Optional<Post> postOpt= postService.getPost(id);
        return postOpt.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    //게시글 생성
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }
//게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatePost){
        Optional<Post> updated = postService.updatePost(id, updatePost);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        boolean deleted = postService.deletePost(id);
        if(deleted) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
