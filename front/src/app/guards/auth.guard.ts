import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UsersSessionService } from '../services/usersSession.service';
import { UsersService } from '../services/users.service';
import { Observable, of } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private sessionService: UsersSessionService, private usersService: UsersService, private router: Router) {}

  public canActivate(): Observable<boolean> | boolean {
    if (this.sessionService.isLogged) {
      return true;
    }

    if (!localStorage.getItem('token')) {
      this.router.navigate(['/auth/login']);
      return false;
    }

    return this.usersService.me().pipe(
      switchMap((users: any) => {
        const token = localStorage.getItem('token');
        this.sessionService.users = { ...this.sessionService.users, ...users };
        this.sessionService.logIn(token!);
        return of(true);
      }),
      catchError(async () => this.router.navigate(['/auth/login']))
    );
  }
}
