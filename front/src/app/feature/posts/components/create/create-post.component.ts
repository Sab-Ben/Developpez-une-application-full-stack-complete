import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopicsService } from 'src/app/feature/topics/services/topics.service';
import { Topics } from 'src/app/feature/topics/interfaces/subscription.interface';
import { PostsService } from '../../services/posts.service';
import { CreatePostRequest } from '../../interfaces/createPostRequest.interface';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {
  public topics: Topics[] = [];
  public postForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(3)]],
    description: ['', [Validators.required, Validators.minLength(3)]],
    topics: [0, [Validators.required, Validators.min(1)]],
  });
  public onError: boolean = false;

  constructor(
    private topicsService: TopicsService,
    public fb: FormBuilder,
    private router: Router,
    private postsService: PostsService,
    private title: Title
  ) {
    this.title.setTitle('MDD - Créer un article');
  }

  public submit() {
    const post = this.postForm.value as CreatePostRequest;
    this.postsService.createPost(post).subscribe({
      next: () => {
        this.router.navigate(['/posts']);
      },
      error: () => (this.onError = true),
    });
  }

  ngOnInit(): void {
    this.topicsService.getTopics().subscribe((res) => {
      this.topics = res;
    });
  }
}
