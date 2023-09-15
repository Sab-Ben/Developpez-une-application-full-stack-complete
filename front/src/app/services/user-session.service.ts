import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Topics } from '../feature/topics/interfaces/subscription.interface';
import { Users } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class UserSessionService {
  public isLogged = false;
  public subscriptions: Topics[] = [];
  public user: Users = {} as Users;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);
  private subscriptionsSubject = new BehaviorSubject<Topics[]>(this.subscriptions);
  private userInformationSubject = new BehaviorSubject<Users>(this.user);

  public setSubscriptions(subscriptions: Topics[]): void {
    this.subscriptions = subscriptions;
    this.subscriptions.sort((a, b) => a.title.localeCompare(b.title));
    this.subscriptionsSubject.next(this.subscriptions);
  }

  public setUserInformation(user: Users): void {
    this.user = user;
    this.userInformationSubject.next(this.user);
  }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public $subscriptions(): Observable<Topics[]> {
    return this.subscriptionsSubject.asObservable();
  }

  public logIn(token: string): void {
    localStorage.setItem('token', token);
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.setUserInformation({} as Users);
    this.setSubscriptions([]);
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
