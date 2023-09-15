import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignupRequest } from '../interfaces/signupRequest.interface';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { Observable } from 'rxjs';
import { LoginResponse } from '../interfaces/loginResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>('/api/auth/login', loginRequest);
  }

  public signup(signupRequest: SignupRequest): Observable<string> {
    return this.http.post<string>('/api/auth/register', signupRequest);
  }
}
