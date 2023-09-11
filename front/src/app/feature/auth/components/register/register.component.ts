import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { SignupRequest } from '../../interfaces/signupRequest.interface';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'register-page',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public onError: boolean = false;

  public form = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]],
    password: [
      '',
      [
        Validators.required,
        Validators.min(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+!=*])(?=\S+$).{8,}$/),
      ],
    ],
  });

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router, private title: Title) {
    this.title.setTitle('MDD - Register');
  }

  public submit() {
    const registerRequest = this.form.value as SignupRequest;
    this.authService.signup(registerRequest).subscribe({
      next: () => {
        this.router.navigate(['/auth/login']);
      },
      error: () => (this.onError = true),
    });
  }
}
