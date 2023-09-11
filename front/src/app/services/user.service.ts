import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private cachedUser$?: Observable<User>;
  constructor(private http: HttpClient) {}

  public me() {
    if (!this.cachedUser$) {
      this.cachedUser$ = this.http.get<User>('/api/user/me');
    }

    return this.cachedUser$;
  }

  public updateMe(data: any) {
    return this.http.put<User>('/api/user/me', data);
  }
}
