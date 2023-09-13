import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Posts } from '../interfaces/posts.interface';
import { Observable } from 'rxjs';
import { CreatePostRequest } from '../interfaces/createPostRequest.interface';

@Injectable({
  providedIn: 'root',
})
export class PostsService {
  constructor(private http: HttpClient) {}

  public getPosts(): Observable<Posts[]> {
    return this.http.get<Posts[]>(`/api/posts/feed`);
  }

  public getPost(id: string): Observable<Posts> {
    return this.http.get<Posts>(`/api/posts/${id}`);
  }

  public createPost(post: CreatePostRequest): Observable<Posts> {
    return this.http.post<Posts>(`/api/posts`, post);
  }
}
