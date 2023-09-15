import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserSessionService } from '../services/user-session.service';
import { UserService } from '../services/user.service';
import { Observable, of } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private sessionService: UserSessionService, private userService: UserService, private router: Router) {}

  public canActivate(): Observable<boolean> | boolean {
    if (this.sessionService.isLogged) {
      return true;
    }

    if (!localStorage.getItem('token')) {
      this.router.navigate(['/auth/login']);
      return false;
    }

    return this.userService.me().pipe(
      switchMap((user: any) => {
        const token = localStorage.getItem('token');
        this.sessionService.user = { ...this.sessionService.user, ...user };
        this.sessionService.logIn(token!);
        return of(true);
      }),
      catchError(async () => this.router.navigate(['/auth/login']))
    );
  }
}
