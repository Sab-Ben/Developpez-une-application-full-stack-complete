import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comments } from '../interfaces/comments.interface';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  constructor(private http: HttpClient) {}

  public createComment(comment: any): Observable<Comments> {
    return this.http.post<Comments>(`/api/comment`, comment);
  }

  public getPostsComments(postsId: string): Observable<Comments[]> {
    return this.http.get<Comments[]>(`/api/posts/${postsId}/comments`);
  }
}
