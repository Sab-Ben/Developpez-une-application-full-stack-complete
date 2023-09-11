import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { AuthComponent } from './pages/auth/auth.component';
import { MaterialModule } from './material.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ComponentsModule } from './components/components.module';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { TopicsModule } from './feature/topics/topics.module';

@NgModule({
  declarations: [AppComponent, ProfileComponent, AuthComponent, NotFoundComponent],
  imports: [
    TopicsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    ComponentsModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent],
})
export class AppModule {}
