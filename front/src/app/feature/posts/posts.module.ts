import { NgModule } from '@angular/core';
import { PostsCardComponent } from './components/posts-card/posts-card.component';
import { MaterialModule } from 'src/app/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { ComponentsModule } from 'src/app/components/components.module';
import { CreatePostComponent } from './components/create/create-post.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { SinglePostComponent } from './components/single/single-post.component';
import { PostsPage } from './components/list/posts.component';
import { PostsService } from './services/posts.service';


@NgModule({
  declarations: [PostsCardComponent, CreatePostComponent, SinglePostComponent, PostsPage],
  imports: [MaterialModule, BrowserModule, ComponentsModule, RouterModule, ReactiveFormsModule],
  providers: [PostsService ],
  exports: [],
})
export class PostsModule {}
