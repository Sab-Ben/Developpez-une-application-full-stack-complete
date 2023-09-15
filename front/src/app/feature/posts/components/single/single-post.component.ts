import { Component, OnInit } from '@angular/core';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute } from '@angular/router';
import { Posts } from '../../interfaces/posts.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { TopicsService } from 'src/app/feature/topics/services/topics.service';
import { Title } from '@angular/platform-browser';
import { CommentService } from "../../services/comment.service";
import { Comments } from "../../interfaces/comments.interface";
import {CreateCommentRequest} from "../../interfaces/createComment.interface";

@Component({
  selector: 'single-post',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.scss'],
})
export class SinglePostComponent implements OnInit {
  constructor(
    private postsService: PostsService,
    private route: ActivatedRoute,
    private commentService: CommentService,
    private topicsService: TopicsService,
    private formBuilder: FormBuilder,
    private title: Title
  ) {}

  public posts: Posts = {} as Posts;
  public comments: Comments[] = [];
  public topics = '';
  private postId: string = '';
  public loaded = false;
  public commentForm = this.formBuilder.group({
    description: ['', [Validators.required, Validators.minLength(3)]],
  });

  submitComment() {
    const comment = this.commentForm.value as CreateCommentRequest;
    comment.posts = Number(this.postId);
    this.commentService.createComment(comment).subscribe((res) => {
      this.comments.push(res);
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.postId = params.get('id') as string;
    });

    this.postsService.getPost(this.postId).subscribe((res) => {
      this.posts = res;
      this.title.setTitle(`MDD - ${res.title}`);
      this.topicsService.findOne(res.topics).subscribe((res) => {
        this.topics = res.title;
        this.loaded = true;
      });
    });


    this.commentService.getPostsComments(this.postId).subscribe((res) => {
      this.comments = res;
    });

  }
}
