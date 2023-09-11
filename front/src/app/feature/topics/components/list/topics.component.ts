import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TopicsService } from 'src/app/feature/topics/services/topics.service';
import { Topics } from 'src/app/feature/topics/interfaces/subscription.interface';

@Component({
  selector: 'app-themes',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss'],
})
export class TopicsPage implements OnInit {
  constructor(private topicsService: TopicsService, private title: Title) {
    this.title.setTitle('MDD - Themes');
  }

  public topics: Topics[] = [];
  public loaded = false;

  ngOnInit(): void {
    this.topicsService.getTopics().subscribe((res) => {
      this.topics = res;
      this.loaded = true;
    });
  }
}
