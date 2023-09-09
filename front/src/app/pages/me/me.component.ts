import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersSessionService } from 'src/app/services/usersSession.service';
import { Subscription } from 'rxjs';
import { UsersService } from 'src/app/services/users.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css'],
})
export class MeComponent implements OnInit, OnDestroy {
  public users: any = this.usersSessionService.users;
  public updateValid: boolean = false;

  constructor(
    private router: Router,
    private usersSessionService: UsersSessionService,
    private usersService: UsersService,
    private fb: FormBuilder,
    private title: Title
  ) {
    this.title.setTitle('MDD - Profil');
  }

  public form = this.fb.group({
    username: [this.users.username, [Validators.required, Validators.minLength(3)]],
    email: [this.users.email, [Validators.required, Validators.email]],
  });

  private destroy$: Subscription = new Subscription();

  submit() {
    const request = this.form.value;
    this.usersService.updateMe(request).subscribe((res) => {
      this.usersSessionService.setUserInformation(res);
      this.updateValid = true;
      setTimeout(() => {
        this.updateValid = false;
      }, 3000);
    });
  }

  logout() {
    this.usersSessionService.logOut();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
