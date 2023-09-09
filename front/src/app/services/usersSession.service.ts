import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Users } from '../interfaces/users';

@Injectable({
  providedIn: 'root',
})
export class UsersSessionService {
  public isLogged = false;
  public users: Users = {} as Users;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);
  private userInformationSubject = new BehaviorSubject<Users>(this.users);


  public setUserInformation(users: Users): void {
    this.users = users;
    this.userInformationSubject.next(this.users);
  }


  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(token: string): void {
    localStorage.setItem('token', token);
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.setUserInformation({} as Users);
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
