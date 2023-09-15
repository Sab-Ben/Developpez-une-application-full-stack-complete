import { NgModule } from '@angular/core';
import { TopicsCardComponent } from './components/topics-card/topics-card.component';
import { MaterialModule } from 'src/app/material.module';
import { TopicsPage } from './components/list/topics.component';
import { TopicsService } from './services/topics.service';
import { ComponentsModule } from 'src/app/components/components.module';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [TopicsCardComponent, TopicsPage],
  imports: [MaterialModule, ComponentsModule, BrowserModule],
  exports: [TopicsCardComponent],
  providers: [TopicsService],
})
export class TopicsModule {}
