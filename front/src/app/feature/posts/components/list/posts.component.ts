import { Component } from '@angular/core';
import { Posts } from 'src/app/feature/posts/interfaces/posts.interface';
import { PostsService } from 'src/app/feature/posts/services/posts.service';
import { OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'posts-page',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss'],
})
export class PostsPage implements OnInit {
  constructor(private postsService: PostsService, private title: Title) {
    this.title.setTitle('MDD - Articles');
  }
  public posts: Posts[] = [];
  public loaded = false;
  public sortingASC: boolean = true;

  ngOnInit(): void {
    this.postsService.getPosts().subscribe({
      next: (posts) => {
        this.posts = posts;
        this.loaded = true;
      },
      error: (error) => console.error(error),
    });
  }

  public handleSort(): void {
    this.sortingASC = !this.sortingASC;
    this.posts.sort((a, b) => {
      if (this.sortingASC) {
        return a.id - b.id;
      } else {
        return b.id - a.id;
      }
    });
  }
}
