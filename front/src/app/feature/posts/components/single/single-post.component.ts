import { Component, OnInit } from '@angular/core';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Posts } from '../../interfaces/posts.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { TopicsService } from 'src/app/feature/topics/services/topics.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'single-article',
  templateUrl: './single-post.component.html',
  styleUrls: ['./single-post.component.scss'],
})
export class SinglePostComponent implements OnInit {
  constructor(
    private postsService: PostsService,
    private route: ActivatedRoute,
    private topicsService: TopicsService,
    private formBuilder: FormBuilder,
    private title: Title
  ) {}

  public posts: Posts = {} as Posts;
  public topics = '';
  private postId: string = '';
  public loaded = false;


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

  }
}
