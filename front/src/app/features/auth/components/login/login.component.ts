import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/loginRequest';
import { LoginResponse } from '../../interfaces/loginResponse';
import { UsersSessionService } from 'src/app/services/usersSession.service';
import { UsersService } from 'src/app/services/users.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
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
    private usersSessionService: UsersSessionService,
    private usersService: UsersService,
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
        this.usersSessionService.logIn(response.token);
        this.usersService.me().subscribe((user) => {
          this.usersSessionService.setUserInformation(user);
          this.router.navigate(['/profile']);
        });
      },
      error: () => (this.onError = true),
    });
  }
}
