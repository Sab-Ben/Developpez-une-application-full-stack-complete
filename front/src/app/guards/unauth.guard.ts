import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UserSessionService } from '../services/user-session.service';

@Injectable({ providedIn: 'root' })
export class UnAuthGuard implements CanActivate {
  constructor(private router: Router, private sessionService: UserSessionService) {}

  public canActivate(): boolean {
    if (this.sessionService.isLogged) {
      this.router.navigate(['/profile']);
      return false;
    }
    return true;
  }
}
