import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { UsersSessionService } from 'src/app/services/usersSession.service';
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
  constructor(private userSessionService: UsersSessionService, private router: Router) {}
  public ROUTES = [
    { path: '/articles', label: 'Articles' },
    { path: '/themes', label: 'Thèmes' },
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
