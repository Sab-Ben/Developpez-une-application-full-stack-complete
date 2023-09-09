import { NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { MaterialModule } from '../material.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [HeaderComponent, LayoutComponent],
  imports: [MaterialModule, RouterModule, CommonModule, ReactiveFormsModule],
  exports: [HeaderComponent, LayoutComponent],
})
export class ComponentsModule {}
