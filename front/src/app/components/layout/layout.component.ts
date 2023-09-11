import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent {
  @Input() withBackLink?: boolean = false;
  @Input() link: string = '/';
  @Input() title: string = '';
}
