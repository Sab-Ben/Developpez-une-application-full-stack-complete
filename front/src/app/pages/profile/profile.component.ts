import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSessionService } from 'src/app/services/user-session.service';
import { Subscription } from 'rxjs';
import { Topics } from 'src/app/feature/topics/interfaces/subscription.interface';
import { TopicsService } from 'src/app/feature/topics/services/topics.service';
import { UserService } from 'src/app/services/user.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  public user: any = this.userSessionService.user;
  public updateValid: boolean = false;

  constructor(
    private router: Router,
    private userSessionService: UserSessionService,
    private userService: UserService,
    private fb: FormBuilder,
    private topicsService: TopicsService,
    private title: Title
  ) {
    this.title.setTitle('MDD - Profil');
  }

  public form = this.fb.group({
    username: [this.user.username, [Validators.required, Validators.minLength(3)]],
    email: [this.user.email, [Validators.required, Validators.email]],
  });

  public topicsSubcriptions: Topics[] = [];
  private destroy$: Subscription = new Subscription();

  submit() {
    const request = this.form.value;
    this.userService.updateMe(request).subscribe((res) => {
      this.userSessionService.setUserInformation(res);
      this.updateValid = true;
      setTimeout(() => {
        this.updateValid = false;
      }, 3000);
    });
  }

  logout() {
    this.userSessionService.logOut();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    this.topicsService.getUserSubscribedTopics().subscribe((res) => this.userSessionService.setSubscriptions(res));
    this.destroy$ = this.userSessionService.$subscriptions().subscribe((subscriptions) => {
      this.topicsSubcriptions = subscriptions;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
