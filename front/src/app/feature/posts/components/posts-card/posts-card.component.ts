import { Component, Input, OnInit } from '@angular/core';
import { Posts } from 'src/app/feature/posts/interfaces/posts.interface';

@Component({
  selector: 'posts-card',
  templateUrl: './posts-card.component.html',
  styleUrls: ['./posts-card.component.scss'],
})
export class PostsCardComponent implements OnInit {
  @Input() posts!: Posts;

  public formatContent: string = '';

  ngOnInit(): void {
    this.formatContent =
      this.posts.description.length > 250 ? this.posts.description.slice(0, 250) + '...' : this.posts.description;
  }
}
