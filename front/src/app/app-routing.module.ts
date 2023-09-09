import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {UnAuthGuard} from "./guards/unauth.guard";
import {AuthGuard} from "./guards/auth.guard";
import {MeComponent} from "./pages/me/me.component";

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [UnAuthGuard],},
  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.module').then((m) => m.AuthModule),
    canActivate: [UnAuthGuard],
  },
  { path: 'me', component: MeComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
