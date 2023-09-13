import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UserSessionService } from 'src/app/services/user-session.service';
import { Observable, of, Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent implements OnInit {
  public $isLogged: Observable<boolean> = of(false);

  private destroy$!: Subscription;
  constructor(private userSessionService: UserSessionService, private router: Router) {}
  public ROUTES = [
    { path: '/posts', label: 'Articles' },
    { path: '/topics', label: 'Thèmes' },
  ];

  ngOnInit(): void {
    this.destroy$ = this.userSessionService.$isLogged().subscribe((res) => (this.$isLogged = of(res)));
  }

  isActive(path: string): boolean {
    return this.router.url === path;
  }

  ngOnDestroy(): void {
    this.destroy$.unsubscribe();
  }
}
