import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthGuard } from './guards/auth.guard';
import { UnAuthGuard } from './guards/unauth.guard';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { TopicsPage } from './feature/topics/components/list/topics.component';
import { PostsPage } from "./feature/posts/components/list/posts.component";
import { CreatePostComponent } from "./feature/posts/components/create/create-post.component";
import { SinglePostComponent } from "./feature/posts/components/single/single-post.component";

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    canActivate: [UnAuthGuard],
  },
  {
    path: 'auth',
    loadChildren: () => import('./feature/auth/auth.module').then((m) => m.AuthModule),
    canActivate: [UnAuthGuard],
  },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  {
    path: 'posts',
    component: PostsPage,
    canActivate: [AuthGuard],
  },
  {
    path: 'posts/create',
    component: CreatePostComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'posts/:id',
    component: SinglePostComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'topics',
    component: TopicsPage,
    canActivate: [AuthGuard],
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
