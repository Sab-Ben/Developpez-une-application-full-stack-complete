import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { LoginResponse } from '../../interfaces/loginResponse.interface';
import { UserSessionService } from 'src/app/services/user-session.service';
import { UserService } from 'src/app/services/user.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });

  constructor(
    private authService: AuthService,
    private userSessionService: UserSessionService,
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router,
    private title: Title
  ) {
    this.title.setTitle('MDD - Login');
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (response: LoginResponse) => {
        this.userSessionService.logIn(response.token);
        this.userService.me().subscribe((user) => {
          this.userSessionService.setUserInformation(user);
          this.router.navigate(['/profile']);
        });
      },
      error: () => (this.onError = true),
    });
  }
}
