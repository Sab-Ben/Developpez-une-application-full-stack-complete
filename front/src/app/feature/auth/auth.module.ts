import { NgModule } from '@angular/core';
import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ComponentsModule } from 'src/app/components/components.module';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [AuthRoutingModule, ComponentsModule, CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  exports: [],
})
export class AuthModule {}
