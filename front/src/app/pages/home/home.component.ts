import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  start() {
    alert('Commencez par lire le README et à vous de jouer !');
  }
}
